package com.jacoblucas.adventofcode2021.day09;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

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

        // Part 2
        final List<Set<Point>> basins = lowPoints.stream()
                .map(lp -> discoverBasin(lp, heightmap))
                .sorted(Comparator.comparing(Set::size))
                .collect(Collectors.toList());

        final int count = basins.size();
        final int size = basins.get(count - 1).size() * basins.get(count - 2).size() * basins.get(count - 3).size();
        System.out.println(size);
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
                final int height = heightmap[y][x];
                final List<Integer> neighbourHeights = new ArrayList<>();

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int neighbourX = x + dx;
                        int neighbourY = y + dy;
                        if (validDirection(dx, dy) && inBounds(neighbourX, neighbourY, heightmap)) {
                            // actually on the heightmap
                            int neighbourHeight = heightmap[neighbourY][neighbourX];
                            neighbourHeights.add(neighbourHeight);
                        }
                    }
                }

                if (neighbourHeights.stream().allMatch(h -> h > height)) {
                    lowPoints.add(ImmutablePoint.of(x, y));
                }
            }
        }

        return lowPoints;
    }

    public static int riskLevel(final Point p, final int[][] heightmap) {
        return 1 + heightmap[p.getY()][p.getX()];
    }

    public static Set<Point> discoverBasin(final Point lowPoint, final int[][] heightmap) {
        // BFS from the low point until we go off the board or encounter 9s
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(lowPoint);
        return discoverBasin(heightmap, queue, new HashSet<>());
    }

    private static Set<Point> discoverBasin(final int[][] heightmap, final Queue<Point> neighbours, final Set<Point> visited) {
        while (!neighbours.isEmpty()) {
            final Point p = neighbours.remove();
            visited.add(p);

            // find all neighbours of p and add to the queue
            int x = p.getX();
            int y = p.getY();
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    int neighbourX = x + dx;
                    int neighbourY = y + dy;
                    if (validDirection(dx, dy) && inBounds(neighbourX, neighbourY, heightmap)) {
                        // found a valid neighbour
                        final Point neighbour = ImmutablePoint.of(neighbourX, neighbourY);
                        if (!visited.contains(neighbour) && heightmap[neighbourY][neighbourX] != 9) {
                            neighbours.add(neighbour);
                        }
                    }
                }
            }
        }

        return visited;
    }

    private static boolean inBounds(int x, int y, int[][] heightmap) {
        return x >= 0 && x < heightmap[0].length && y >= 0 && y < heightmap.length;
    }

    // Valid directions are up,down,left,right... ignore diagonals, and the actual x,y coordinate
    private static boolean validDirection(int dx, int dy) {
        return Math.abs(dx) + Math.abs(dy) == 1;
    }
}
