/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oelprince.events.entity;

import java.util.UUID;
import javax.json.JsonObject;

/**
 *
 * @author oelprince
 */
public class PersonAddressInfo {
    private final UUID personId;
    private final UUID addressId;

    public PersonAddressInfo(final UUID personId, final UUID addressId) {
        this.personId = personId;
        this.addressId = addressId;
    }

    public PersonAddressInfo(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("personId")),
                UUID.fromString(jsonObject.getString("addressId")));
    }

    public UUID getPersonId() {
        return this.personId;
    }
    
    public UUID getAddressId() {
        return this.addressId;
    }

   
}
