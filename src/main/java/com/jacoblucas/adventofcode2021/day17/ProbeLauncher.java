package com.jacoblucas.adventofcode2021.day17;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import com.jacoblucas.adventofcode2021.interfaces.Pair;

public class ProbeLauncher {
    private final ModifiablePair<Integer, Integer> probePosition;
    private int maxHeight;

    public ProbeLauncher() {
        probePosition = ModifiablePair.create(0,0);
        maxHeight = 0;
    }

    public Pair<Integer, Integer> getProbePosition() {
        return probePosition;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public boolean fire(
            final int x1, final int x2, final int y1, final int y2, // target coordinates
            final int x, final int y // initial velocities
    ) {
        int vx = x;
        int vy = y;
        while (!missed(x1, x2, y1, y2)) {
            Pair<Integer, Integer> result = step(vx, vy);
            vx = result.getFirst();
            vy = result.getSecond();

            // update max height
            if (probePosition.getSecond() > maxHeight) {
                maxHeight = probePosition.getSecond();
            }

            if (withinArea(x1, x2, y1, y2)) {
                return true;
            }
        }

        return false;
    }

    Pair<Integer, Integer> step(final int x, final int y) {
        int px = probePosition.getFirst();
        int py = probePosition.getSecond();

        // 1. The probe's x position increases by its x velocity.
        probePosition.setFirst(px + x);

        // 2. The probe's y position increases by its y velocity.
        probePosition.setSecond(py + y);

        // 3. Due to drag, the probe's x velocity changes by 1 toward the value 0;
        //    That is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0,
        //    or does not change if it is already 0.
        int vx = x;
        if (x > 0) vx = x - 1;
        if (x < 0) vx = x + 1;

        // 4. Due to gravity, the probe's y velocity decreases by 1.
        int vy = y - 1;

        return ModifiablePair.create(vx, vy);
    }

    public boolean withinArea(final int x1, final int x2, final int y1, final int y2) {
        final int px = probePosition.getFirst();
        final int py = probePosition.getSecond();
        return x1 <= px && px <= x2 && y1 <= py && py <= y2;
    }

    public boolean missed(final int x1, final int x2, final int y1, final int y2) {
        // we missed if we're beyond or below the target
        final int px = probePosition.getFirst();
        final int py = probePosition.getSecond();
        return px > x2 || py < y1;
    }
}
