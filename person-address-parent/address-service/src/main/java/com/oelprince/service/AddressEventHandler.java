package com.oelprince.service;

/*
 * The MIT License
 *
 * Copyright 2018 oelprince.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.oelprince.entity.Address;
import com.oelprince.events.control.EventConsumer;
import com.oelprince.events.entity.AddressUpdated;
import com.oelprince.events.entity.CreatePerson;
import com.oelprince.events.entity.CreditAccept;
import com.oelprince.events.entity.CreditReject;
import com.oelprince.events.entity.PersonAddressEvent;
import com.oelprince.events.entity.RemovePerson;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;


/**
 *
 * @author oelprince
 */
@Singleton
@Startup
public class AddressEventHandler {
    
    private EventConsumer eventConsumer;

    @Inject
    private AddressCommandService commandService;
    
    @Inject
    private AddressQueryService queryService;

    @Resource
    private ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<PersonAddressEvent> events;

    @Inject
    Logger logger;

    public void handle(@Observes AddressUpdated event) {
        commandService.acceptAddress(event.getPersonAddressInfo().getAddressId());
    }
    
    public void handle(@Observes CreatePerson event) {
        Address address = null;
        
        if (event.getPersonAddressInfo().getPersonId() != null && event.getPersonAddressInfo().getAddressId() != null) {
            address = queryService.getAddress(event.getPersonAddressInfo().getAddressId().toString());
        } 
        
        if (address != null) {
            address.setPersonId(event.getPersonAddressInfo().getPersonId().toString());
            commandService.updateAddress(address);
        }
    }
    
    public void handle(@Observes RemovePerson event) {
        Address address = null;
        
        if (event.getPersonAddressInfo().getPersonId() != null && event.getPersonAddressInfo().getAddressId() != null) {
            address = queryService.getAddress(event.getPersonAddressInfo().getAddressId().toString());
        }
        commandService.removePersonFromAddress(address);
    }
    
    public void handle(@Observes CreditAccept event) {
        
        logger.log(Level.INFO, "Person with id [{0}] have sufficient credit", event.getPersonAddressInfo().getPersonId());
        
        
    }
    
    public void handle(@Observes CreditReject event) {
        
        Address address = null;
        if (event.getPersonAddressInfo().getPersonId() != null && event.getPersonAddressInfo().getAddressId() != null) {
            address = queryService.getAddress(event.getPersonAddressInfo().getAddressId().toString());
        }
        
        logger.log(Level.INFO, "Person with id [{0}] have insufficient credit", event.getPersonAddressInfo().getPersonId());
        
        commandService.removePersonFromAddress(address);
    }
    
    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "address-handler");
        String person = kafkaProperties.getProperty("person.topic");
        String credit = kafkaProperties.getProperty("credit.topic");
        
        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            logger.info("firing = " + ev);
            events.fire(ev);
        }, person, credit);

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

    
    
    
}
