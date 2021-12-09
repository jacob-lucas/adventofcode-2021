package com.jacoblucas.adventofcode2021.interfaces;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Point {
    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();
}
