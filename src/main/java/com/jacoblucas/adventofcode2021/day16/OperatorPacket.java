package com.jacoblucas.adventofcode2021.day16;

import java.util.List;

public class OperatorPacket extends Packet {
    private final List<Packet> packets;

    public OperatorPacket(final String binaryString, List<Packet> packets) {
        super(binaryString);
        this.packets = packets;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    /**
     * If the length type ID is 0, then the next 15 bits are a number that represents the total length in bits of the
     * sub-packets contained by this packet.
     *
     * If the length type ID is 1, then the next 11 bits are a number that represents the number of sub-packets
     * immediately contained by this packet.
     */
    public int getLengthTypeId() {
        return Integer.parseInt("" + getPacketString().charAt(0));
    }

    @Override
    public int getVersionSum() {
        return getHeader().getVersion() + packets.stream().mapToInt(Packet::getVersionSum).sum();
    }

    @Override
    public long get() {
        final PacketType packetType = getHeader().getPacketType();
        return packetType.getOperation().apply(packets);
    }
}
