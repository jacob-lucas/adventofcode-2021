package com.jacoblucas.adventofcode2021.interfaces;

import org.immutables.value.Value;

@Value.Modifiable
public abstract class Pair<T, U> {
    @Value.Parameter
    public abstract T getFirst();

    @Value.Parameter
    public abstract U getSecond();

    @Override
    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }
}
