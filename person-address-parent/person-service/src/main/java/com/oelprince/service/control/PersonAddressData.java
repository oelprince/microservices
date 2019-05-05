/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.service.control;

import com.oelprince.events.entity.AddressUpdated;
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

    public void apply(@Observes CreatePerson event) {
        personAddresses.putIfAbsent(event.getPersonAddressInfo().getPersonId(), new PersonAddressStatus());
        applyFor(event.getPersonAddressInfo().getPersonId(), o -> o.place(event.getPersonAddressInfo()));
    }

//    public void apply(@Observes OrderCancelled event) {
//        applyFor(event.getOrderId(), CoffeeOrder::cancel);
//    }
//
    public void apply(@Observes AddressUpdated event) {
        applyFor(event.getPersonAddressInfo().getPersonId(), PersonAddressStatus::accept);
    }
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
