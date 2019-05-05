/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.service;

import com.oelprince.entity.PersonAddress;
import com.oelprince.facade.PersonAddressFacadeLocal;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@ApplicationScoped
public class PersonAddressCommandService {
    
    @Inject 
    private PersonAddressFacadeLocal personAddressFacade;
    
    public void createPersonAddress(PersonAddress personAddress) {
        
        personAddressFacade.edit(personAddress);
        
    }

}
