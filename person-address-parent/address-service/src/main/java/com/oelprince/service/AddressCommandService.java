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

import com.oelprince.entity.Address;
import com.oelprince.events.control.EventProducer;
import com.oelprince.events.entity.AddressRemoved;
import com.oelprince.events.entity.AddressUpdated;
import com.oelprince.events.entity.CreateAddress;
import com.oelprince.events.entity.CreditVerify;
import com.oelprince.events.entity.PersonAddressInfo;
import com.oelprince.facade.AddressFacadeLocal;
import com.oelprince.service.control.PersonAddressData;
import com.oelprince.service.control.PersonAddressStatus;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@ApplicationScoped
public class AddressCommandService {
    
    @Inject
    private EventProducer eventProducer;
    
    @Inject 
    private AddressFacadeLocal addressFacade;
    
    @Inject
    private PersonAddressData personAddressData;
    
    @Inject
    private Logger logger;
    
    
    public void createAddress(final Address address) {
        eventProducer.publish(new CreateAddress(new PersonAddressInfo(
                address.getPersonId() == null ? UUID.randomUUID() : 
                        UUID.fromString(address.getPersonId()),UUID.fromString(address.getId()))));
        
        addressFacade.create(address);
    }
    
    public void updateAddressAndSendit(final Address address) {
        //TODO: Error handling and publish compancating events.
        
        logger.log(Level.INFO, "address update and verify credit");
        
        addressFacade.edit(address);
        if(address.getPersonId() != null) {
            //eventProducer.publish(new AddressUpdated(new PersonAddressInfo(UUID.fromString(address.getPersonId()),UUID.fromString(address.getId()))));
            eventProducer.publish(new CreditVerify(new PersonAddressInfo(UUID.fromString(address.getPersonId()),UUID.fromString(address.getId()))));
        }
    }
    
    public void updateAddress(final Address address) {
        addressFacade.edit(address);
    }
    
    
    public void removeAddress(final Address address) {
        addressFacade.remove(address);
    }

    public void acceptAddress(UUID addressId) {
        final PersonAddressStatus personAddressStatus = personAddressData.get(addressId);
        if(personAddressStatus != null) {
            personAddressStatus.accept();
        }
    }

    void removePersonFromAddress(Address address) {
        final String personId = address.getPersonId();
        address.setPersonId(null);
        addressFacade.edit(address);
        
        eventProducer.publish(new AddressRemoved(new PersonAddressInfo(UUID.fromString(personId),UUID.fromString(address.getId()))));
    }
}
