package com.jacoblucas.adventofcode2021.day16;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum PacketType {
    SUM(0, ps -> ps.stream().mapToLong(Packet::get).sum()),
    PRODUCT(1, ps -> ps.stream().mapToLong(Packet::get).reduce((a, b) -> a * b).getAsLong()),
    MINIMUM(2, ps -> ps.stream().mapToLong(Packet::get).min().getAsLong()),
    MAXIMUM(3, ps -> ps.stream().mapToLong(Packet::get).max().getAsLong()),
    LITERAL(4, ps -> ps.get(0).get()),
    GREATER(5, ps -> ps.get(0).get() > ps.get(1).get() ? 1L : 0L),
    LESS(6, ps -> ps.get(0).get() < ps.get(1).get() ? 1L : 0L),
    EQUALS(7, ps -> ps.get(0).get() == ps.get(1).get() ? 1L : 0L);

    private final int id;

    private final Function<List<Packet>, Long> operation;

    PacketType(int id, Function<List<Packet>, Long> operation) {
        this.id = id;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public Function<List<Packet>, Long> getOperation() {
        return operation;
    }

    public boolean isOperator() {
        return this != LITERAL;
    }

    public static PacketType byId(final int id) {
        return Arrays.stream(PacketType.values())
                .filter(pt -> pt.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid PacketType: " + id));
    }
}
