package com.jacoblucas.adventofcode2021.day7;

@FunctionalInterface
public interface IntIntFunction<R> {
    R apply(int value1, int value2);
}
