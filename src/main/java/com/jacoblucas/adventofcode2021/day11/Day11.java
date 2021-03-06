package com.jacoblucas.adventofcode2021.day11;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Day11 {
    public static final int GRID_SIZE = 10;

    // Change to false to print the animations to stdout
    private static final boolean silent = true;

    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.read("day11-input.txt");
        final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        init(grid, lines);

        // Part 1
        int flashCount = 0;
        int i = 0;
        for (; i < 100; i++) {
            flashCount += step(grid);
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Part 2
        boolean allFlashed = false;
        while (!allFlashed) {
            allFlashed = step(grid) == 100;
            i++;
        }

        System.out.println("Part 1: " + flashCount);
        System.out.println("All flashed at step: " + i);
    }

    public static void init(final int[][] grid, final List<String> lines) {
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = Integer.parseInt(""+line.charAt(x));
            }
        }
    }

    public static void print(final int[][] grid) {
        if (!silent) {
            Arrays.stream(grid).forEach(row -> {
                final StringBuilder sb = new StringBuilder();
                sb.append("[ ");
                Arrays.stream(row).forEach(i -> {
                    sb.append(i < 10 ? " " + i : i);
                    sb.append(" ");
                });
                sb.append("]");
                System.out.println(sb);
            });
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                // do nothing
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static int step(final int[][] grid) {
        // 1. Increase by 1
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = grid[y][x] + 1;
            }
        }
        print(grid);

        // 2. Any point > 9 flashes
        final Queue<Point> flashQueue = new ArrayDeque<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] > 9) {
                    // Increase all neighbours by 1
                    flashQueue.add(ImmutablePoint.of(x, y));
                }
            }
        }
        final Set<Point> flashed = new HashSet<>();
        flash(grid, flashQueue, flashed);
        print(grid);

        // 3. Reset those that flashed to 0
        flashed.forEach(p -> grid[p.getY()][p.getX()] = 0);
        print(grid);
        return flashed.size();
    }

    private static void flash(final int[][] grid, final Queue<Point> flashQueue, final Set<Point> flashed) {
        while (!flashQueue.isEmpty()) {
            final Point flashPoint = flashQueue.remove();
            flashed.add(flashPoint);

            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }

                    final int adjX = flashPoint.getX() + dx;
                    final int adjY = flashPoint.getY() + dy;
                    final Point adjacentPoint = ImmutablePoint.of(adjX, adjY);
                    if (!flashed.contains(adjacentPoint) && adjX >= 0 && adjX < GRID_SIZE && adjY >= 0 && adjY < GRID_SIZE) {
                        // Increase by 1
                        grid[adjY][adjX] = grid[adjY][adjX] + 1;
                        if (grid[adjY][adjX] > 9 && !flashQueue.contains(adjacentPoint)) {
                            flashQueue.add(adjacentPoint);
                        }
                    }
                }
            }
        }
    }
}
