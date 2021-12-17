package com.jacoblucas.adventofcode2021.day16;

import java.util.ArrayList;
import java.util.List;

/**
 * Literal value packets encode a single binary number.
 * To do this, the binary number is padded with leading zeroes until its length is a multiple of four bits, and then
 * it is broken into groups of four bits. Each group is prefixed by a 1 bit except the last group, which is prefixed by a 0 bit.
 */
public class LiteralPacket extends Packet {
    public static final int GROUP_LENGTH = 5;

    public LiteralPacket(String binaryString) {
        super(binaryString);
    }

    @Override
    public int getVersionSum() {
        return getHeader().getVersion();
    }

    @Override
    public long get() {
        final String str = getPacketString();
        final List<String> groups = new ArrayList<>();

        int index = 0;
        boolean hasMoreGroups;
        do {
            String group = str.substring(index, index + GROUP_LENGTH);
            groups.add(group.substring(1));
            index += GROUP_LENGTH;
            hasMoreGroups = group.charAt(0) == '1';
        } while (hasMoreGroups);

        return PacketDecoder.binaryToDecimal(String.join("", groups));
    }
}
