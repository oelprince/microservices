package com.oelprince.service;

import com.oelprince.entity.PersonAddress;
import com.oelprince.facade.PersonAddressFacadeLocal;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@ApplicationScoped
public class PersonAddressQueryService {
    
    @Inject 
    private PersonAddressFacadeLocal personAddressFacade;
    
    
    public int getCount() {
        return personAddressFacade.count();
    }

    public List<PersonAddress> getPersonAddress(String personId) {
        return personAddressFacade.getPersonAddress(personId);
    }
    
    public List<PersonAddress> getPersonAddress() {
        return personAddressFacade.getPersonAddress();
    }
    
    public PersonAddress getPersonAddress(String personId, String addressId) {
        return personAddressFacade.getPersonAddress(personId, addressId);
    }
    
    
    
}
