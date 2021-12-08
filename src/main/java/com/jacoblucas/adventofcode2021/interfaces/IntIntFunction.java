package com.jacoblucas.adventofcode2021.interfaces;

/**
 * Represents a function that accepts two int-valued arguments and produces a result of type R.
 * This is a functional interface, whose functional method is apply(int, int).
 * @param <R> the type of the result return from the function.
 * @see java.util.function.IntFunction
 */
@FunctionalInterface
public interface IntIntFunction<R> {

    /**
     * Applies this function to the given arguments.
     * @param value1 - the first integer function argument.
     * @param value2 - the second integer function argument.
     * @return The function result, of type R.
     */
    R apply(int value1, int value2);
}
