package com.jacoblucas.adventofcode2021.day5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Grid {
    private final int[][] gridArray;

    public Grid(final int width, final int height) {
        gridArray = new int[height][width];
    }

    public int valueAt(final int x, final int y) {
        return gridArray[y][x];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int[] row : gridArray) {
            String rowStr = "";
            for (int j = 0; j < gridArray[0].length; j++) {
                final int n = row[j];
                rowStr += n == 0 ? "." : n;
            }
            rowStr += "\n";
            sb.append(rowStr);
        }
        return sb.toString();
    }

    public void track(final List<Vent> vents) {
        vents.forEach(this::track);
    }

    public void track(final Vent vent) {
        final int x1 = vent.getX1();
        final int y1 = vent.getY1();
        final int x2 = vent.getX2();
        final int y2 = vent.getY2();

        int lower;
        int upper;
        if (vent.isHorizontal()) {
            // y values are the same
            if (x1 < x2) {
                lower = x1;
                upper = x2;
            } else {
                lower = x2;
                upper = x1;
            }

            for (int i = lower; i <= upper; i++) {
                int currentGridValue = gridArray[y1][i];
                gridArray[y1][i] = currentGridValue + 1;
            }
        } else if (vent.isVertical()) {
            // x values are the same
            if (y1 < y2) {
                lower = y1;
                upper = y2;
            } else {
                lower = y2;
                upper = y1;
            }

            for (int i = lower; i <= upper; i++) {
                int currentGridValue = gridArray[i][x1];
                gridArray[i][x1] = currentGridValue + 1;
            }
        }
    }

    public int countWhere(final Function<Integer, Boolean> func) {
        return (int) Arrays.stream(gridArray)
                .flatMapToInt(IntStream::of)
                .filter(func::apply)
                .count();
    }
}
