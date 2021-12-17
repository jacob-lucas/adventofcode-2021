package com.jacoblucas.adventofcode2021.day16;

import org.junit.Test;

import static com.jacoblucas.adventofcode2021.day16.PacketType.EQUALS;
import static com.jacoblucas.adventofcode2021.day16.PacketType.GREATER;
import static com.jacoblucas.adventofcode2021.day16.PacketType.LESS;
import static com.jacoblucas.adventofcode2021.day16.PacketType.LITERAL;
import static com.jacoblucas.adventofcode2021.day16.PacketType.MAXIMUM;
import static com.jacoblucas.adventofcode2021.day16.PacketType.MINIMUM;
import static com.jacoblucas.adventofcode2021.day16.PacketType.PRODUCT;
import static com.jacoblucas.adventofcode2021.day16.PacketType.SUM;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PacketTypeTest {
    @Test
    public void testById() {
        assertThat(PacketType.byId(0), is(SUM));
        assertThat(PacketType.byId(1), is(PRODUCT));
        assertThat(PacketType.byId(2), is(MINIMUM));
        assertThat(PacketType.byId(3), is(MAXIMUM));
        assertThat(PacketType.byId(4), is(LITERAL));
        assertThat(PacketType.byId(5), is(GREATER));
        assertThat(PacketType.byId(6), is(LESS));
        assertThat(PacketType.byId(7), is(EQUALS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedId() {
        PacketType.byId(99);
    }

    @Test
    public void testIsOperator() {
        assertThat(SUM.isOperator(), is(true));
        assertThat(PRODUCT.isOperator(), is(true));
        assertThat(MINIMUM.isOperator(), is(true));
        assertThat(MAXIMUM.isOperator(), is(true));
        assertThat(LITERAL.isOperator(), is(false));
        assertThat(GREATER.isOperator(), is(true));
        assertThat(LESS.isOperator(), is(true));
        assertThat(EQUALS.isOperator(), is(true));
    }

    @Test
    public void testSum() {
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("C200B40A82");
        assertThat(SUM.getOperation().apply(packet.getPackets()), is(3L));
    }

    @Test
    public void testProduct() {
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("04005AC33890");
        assertThat(PRODUCT.getOperation().apply(packet.getPackets()), is(54L));
    }

    @Test
    public void testMinimum() {
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("880086C3E88112");
        assertThat(MINIMUM.getOperation().apply(packet.getPackets()), is(7L));
    }

    @Test
    public void testMaximum() {
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("CE00C43D881120");
        assertThat(MAXIMUM.getOperation().apply(packet.getPackets()), is(9L));
    }

    @Test
    public void testGreater() {
        // Contains (5, 15)
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("D8005AC2A8F0");
        assertThat(GREATER.getOperation().apply(packet.getPackets()), is(0L));
        assertThat(LESS.getOperation().apply(packet.getPackets()), is(1L));
    }

    @Test
    public void testLess() {
        // Contains (5, 15)
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("F600BC2D8F");
        assertThat(GREATER.getOperation().apply(packet.getPackets()), is(0L));
        assertThat(LESS.getOperation().apply(packet.getPackets()), is(1L));
    }

    @Test
    public void testEquals() {
        final OperatorPacket packet = (OperatorPacket) PacketDecoder.decodeHex("9C0141080250320F1802104A08");
        assertThat(EQUALS.getOperation().apply(packet.getPackets()), is(1L));
    }
}
