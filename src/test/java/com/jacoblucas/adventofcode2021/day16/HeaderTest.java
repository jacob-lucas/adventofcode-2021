package com.jacoblucas.adventofcode2021.day16;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HeaderTest {
    private Header header;

    @Before
    public void setUp() {
        header = ImmutableHeader.of("110100");
    }

    @Test
    public void testGetVersion() {
        assertThat(header.getVersion(), is(6));
    }

    @Test
    public void testGetPacketType() {
        assertThat(header.getPacketType(), is(PacketType.LITERAL));
    }
}
