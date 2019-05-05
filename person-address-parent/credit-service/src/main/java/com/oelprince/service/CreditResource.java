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
import java.net.URI;
import java.util.UUID;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author oelprince
 */
@Path("credit")
public class CreditResource {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private CreditCommandService commandService;
    
    @Inject
    private CreditQueryService queryService;
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response createCredit(Credit credit) {
        final UUID creditId = UUID.randomUUID();
        credit.setId(creditId.toString());

        commandService.createCredit(credit);

        final URI uri = uriInfo.getRequestUriBuilder().path(CreditResource.class, "getCredit").build(creditId);

        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    
    
    @POST
    @Path("/update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response updateCredit(Credit credit) {

        commandService.updateCredit(credit);

        final URI uri = uriInfo.getRequestUriBuilder().path(CreditResource.class, "getCredit").build(credit.getId());

        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    

    /**
     * 
     * @param creditId
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credit getCredit(@PathParam("id") String creditId) {
        Credit credit = queryService.getCredit(creditId);
        
        if(credit == null) {
            throw new NotFoundException();
        }
        
        return credit;
    }
    
    /**
     * 
     * @param creditId
     * @return 
     */
    @GET
    @Path("/remove/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Credit removeCredit(@PathParam("id") String creditId) {
        Credit credit = queryService.getCredit(creditId);
        
        if(credit == null) {
            throw new NotFoundException();
        }
        
        commandService.removeCredit(credit);
        
        return credit;
    }
    
}
