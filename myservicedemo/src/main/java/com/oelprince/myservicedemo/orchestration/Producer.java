package com.oelprince.myservicedemo.orchestration;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@Singleton
public class Producer {
    
    @Inject
    private OrchestrationManager orchestration;
    
    @PostConstruct
    void init() {
        
    }
    /**
     * produce - produce item to a distributed queue.
     * 
     * @param item
     * @param queueName 
     */
    public void produce(final String item,final String queueName) {
        try {
            BlockingQueue<String> queue = orchestration.getInstance().getQueue(queueName);
            queue.put(item);
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, "Error happened during producing", ex);
        }
    }
    
    
}
