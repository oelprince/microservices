package com.oelprince.facade;

import com.oelprince.entity.PersonAddress;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oelprince
 */
@Stateless
public class PersonAddressFacade extends AbstractFacade<PersonAddress> implements PersonAddressFacadeLocal {

    @PersistenceContext(unitName = "com.oelprince_person_address_service_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<PersonAddress> getPersonAddress(String personId) {
        
        List<PersonAddress> personAddresses = em.createNamedQuery("PersonAddress.findByPersonId")
                .setParameter("personId", personId).getResultList();
        
        return personAddresses;
    }
    
    @Override
    public List<PersonAddress> getPersonAddress() {
        
        List<PersonAddress> personAddresses = em.createNamedQuery("PersonAddress.findAll").getResultList();
        
        return personAddresses;
    }
    
    @Override
    public PersonAddress getPersonAddress(String personId, String addressId) {
        
        List<PersonAddress> personAddress = em.createNamedQuery("PersonAddress.findByPersonAddressId")
                .setParameter("personId", personId).setParameter("addressId", addressId).getResultList();
        
        if(personAddress == null || personAddress.isEmpty()) {
            return null;
        }
        
        return personAddress.get(0);
    }
    
    

    public PersonAddressFacade() {
        super(PersonAddress.class);
    }
    
}
