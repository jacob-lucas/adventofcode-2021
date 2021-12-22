package com.jacoblucas.adventofcode2021.interfaces;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Coordinate3D {
    @Value.Parameter
    public abstract int x();

    @Value.Parameter
    public abstract int y();

    @Value.Parameter
    public abstract int z();

    @Override
    public String toString() {
        return "(" + x() + "," + y() + "," + z() + ")";
    }
}
