package com.jacoblucas.adventofcode2021.day25;

import java.util.Arrays;
import java.util.Optional;

public enum SeaCucumberType {
    EAST(">"),
    SOUTH("v");

    private final String id;

    SeaCucumberType(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Optional<SeaCucumberType> of (final String id) {
        return Arrays.stream(values())
                .filter(v -> v.id.equals(id))
                .findFirst();
    }
}
