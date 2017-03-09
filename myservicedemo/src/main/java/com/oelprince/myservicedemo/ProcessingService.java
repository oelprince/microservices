/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.myservicedemo;

import com.oelprince.myservicedemo.orchestration.OrchestrationManager;
import com.oelprince.myservicedemo.orchestration.Producer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author oelprince
 */
@Path("service")
public class ProcessingService {

    @Inject
    private Producer producer;

    @Inject
    private OrchestrationManager orchestration;


    @POST
    @Path("/senditem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendToQueue(@QueryParam("message") final String message) {
        
        String[] messArr = message.split(",");
        System.out.println("queue = " + messArr[0]);        
        System.out.println("item = " + messArr[1]);
        
        orchestration.addQueueToMap(messArr[0]);       
        producer.produce(messArr[1], messArr[0]);       
        return Response.ok("queue=" +  messArr[0]).build();
    }

    @GET
    public void getItem() {
        Logger.getLogger(ProcessingService.class.getName()).log(Level.INFO, "Test...");
    }

}
