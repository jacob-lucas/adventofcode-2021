package com.jacoblucas.adventofcode2021.day22;

import com.google.common.base.Preconditions;
import com.jacoblucas.adventofcode2021.interfaces.Coordinate3D;
import com.jacoblucas.adventofcode2021.interfaces.ImmutableCoordinate3D;
import com.jacoblucas.adventofcode2021.interfaces.Pair;
import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * The set of all cubes that have coordinates which fall within ranges for x, y, and z
 */
@Value.Immutable
public abstract class Cuboid {
    @Value.Parameter
    public abstract Pair<Integer, Integer> getX();

    @Value.Parameter
    public abstract Pair<Integer, Integer> getY();

    @Value.Parameter
    public abstract Pair<Integer, Integer> getZ();

    @Value.Lazy
    public List<Coordinate3D> getCubes() {
        final List<Coordinate3D> cubes = new ArrayList<>();
        for (int z = getZ().getFirst(); z <= getZ().getSecond(); z++) {
            for (int y = getY().getFirst(); y <= getY().getSecond(); y++) {
                for (int x = getX().getFirst(); x <= getX().getSecond(); x++) {
                    cubes.add(ImmutableCoordinate3D.of(x, y, z));
                }
            }
        }
        return cubes;
    }

    @Override
    public String toString() {
        return "x=" + getX().getFirst() + ".." + getX().getSecond() + ","
                + "y=" + getY().getFirst() + ".." + getY().getSecond() + ","
                + "z=" + getZ().getFirst() + ".." + getZ().getSecond();
    }

    @Value.Check
    public void check() {
        Preconditions.checkArgument(getX().getFirst() <= getX().getSecond(), "X range error - first value must be <= second value: " + this);
        Preconditions.checkArgument(getY().getFirst() <= getY().getSecond(), "Y range error - first value must be <= second value: " + this);
        Preconditions.checkArgument(getZ().getFirst() <= getZ().getSecond(), "Z range error - first value must be <= second value: " + this);
    }
}
