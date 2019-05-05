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
import com.oelprince.entity.PersonAddress;
import com.oelprince.events.control.EventConsumer;
import com.oelprince.events.entity.CreatePerson;
import com.oelprince.events.entity.PersonAddressEvent;
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
public class PersonAddressEventHandler {

    private EventConsumer eventConsumer;
    
    @Resource
    private ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<PersonAddressEvent> events;
    
    @Inject
    PersonAddressQueryService queryService;
    
    @Inject
    PersonAddressCommandService commandService;

    @Inject
    Logger logger;
    
    public void handle(@Observes CreatePerson event) {
        
        String addressId = null;
        
        if (event.getPersonAddressInfo().getAddressId() != null) {
            addressId = event.getPersonAddressInfo().getAddressId().toString();
        }
        
        
        PersonAddress personAddress = queryService.getPersonAddress(
                event.getPersonAddressInfo().getPersonId().toString(),
                addressId);
       
        if(personAddress == null) {
            personAddress = new PersonAddress();
            personAddress.setPersonId(event.getPersonAddressInfo().getPersonId().toString());
            personAddress.setAddressId(addressId);
        }

        commandService.createPersonAddress(personAddress);
    }
    
    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "person-address-query-handler");
        
        String address = kafkaProperties.getProperty("address.topic");
        String person = kafkaProperties.getProperty("person.topic");

        logger.log(Level.INFO, "initConsumer - person-address-query-handler group");
        
        
       eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            logger.info("firing = " + ev);
            events.fire(ev);
        }, person,address);

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
