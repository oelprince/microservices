package com.oelprince.service.control;

import com.oelprince.events.entity.PersonAddressInfo;

/**
 *
 * @author oelprince
 */
public class PersonAddressStatus {
    private PersonAddressState state;
    private PersonAddressInfo personAddressInfo;

    public void place(final PersonAddressInfo personAddressInfo) {
        state = PersonAddressState.CREATED;
        this.personAddressInfo = personAddressInfo;
    }

    public void accept() {
        state = PersonAddressState.ACCEPTED;
    }

    public void cancel() {
        state = PersonAddressState.CANCELLED;
    }

    public void start() {
        state = PersonAddressState.STARTED;
    }

    public void finish() {
        state = PersonAddressState.FINISHED;
    }

    public void deliver() {
        state = PersonAddressState.DELIVERED;
    }

    public PersonAddressState getState() {
        return state;
    }

    public PersonAddressInfo getPersonAddressInfo() {
        return personAddressInfo;
    }

    public enum PersonAddressState {
        CREATED,
        ACCEPTED,
        STARTED,
        FINISHED,
        DELIVERED,
        CANCELLED
    }

}
