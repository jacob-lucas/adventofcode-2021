package com.jacoblucas.adventofcode2021.day9;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day9-input.txt");
        final int[][] heightmap = parse(input);

        final List<Point> lowPoints = findLowPoints(heightmap);

        // Part 1
        int totalRisk = lowPoints.stream()
                .mapToInt(p -> 1 + heightmap[p.getY()][p.getX()])
                .sum();
        System.out.println(totalRisk);
    }

    public static int[][] parse(List<String> input) {
        int width = input.get(0).length();
        int height = input.size();

        final int[][] heightmap = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                heightmap[y][x] = Integer.parseInt(""+input.get(y).charAt(x));
            }
        }

        return heightmap;
    }

    public static List<Point> findLowPoints(final int[][] heightmap) {
        final List<Point> lowPoints = new ArrayList<>();

        // A low point is one where every neighbouring element is greater than it
        for (int y = 0; y < heightmap.length; y++) {
            for (int x = 0; x < heightmap[y].length; x++) {
                final int value = heightmap[y][x];
                final List<Integer> neighbourValues = new ArrayList<>();

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int neighbourX = x + dx;
                        int neighbourY = y + dy;
                        if (!(Math.abs(dx) + Math.abs(dy) == 1)) {
                            // Ignore diagonals, and the actual x,y coordinate
                            continue;
                        }

                        if (neighbourX >= 0 && neighbourX < heightmap[y].length && neighbourY >= 0 && neighbourY < heightmap.length) {
                            // actually on the heightmap
                            int neighbourValue = heightmap[neighbourY][neighbourX];
                            neighbourValues.add(neighbourValue);
                        }
                    }
                }

                if (neighbourValues.stream().allMatch(v -> v > value)) {
                    lowPoints.add(ImmutablePoint.of(x, y));
                }
            }
        }

        return lowPoints;
    }

    public static int riskLevel(final Point p, final int[][] heightmap) {
        return 1 + heightmap[p.getY()][p.getX()];
    }
}
