package com.jacoblucas.adventofcode2021.day5;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Vent {
    public abstract int getX1();

    public abstract int getY1();

    public abstract int getX2();

    public abstract int getY2();

    @Value.Derived
    public boolean isHorizontal() {
        return getY1() == getY2();
    }

    @Value.Derived
    public boolean isVertical() {
        return getX1() == getX2();
    }

    public static Vent parse(final String str) {
        final String[] parts = str.split(" ");
        final String[] coord1 = parts[0].split(",");
        final String[] coord2 = parts[2].split(",");
        return ImmutableVent.builder()
                .x1(Integer.parseInt(coord1[0]))
                .y1(Integer.parseInt(coord1[1]))
                .x2(Integer.parseInt(coord2[0]))
                .y2(Integer.parseInt(coord2[1]))
                .build();
    }
}
