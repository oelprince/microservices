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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author oelprince
 */
@Path("personaddress")
public class PersonAddressResource {
    
    @Context
    private UriInfo uriInfo;
    
   
    
    @Inject
    private PersonAddressQueryService queryService;
    
    @Inject
    Logger logger;
    
    /**
     *
     * @param personId
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PersonAddress> getPersonAddress(@PathParam("id") String personId) {
        List<PersonAddress> personAddresses = queryService.getPersonAddress(personId);
        
        if(personAddresses == null) {
            logger.log(Level.INFO, "personId {0} not found.", personId);
            
            throw new NotFoundException();
        }
        
        return personAddresses;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PersonAddress> getPersonAddress() {
        List<PersonAddress> personAddresses = queryService.getPersonAddress();
        
        if(personAddresses == null) {
            throw new NotFoundException();
        }
        
        return personAddresses;
    }
    
    
}
