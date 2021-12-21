package com.jacoblucas.adventofcode2021.day21;

public class Die {
    private int value;

    public Die() {
        value = 0;
    }

    public int roll() {
        return ++value > 100 ? value % 100 : value;
    }

    public int get() {
        return value;
    }
}
