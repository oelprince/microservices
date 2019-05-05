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


import com.oelprince.events.entity.PersonAddressInfo;

/**
 *
 * @author oelprince
 */
public class CreditStatus {
    private CreditState state;
    private PersonAddressInfo personAddressInfo;
    
    public void place(final PersonAddressInfo personAddressInfo) {
        state = CreditState.CREATED;
        this.personAddressInfo = personAddressInfo;
    }

    public void accept() {
        state = CreditState.ACCEPTED;
    }

    public void cancel() {
        state = CreditState.CANCELLED;
    }

    public void start() {
        state = CreditState.STARTED;
    }

    public void finish() {
        state = CreditState.FINISHED;
    }

    public void deliver() {
        state = CreditState.DELIVERED;
    }

    public CreditState getState() {
        return state;
    }

    public PersonAddressInfo getPersonAddressInfo() {
        return personAddressInfo;
    }

    public enum CreditState {
        CREATED,
        ACCEPTED,
        STARTED,
        FINISHED,
        DELIVERED,
        CANCELLED
    }
    
    
}
