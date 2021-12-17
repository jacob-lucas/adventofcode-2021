package com.jacoblucas.adventofcode2021.day16;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PacketTest {
    private Packet packet;

    @Before
    public void setUp() {
        packet = new LiteralPacket("110100101111111000101000");
    }

    @Test
    public void testGetHeader() {
        assertThat(packet.getHeader(), is(ImmutableHeader.of("110100")));
    }

    @Test
    public void testGetPacketString() {
        assertThat(packet.getPacketString(), is("101111111000101000"));
    }

    @Test
    public void testEquals() {
        Packet p1 = new LiteralPacket("110100101111111000101000");
        Packet p2 = new LiteralPacket("110100101111111000101000");
        assertThat(p1, is(p2));
    }

    @Test
    public void testHashcode() {
        final Map<Packet, Integer> testMap = new HashMap<>();
        Packet p1 = new LiteralPacket("110100101111111000101000");
        Packet p2 = new LiteralPacket("110100101111111000101000");

        testMap.put(p1, 1);
        assertThat(testMap.get(p2), is(1));
    }
}
