package com.jacoblucas.adventofcode2021.day9;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Point {
    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();
}
