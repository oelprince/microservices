package com.oelprince.myservicedemo.orchestration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
public class Consumer {
    
    
    
    public void consume(final String targetQueue,HazelcastInstance hzInstance) {        
        while(true) {
            try {               
                hzInstance.getQueue(targetQueue);     
                BlockingQueue<String> queue = hzInstance.getQueue(targetQueue);
                String item = queue.take();
                
                Logger.getLogger(Consumer.class.getName()).log(Level.INFO, "Conumer consume..." + item);
                //TimeUnit.SECONDS.sleep(5);                
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
    }

}
