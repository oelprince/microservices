package com.oelprince.facade;

import com.oelprince.entity.PersonAddress;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author oelprince
 */
@Local
public interface PersonAddressFacadeLocal {

    void create(PersonAddress person);

    void edit(PersonAddress person);

    void remove(PersonAddress person);

    PersonAddress find(Object id);

    List<PersonAddress> findAll();

    List<PersonAddress> findRange(int[] range);

    int count();
    
    List<PersonAddress> getPersonAddress(String personId);
    
    List<PersonAddress> getPersonAddress();
    
    PersonAddress getPersonAddress(String personId, String addressId);
    
}
