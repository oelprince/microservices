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
import com.oelprince.entity.Person;
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
@Path("person")
public class PersonResource {
    
    @Context
    private UriInfo uriInfo;
    
    @Inject
    private PersonCommandService commandService;
    
    @Inject
    private PersonQueryService queryService;
    
    /**
     *
     * @param person
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response createPerson(Person person) {
        final UUID personId = UUID.randomUUID();
        person.setId(personId.toString());

        commandService.createPerson(person);

        final URI uri = uriInfo.getRequestUriBuilder().path(PersonResource.class, "getPerson").build(personId);

        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    
    /**
     *
     * @param person
     * @return
     */
    @POST
    @Path("/update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response updatePerson(Person person) {
        
        commandService.updatePerson(person);

        final URI uri = uriInfo.getRequestUriBuilder().path(PersonResource.class, "getPerson").build(person.getId());

        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }
    
    
    
    /**
     *
     * @param personId
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person getPerson(@PathParam("id") String personId) {
        Person person = queryService.getPerson(personId);
        
        if(person == null) {
            throw new NotFoundException();
        }
        
        return person;
    }
    
    @GET
    @Path("/remove/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Person removePerson(@PathParam("id") String personId) {
        Person person = queryService.getPerson(personId);
        
        if(person == null) {
            throw new NotFoundException();
        }
        
        commandService.removePerson(person);
        
        return person;
    }
    
}
