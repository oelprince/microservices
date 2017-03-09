/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.myservicedemo.orchestration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.Destroyed;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@Singleton
@Startup
public class OrchestrationManager {
    private HazelcastInstance hz;
    
    ConcurrentMap<String, String> queueMap;

    @PostConstruct
    void init() {
        Logger.getLogger(OrchestrationManager.class.getName()).log(Level.INFO, "starting hazelcast");
        hz = Hazelcast.newHazelcastInstance();
        queueMap = hz.getMap("my-distributed-map");
    }
    
    public HazelcastInstance getInstance() {
        return hz;
    }
    
    public void addQueueToMap(String queue) {   
        
        if (queueMap.get(queue) == null) {
            Logger.getLogger(OrchestrationManager.class.getName()).log(Level.INFO, "Adding new queue");
            
            Thread thread = new Thread(() -> {
                Consumer consumer = new Consumer();
                
                consumer.consume(queue,hz);           
            });

            thread.start();
            queueMap.put(queue, queue);
        } else {
            Logger.getLogger(OrchestrationManager.class.getName()).log(Level.INFO, "Queue already exist");
        }
    }
    
    public String getQueueThreadFromMap(String queue) {
        return queueMap.get(queue);
    }
    
    @PreDestroy
    public void cleanUp() {
       hz.shutdown();
    }
    
}



