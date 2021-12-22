package com.jacoblucas.adventofcode2021.day22;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import com.jacoblucas.adventofcode2021.interfaces.Pair;
import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.List;

@Value.Immutable
public abstract class RebootStep {
    @Value.Parameter
    public abstract Cuboid getCuboid();

    @Value.Parameter
    public abstract CubeState getState();

    @Override
    public String toString() {
        return getState().name().toLowerCase() + " " + getCuboid();
    }

    public static RebootStep parse(final String str) {
        final String[] parts = str.split(" ");

        final List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        final CubeState state = parts[0].equals("on") ? CubeState.ON : CubeState.OFF;

        final String[] coordParts = parts[1].split(",");
        for (final String coord : coordParts) {
            final int equalsIndex = coord.indexOf('=');
            final int dotsIndex = coord.indexOf("..");
            ranges.add(ModifiablePair.create(
                    Integer.parseInt(coord.substring(equalsIndex + 1, dotsIndex)),
                    Integer.parseInt(coord.substring(dotsIndex + 2))));
        }

        return ImmutableRebootStep.of(ImmutableCuboid.of(ranges.get(0), ranges.get(1), ranges.get(2)), state);
    }
}
