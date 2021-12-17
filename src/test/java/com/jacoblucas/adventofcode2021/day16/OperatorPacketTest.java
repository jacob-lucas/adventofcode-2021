package com.jacoblucas.adventofcode2021.day16;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OperatorPacketTest {
    private OperatorPacket operatorPacketTypeZero;
    private OperatorPacket operatorPacketTypeOne;

    @Before
    public void setUp() {
        operatorPacketTypeZero = new OperatorPacket("00111000000000000110111101000101001010010001001000000000", ImmutableList.of(
                new LiteralPacket("11010001010"),
                new LiteralPacket("0101001000100100")));
        operatorPacketTypeOne = new OperatorPacket("11101110000000001101010000001100100000100011000001100000", ImmutableList.of(
                new LiteralPacket("0101000000"),
                new LiteralPacket("110010000010"),
                new LiteralPacket("00110000011")));
    }

    @Test
    public void testGetLengthType() {
        assertThat(operatorPacketTypeZero.getLengthTypeId(), is(0));
        assertThat(operatorPacketTypeOne.getLengthTypeId(), is(1));
    }
}
