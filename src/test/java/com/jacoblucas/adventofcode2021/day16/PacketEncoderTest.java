package com.jacoblucas.adventofcode2021.day16;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PacketEncoderTest {
    @Test
    public void testLiteralPacket() {
        final LiteralPacket literalPacket = PacketEncoder.literalPacket(6, 2021);
        assertThat(literalPacket.getBinaryString(), is("110100101111111000101"));

        final LiteralPacket ten = PacketEncoder.literalPacket(6, 10);
        assertThat(ten.getBinaryString(), is("11010001010"));

        final LiteralPacket twenty = PacketEncoder.literalPacket(2, 20);
        assertThat(twenty.getBinaryString(), is("0101001000100100"));
    }

    @Test
    public void testOperatorPacket() {
        final OperatorPacket operatorPacket = PacketEncoder.operatorPacket(1, PacketType.LESS, 0, ImmutableList.of(
                PacketEncoder.literalPacket(6, 10),
                PacketEncoder.literalPacket(2, 20)));

        assertThat(operatorPacket.getBinaryString(), is("0011100000000000011011110100010100101001000100100"));
    }

    @Test
    public void testDecimalToBinary() {
        assertThat(PacketEncoder.decimalToBinary(2021, true), is("011111100101"));
        assertThat(PacketEncoder.decimalToBinary(10, true), is("1010"));
    }

    @Test
    public void testBinaryToHex() {
        final OperatorPacket operatorPacket = PacketEncoder.operatorPacket(1, PacketType.LESS, 0, ImmutableList.of(
                PacketEncoder.literalPacket(6, 10),
                PacketEncoder.literalPacket(2, 20)));

        assertThat(PacketEncoder.toHex(operatorPacket), is("38006F45291200"));
    }
}
