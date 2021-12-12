package com.jacoblucas.adventofcode2021.day12;

import java.util.HashSet;
import java.util.Set;

public class Cave {
    private final String id;
    private final Set<Cave> connections;

    public Cave(final String id) {
        this.id = id;
        this.connections = new HashSet<>();
    }

    public void connect(final Cave other) {
        connections.add(other);
    }

    public String getId() {
        return id;
    }

    public Set<Cave> getConnections() {
        return connections;
    }

    public boolean isBig() {
        final char c = id.charAt(0);
        return (""+ c).toUpperCase().equals("" + c);
    }

    @Override
    public String toString() {
        return id;
    }
}
