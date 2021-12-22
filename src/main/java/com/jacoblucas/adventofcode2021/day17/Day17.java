package com.jacoblucas.adventofcode2021.day17;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

public class Day17 extends SingleListInputProblem {
    @Override
    public void run() {
        // Target area, from puzzle input... easier to hard code vs trying to parse it ;)
        final int minX = 119, maxX = 176, minY = -141, maxY = -84;

        // Part 1
        final int maxHeight = findMaxHeight(minX, maxX, minY, maxY);
        System.out.println(maxHeight);
    }

    public int findMaxHeight(final int minX, final int maxX, final int minY, final int maxY) {
        int i = minX;
        int inc = 0;
        while (i < maxX) {
            inc += 1;
            i += inc;
        }
        int x1 = inc;

        int maxHeight = Integer.MIN_VALUE;

        // Fire using all the feasible candidates for velocities based on the x/y velocity constraints in the problem
        for (int y = minY; y <= (-minY - 1); y++) {
            for (int x = x1; x <= maxX; x++) {
                final ProbeLauncher launcher = new ProbeLauncher();
                final boolean hit = launcher.fire(minX, maxX, minY, maxY, x, y);
                if (hit) {
                    maxHeight = Math.max(launcher.getMaxHeight(), maxHeight);
//                    System.out.println("Hit the target with velocity (x=" + x + ",y=" + y + ") maxHeight=" + maxHeight);
                }
            }
        }

        return maxHeight;
    }

    public static void main(String[] args) {
        new Day17().run();
    }
}
