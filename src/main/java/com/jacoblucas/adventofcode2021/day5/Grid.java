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

        final int dx = x2 == x1 ? 0 : x2 - x1 > 0 ? 1 : -1;
        final int dy = y2 == y1 ? 0 : y2 - y1 > 0 ? 1 : -1;

        if (vent.isHorizontal()) {
            // y values are the same
            int x = x1;
            while (x != x2+dx) {
                track(x, y1);
                x += dx;
            }
        } else if (vent.isVertical()) {
            // x values are the same
            int y = y1;
            while (y != y2+dy) {
                track(x1, y);
                y += dy;
            }
        } else {
            // 45 degree diagonal
            int x = x1;
            int y = y1;
            while (x != x2+dx && y != y2+dy) {
                track(x, y);
                x += dx;
                y += dy;
            }
        }
    }

    private void track(final int x, final int y) {
        int currentGridValue = gridArray[y][x];
        gridArray[y][x] = currentGridValue + 1;
    }

    public int countWhere(final Function<Integer, Boolean> func) {
        return (int) Arrays.stream(gridArray)
                .flatMapToInt(IntStream::of)
                .filter(func::apply)
                .count();
    }
}
