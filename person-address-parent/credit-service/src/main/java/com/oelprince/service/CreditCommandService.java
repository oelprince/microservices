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


import com.oelprince.entity.Credit;
import com.oelprince.events.control.EventProducer;
import com.oelprince.events.entity.CreditAccept;
import com.oelprince.events.entity.CreditReject;
import com.oelprince.events.entity.PersonAddressInfo;
import com.oelprince.facade.CreditFacadeLocal;
import java.util.UUID;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
public class CreditCommandService {
    
    @Inject
    private EventProducer eventProducer;

    @Inject
    private CreditFacadeLocal creditFacade;
    
    
    public void createCredit(Credit credit) {
        creditFacade.create(credit);
    }
    
    public void updateCredit(Credit credit) {
        creditFacade.edit(credit);
    }

    public void removeCredit(Credit credit) {
        creditFacade.remove(credit); 
    }

    public void rejectCredit(UUID personId, UUID addressId) {
        eventProducer.publish(new CreditReject(new PersonAddressInfo(personId,addressId)));
    }

    public void acceptCredit(UUID personId, UUID addressId) {
        eventProducer.publish(new CreditAccept(new PersonAddressInfo(personId,addressId)));
    }
    
    
    
}
