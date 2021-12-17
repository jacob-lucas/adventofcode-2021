package com.jacoblucas.adventofcode2021.day16;

public abstract class Packet {
    protected String binaryString;

    public Packet(final String binaryString) {
        this.binaryString = binaryString;
    }

    public String getBinaryString() {
        return binaryString;
    }

    public Header getHeader() {
        return ImmutableHeader.of(getBinaryString().substring(0, Header.LENGTH));
    }

    public String getPacketString() {
        return getBinaryString().substring(Header.LENGTH);
    }

    public abstract int getVersionSum();
}
