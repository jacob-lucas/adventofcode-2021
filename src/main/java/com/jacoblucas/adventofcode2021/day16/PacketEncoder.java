package com.jacoblucas.adventofcode2021.day16;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PacketEncoder {

    public static LiteralPacket literalPacket(final long version, final int value) {
        final String versionStr = String.format("%3s", decimalToBinary(version, false)).replace(' ', '0');
        final String packetType = String.format("%3s", decimalToBinary(PacketType.LITERAL.getId(), false)).replace(' ', '0');

        StringBuilder packetString = new StringBuilder();
        final String valueStr = decimalToBinary(value, true);
        for (int i = 0; i < valueStr.length(); i += 4) {
            final boolean more = i + 4 < valueStr.length();
            packetString.append(more ? "1" : "0");
            packetString.append(valueStr, i, i + 4);
        }

        return new LiteralPacket(versionStr + packetType + packetString);
    }

    public static OperatorPacket operatorPacket(
            final long version,
            final PacketType packetType,
            final int lengthTypeId,
            final List<Packet> packets
    ) {
        final String versionStr = String.format("%3s", decimalToBinary(version, false)).replace(' ', '0');
        final String packetTypeStr = String.format("%3s", decimalToBinary(packetType.getId(), false)).replace(' ', '0');
        final String packetString = packets.stream()
                .map(Packet::getBinaryString)
                .collect(Collectors.joining());

        final String lengthStr;
        if (lengthTypeId == 0) {
            lengthStr = String.format("%15s", decimalToBinary(packetString.length(), false)).replace(' ', '0');
        } else {
            lengthStr = String.format("%15s", decimalToBinary(packets.size(), false)).replace(' ', '0');
        }
        return new OperatorPacket(versionStr + packetTypeStr + lengthTypeId + lengthStr + packetString, packets);
    }

    public static String decimalToBinary(final long value, final boolean pad) {
        String str = Long.toBinaryString(value);
        if (pad && str.length() % 4 > 0) {
            final String prefix = IntStream.range(0, 4 - str.length() % 4)
                    .mapToObj(i -> "0")
                    .collect(Collectors.joining());
            return prefix + str;
        } else {
            return str;
        }
    }

    public static String toHex(final Packet packet) {
        String padded = packet.getBinaryString();
        final int mod = padded.length() % 8;
        if (mod > 0) {
            padded = padded + IntStream.range(0, 8 - mod).mapToObj(i -> "0").collect(Collectors.joining());
        }

        final BigInteger bigInteger = new BigInteger(padded, 2);
        return bigInteger.toString(16).toUpperCase();
    }
}
