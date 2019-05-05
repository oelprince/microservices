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
import java.net.URI;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
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
@Path("address")
@RequestScoped
public class AddressResource {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private AddressCommandService commandService;
    
    @Inject
    private AddressQueryService queryService;
    
    /**
     *
     * @param address
     * 
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response createAddress(Address address) {
        final UUID addressId = UUID.randomUUID();
        address.setId(addressId.toString());
        commandService.createAddress(address);
        final URI uri = uriInfo.getRequestUriBuilder().path(AddressResource.class, "getAddress").build(addressId);
        
        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    
    
    /**
     *
     * @param address
     * 
     * @return
     */
    @POST
    @Path("/update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response updateAddress(Address address) {
        
        commandService.updateAddressAndSendit(address);
        
        final URI uri = uriInfo.getRequestUriBuilder().path(AddressResource.class, "getAddress").build(address.getId());
        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    
    /**
     *
     * @param addressId
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Address getAddress(@PathParam("id") String addressId) {
        Address address = queryService.getAddress(addressId);
        
        if(address == null) {
            throw new NotFoundException();
        }
        
        return address;
    }
    
    @GET
    @Path("/remove/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Address removeAddress(@PathParam("id") String addressId) {
        Address address = queryService.getAddress(addressId);
        
        if(address == null) {
            throw new NotFoundException();
        }
        
        commandService.removeAddress(address);
        
        return address;
    }
    
}
