package com.oelprince.facade;

import com.oelprince.entity.Credit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oelprince
 */
@Stateless
public class CreditFacade extends AbstractFacade<Credit> implements CreditFacadeLocal {

    @PersistenceContext(unitName = "com.oelprince_credit_service_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditFacade() {
        super(Credit.class);
    }
    
    @Override
    public Credit findCreditByPersonId(String personId) {
        List<Credit> credits = em.createNamedQuery("Credit.findByPersonId")
                .setParameter("personId", personId).getResultList();
        
        return credits.get(0);
    }
    
}
