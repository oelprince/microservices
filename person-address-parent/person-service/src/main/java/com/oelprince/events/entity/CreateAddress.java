package com.oelprince.events.entity;

import java.time.Instant;
import javax.json.JsonObject;

/**
 *
 * @author oelprince
 */
public class CreateAddress extends PersonAddressEvent {
    private final PersonAddressInfo personAddressInfo;

    public CreateAddress(final PersonAddressInfo personAddressInfo) {
        this.personAddressInfo = personAddressInfo;
    }
    
    public CreateAddress(final PersonAddressInfo personAddressInfo, Instant instant) {
        super(instant);
        this.personAddressInfo = personAddressInfo;
    }

    public CreateAddress(JsonObject jsonObject) {
        this(new PersonAddressInfo(jsonObject.getJsonObject("personAddressInfo")), Instant.parse(jsonObject.getString("instant")));
    }

    public PersonAddressInfo getPersonAddressInfo() {
        return personAddressInfo;
    }
}
