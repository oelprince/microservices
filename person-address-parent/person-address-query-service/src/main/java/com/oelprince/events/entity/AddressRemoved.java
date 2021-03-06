/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.events.entity;

import java.time.Instant;
import javax.json.JsonObject;

/**
 *
 * @author oelprince
 */
public class AddressRemoved extends PersonAddressEvent {
    private final PersonAddressInfo personAddressInfo;

    public AddressRemoved(final PersonAddressInfo personInfo) {
        this.personAddressInfo = personInfo;
    }
    
    public AddressRemoved(final PersonAddressInfo personAddressInfo, Instant instant) {
        super(instant);
        this.personAddressInfo = personAddressInfo;
    }

    public AddressRemoved(JsonObject jsonObject) {
        this(new PersonAddressInfo(jsonObject.getJsonObject("personAddressInfo")), Instant.parse(jsonObject.getString("instant")));
    }

    public PersonAddressInfo getPersonAddressInfo() {
        return personAddressInfo;
    }
}
