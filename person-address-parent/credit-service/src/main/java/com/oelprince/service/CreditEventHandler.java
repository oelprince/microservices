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

import com.oelprince.entity.Credit;
import com.oelprince.events.control.EventConsumer;
import com.oelprince.events.entity.CreditVerify;

import com.oelprince.events.entity.PersonAddressEvent;
import java.util.Properties;
import java.util.UUID;
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
public class CreditEventHandler {

    private EventConsumer eventConsumer;

    @Inject
    private CreditCommandService commandService;
    
    @Inject
    private CreditQueryService queryService;

    @Resource
    private ManagedExecutorService mes;

    @Inject
    private Properties kafkaProperties;

    @Inject
    private Event<PersonAddressEvent> events;

    @Inject
    private Logger logger;
    
    
    public void handle(@Observes CreditVerify event) {
        logger.log(Level.INFO, "Handling Credit Verify");
        UUID personId = event.getPersonAddressInfo().getPersonId();
        
        Credit credit = queryService.getCreditByPersonId(personId.toString());
        
        logger.log(Level.INFO, "Credit = {0}", credit.toString());
        
        if(credit == null || credit.getScore() < 600) {
            logger.log(Level.INFO, "Send reject credit ...");
            commandService.rejectCredit(event.getPersonAddressInfo().getPersonId(), 
                    event.getPersonAddressInfo().getAddressId());
        } else {
            logger.log(Level.INFO, "Send accept credit ...");
            commandService.acceptCredit(event.getPersonAddressInfo().getPersonId(), 
                    event.getPersonAddressInfo().getAddressId());
        }
    }
    

    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "credit-handler");
        String address = kafkaProperties.getProperty("address.topic");
        String person = kafkaProperties.getProperty("person.topic");

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
