package com.jacoblucas.adventofcode2021.day16;

import java.util.Arrays;

public enum PacketType {
    OPERATOR(3),
    LITERAL(4);

    private final int id;

    PacketType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PacketType byId(final int id) {
        return Arrays.stream(PacketType.values())
                .filter(pt -> pt.id == id)
                .findFirst()
                .orElse(OPERATOR);  // default to this for now
    }
}
