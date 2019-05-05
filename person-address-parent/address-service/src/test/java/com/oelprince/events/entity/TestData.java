package com.oelprince.events.entity;

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
import com.oelprince.events.entity.CreatePerson;
import com.oelprince.events.entity.PersonAddressInfo;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author oelprince
 */
public final class TestData {

    private TestData() {
    }

    public static List<Object[]> eventTestData() {
        return Arrays.asList(
                
                new Object[]{"{\"class\":\"com.oelprince.events.entity.CreatePerson\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"personAddressInfo\":{\"addressId\":\"e4d284f0-2545-4368-ae80-8278c33edf17\",\"personId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}}",
                       new CreatePerson(new PersonAddressInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf17")), Instant.parse("2017-01-18T08:11:21.589Z"))},

                
                new Object[]{"{\"class\":\"com.oelprince.events.entity.AddressUpdated\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"personAddressInfo\":{\"addressId\":\"e4d284f0-2545-4368-ae80-8278c33edf17\",\"personId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}}",
                       new AddressUpdated(new PersonAddressInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf17")), Instant.parse("2017-01-18T08:11:21.589Z"))}
  
        );
    }
}
