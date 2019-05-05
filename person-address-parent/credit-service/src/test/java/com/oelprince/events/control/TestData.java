package com.oelprince.events.control;

import com.oelprince.events.entity.CreditAccept;
import com.oelprince.events.entity.CreditReject;
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
                
                new Object[]{"{\"class\":\"com.oelprince.events.entity.CreditAccept\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"personAddressInfo\":{\"addressId\":\"e4d284f0-2545-4368-ae80-8278c33edf17\",\"personId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}}",
                       new CreditAccept(new PersonAddressInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf17")), Instant.parse("2017-01-18T08:11:21.589Z"))},

                
                new Object[]{"{\"class\":\"com.oelprince.events.entity.CreditReject\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"personAddressInfo\":{\"addressId\":\"e4d284f0-2545-4368-ae80-8278c33edf17\",\"personId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}}",
                       new CreditReject(new PersonAddressInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf17")), Instant.parse("2017-01-18T08:11:21.589Z"))}
  
        );
    }
}
