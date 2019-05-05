package com.oelprince.service.control;

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

import com.oelprince.events.entity.AddressUpdated;
import com.oelprince.events.entity.CreateAddress;
import com.oelprince.events.entity.CreatePerson;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 *
 * @author oelprince
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PersonAddressData {
    private Map<UUID, PersonAddressStatus> personAddresses = new ConcurrentHashMap<>();
    
    public PersonAddressStatus get(final UUID personId) {
        return personAddresses.get(personId);
    }

    public void apply(@Observes CreateAddress event) {
        personAddresses.putIfAbsent(event.getPersonAddressInfo().getAddressId(), new PersonAddressStatus());
        applyFor(event.getPersonAddressInfo().getAddressId(), o -> o.place(event.getPersonAddressInfo()));
    }

//    public void apply(@Observes OrderCancelled event) {
//        applyFor(event.getOrderId(), CoffeeOrder::cancel);
//    }
//
 
//
//    public void apply(@Observes OrderStarted event) {
//        applyFor(event.getOrderId(), CoffeeOrder::start);
//    }
//
//    public void apply(@Observes OrderFinished event) {
//        applyFor(event.getOrderId(), CoffeeOrder::finish);
//    }
//
//    public void apply(@Observes OrderDelivered event) {
//        applyFor(event.getOrderId(), CoffeeOrder::deliver);
//    }

    private void applyFor(final UUID personId, final Consumer<PersonAddressStatus> consumer) {
        final PersonAddressStatus personAddressStatus = personAddresses.get(personId);
        if (personAddressStatus != null)
            consumer.accept(personAddressStatus);
    }
}
