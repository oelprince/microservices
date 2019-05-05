package com.oelprince.service;

import com.oelprince.entity.Person;
import com.oelprince.facade.PersonFacadeLocal;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@ApplicationScoped
public class PersonQueryService {
    
    @Inject 
    private PersonFacadeLocal personFacade;
    
    public Person getPerson(final String personId) {
        return personFacade.find(personId);
    }
    
    public int getCount() {
        return personFacade.count();
    }
    
}
