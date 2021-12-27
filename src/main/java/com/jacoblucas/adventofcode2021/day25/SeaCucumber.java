package com.jacoblucas.adventofcode2021.day25;

import org.immutables.value.Value;

@Value.Immutable
public abstract class SeaCucumber {
    @Value.Parameter
    public abstract SeaCucumberType getType();
}
