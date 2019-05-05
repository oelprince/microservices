/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EventSerializerTest {
    
    @Parameterized.Parameter
    public String expected;

    @Parameterized.Parameter(1)
    public PersonAddressEvent event;

    private EventSerializer cut = new EventSerializer();

    @Test
    public void test() {
        final String actual = new String(cut.serialize(null, event), StandardCharsets.UTF_8);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestData.eventTestData();
    }

}
