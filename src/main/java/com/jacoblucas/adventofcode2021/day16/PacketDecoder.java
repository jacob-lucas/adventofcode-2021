package com.jacoblucas.adventofcode2021.day16;

import java.util.ArrayList;
import java.util.List;

public final class PacketDecoder {

    public static Packet decodeHex(final String hex) {
        final String binaryString = hexToBinary(hex);
        return decodeBinary(binaryString);
    }

    public static String hexToBinary(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    public static int binaryToInt(final String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static Packet decodeBinary(final String binaryString) {
        // Grab the header
        final Header header = ImmutableHeader.of(binaryString.substring(0, Header.LENGTH));
        if (header.getPacketType() == PacketType.OPERATOR) {
            return decodeOperator(binaryString);
        } else if (header.getPacketType() == PacketType.LITERAL) {
            return decodeLiteral(binaryString);
        } else {
            throw new IllegalArgumentException("Unexpected packet type detected in header: " + header);
        }
    }

    public static Packet decodeLiteral(final String binaryString) {
        int index = Header.LENGTH;
        int groupCount = 1;
        while (binaryString.charAt(index) != '0') {
            groupCount++;
            index += 5;
        }
        // we now know the header + how many groups
        int length = Header.LENGTH + groupCount * 5;
        return new LiteralPacket(binaryString.substring(0, length));
    }

    public static Packet decodeOperator(String binaryString) {
        final int lengthTypeIdIndex = Header.LENGTH;
        int lengthTypeId = Integer.parseInt("" + binaryString.charAt(lengthTypeIdIndex));
        if (lengthTypeId == 0) {
            // We get told the exact number of bits to decode
            final int bitCountStartIndex = lengthTypeIdIndex + 1;
            final int bitCountEndIndex = bitCountStartIndex + 15;
            final String bitsBinaryString = binaryString.substring(bitCountStartIndex, bitCountEndIndex);
            final int subPacketBits = PacketDecoder.binaryToInt(bitsBinaryString);

            final int packetStartIndex = bitCountEndIndex;
            final int packetEndIndex = packetStartIndex + subPacketBits;
            String packetsBinaryString = binaryString.substring(packetStartIndex, packetEndIndex);

            final List<Packet> packets = new ArrayList<>();
            while (!packetsBinaryString.isEmpty()) {
                final Packet packet = decodeBinary(packetsBinaryString);
                packets.add(packet);
                packetsBinaryString = packetsBinaryString.substring(packet.binaryString.length());
            }
            return new OperatorPacket(binaryString.substring(0, packetEndIndex), packets);
        } else if (lengthTypeId == 1) {
            // We get told the number of packets to decode
            final int bitCountStartIndex = lengthTypeIdIndex + 1;
            final int bitCountEndIndex = bitCountStartIndex + 11;
            final String packetCountBinaryString = binaryString.substring(bitCountStartIndex, bitCountEndIndex);
            final int subPacketBits = PacketDecoder.binaryToInt(packetCountBinaryString);
            String packetsBinaryString = binaryString.substring(bitCountEndIndex); // who knows how long this is?

            final List<Packet> packets = new ArrayList<>();
            for (int i = 0; i < subPacketBits; i++) {
                final Packet packet = decodeBinary(packetsBinaryString);
                packets.add(packet);
                packetsBinaryString = packetsBinaryString.substring(packet.binaryString.length());
            }

            String operatorPacketBinaryString = binaryString.substring(0, 18 + packets.stream().map(Packet::getBinaryString).mapToInt(String::length).sum());
            return new OperatorPacket(operatorPacketBinaryString, packets);
        } else {
            throw new IllegalArgumentException("Unexpected operator packet type detected: " + binaryString);
        }
    }
}
