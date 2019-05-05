package com.oelprince.events.control;

import com.oelprince.events.entity.PersonAddressEvent;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author oelprince
 */
@RunWith(Parameterized.class)
public class EventDeserializerTest {
     @Parameterized.Parameter
    public String data;

    @Parameterized.Parameter(1)
    public PersonAddressEvent expected;

    private EventDeserializer cut = new EventDeserializer();

    @Test
    public void test() {
        final PersonAddressEvent actual = cut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));
        assertEventEquals(actual, expected);
    }

    private void assertEventEquals(final PersonAddressEvent actual, final PersonAddressEvent expected) {
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestData.eventTestData();
    }
   
}
