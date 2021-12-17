package com.jacoblucas.adventofcode2021.day16;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LiteralPacketTest {
    private LiteralPacket literalPacket;

    @Before
    public void setUp() {
        literalPacket = new LiteralPacket("110100101111111000101000");
    }

    @Test
    public void testGet() {
        assertThat(literalPacket.get(), is(2021));
    }

    @Test
    public void testLongValue() {
        final LiteralPacket longValueLiteralPacket = new LiteralPacket("1111001111111111110011010011100101011110000100");
        assertThat(longValueLiteralPacket.get(), is(4287940036L));
    }
}
