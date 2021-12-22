package com.jacoblucas.adventofcode2021.day22;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.interfaces.Coordinate3D;
import com.jacoblucas.adventofcode2021.interfaces.ImmutableCoordinate3D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactorCore {
    private final Map<Coordinate3D, CubeState> grid;

    public ReactorCore() {
        grid = new HashMap<>();
    }

    public void reboot(final List<RebootStep> rebootStepList) {
        rebootStepList.forEach(this::execute);
    }

    void execute(final RebootStep rebootStep) {
        final Cuboid cuboid = rebootStep.getCuboid();
        if (insideInitRange(cuboid)) {
            System.out.println("Executing: " + rebootStep);
            final CubeState cubeState = rebootStep.getState();
            cuboid.getCubes().forEach(cube -> grid.put(cube, cubeState));
        }
    }

    private boolean insideInitRange(final Cuboid cube) {
        final List<Integer> lowerBounds = ImmutableList.of(
                cube.getX().getFirst(),
                cube.getY().getFirst(),
                cube.getZ().getFirst());

        final List<Integer> upperBounds = ImmutableList.of(
                cube.getX().getFirst(),
                cube.getY().getFirst(),
                cube.getZ().getFirst());

        return lowerBounds.stream().allMatch(i -> i >= -50) && upperBounds.stream().allMatch(i -> i <= 50);
    }

    public CubeState get(final int x, final int y, final int z) {
        return grid.getOrDefault(ImmutableCoordinate3D.of(x,y,z), CubeState.OFF);
    }

    public long count(final CubeState cubeState) {
        return grid.values().stream()
                .filter(cs -> cs == cubeState)
                .count();
    }
}
