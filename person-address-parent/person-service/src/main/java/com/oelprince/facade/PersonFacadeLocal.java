package com.oelprince.facade;

import com.oelprince.entity.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author oelprince
 */
@Local
public interface PersonFacadeLocal {

    void create(Person person);

    void edit(Person person);

    void remove(Person person);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();
    
}
