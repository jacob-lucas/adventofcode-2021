package com.jacoblucas.adventofcode2021.interfaces;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is a functional interface, whose functional method is apply(T, U).
 * @param <T> the type of the first input to the function.
 * @param <U> the type of the second input to the function.
 * @param <R> the type of the result return from the function.
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface Function2<T, U, R> {

    /**
     * Applies this function to the given arguments.
     * @param t - the first function argument.
     * @param u - the second function argument.
     * @return The function result, of type R.
     */
    R apply(T t, U u);
}
