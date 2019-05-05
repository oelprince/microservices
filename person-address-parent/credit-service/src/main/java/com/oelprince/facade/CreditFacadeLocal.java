package com.oelprince.facade;

import com.oelprince.entity.Credit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author oelprince
 */
@Local
public interface CreditFacadeLocal {

    void create(Credit credit);

    void edit(Credit credit);

    void remove(Credit credit);

    Credit find(Object id);

    List<Credit> findAll();

    List<Credit> findRange(int[] range);

    int count();
    
    Credit findCreditByPersonId(String personId);
    
}
