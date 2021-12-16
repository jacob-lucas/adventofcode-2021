package com.jacoblucas.adventofcode2021.day15;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;
import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15 extends SingleListInputProblem {
    protected int[][] cavern;

    @Override
    public void run() {
        init("day15-input.txt");
        buildCavern();

        // Part 1
        System.out.println(getLowestTotalRisk());

        // Part 2
        cavern = addAllTiles(cavern, 5);
        System.out.println(getLowestTotalRisk());
    }

    public void buildCavern() {
        cavern = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            final String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                cavern[y][x] = Integer.parseInt(""+row.charAt(x));
            }
        }
    }

    public int getLowestTotalRisk() {
        int[][] risks = new int[cavern.length][cavern[0].length];
        for (int[] risk : risks) {
            Arrays.fill(risk, 9999999); // something sufficiently high
        }

        risks[cavern.length - 1][cavern[0].length - 1] = 0;
        buildRiskArray(risks);

        // Print the risks
        for (int y = 0; y < cavern.length; y++) {
            final int yVal = y;
            final List<String> rowRisks = IntStream.range(0, cavern[y].length)
                    .mapToObj(x -> ImmutablePoint.of(x, yVal))
                    .map(p -> String.format("%03d", risks[p.getY()][p.getX()]))
                    .collect(Collectors.toList());
            System.out.println(rowRisks);
        }

        return risks[0][0];
    }

    // Starting from dest, walking our way out to the start, calculate the risk from each position in the cavern to dest.
    // E.g.
    // cavern            risks
    // [1, 1, 6, 3]      [17, 17, 14, 11]
    // [1, 3, 8, 1]      [16, 14, 11, 10]
    // [2, 1, 3, 6]  =>  [14, 13, 10,  4]
    // [3, 6, 9, 4]      [16, 13,  4,  0]
    private void buildRiskArray(final int[][] risks) {
        boolean updateNeeded = true;
        while (updateNeeded) {
            updateNeeded = false;
            for (int y = risks.length - 1; y >= 0; y--) {
                for (int x = risks[y].length - 1; x >= 0; x--) {
                    // Find the minimum risk for this point by looking at computed risks for all the neighbours
                    int min = Integer.MAX_VALUE;
                    final List<Point> neighbours = getNeighbours(ImmutablePoint.of(x, y));
                    for (final Point neighbour : neighbours) {
                        int risk = cavern[neighbour.getY()][neighbour.getX()] + risks[neighbour.getY()][neighbour.getX()];
                        if (risk < min) {
                            min = risk;
                        }
                    }

                    // flag if we need to make any updates based on the new risks detected above
                    int curr = risks[y][x];
                    if (min < curr) {
                        risks[y][x] = min;
                        updateNeeded = true;
                    }
                }
            }
        }
    }

    public List<Point> getNeighbours(final Point point) {
        final List<Point> neighbours = new ArrayList<>();
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int cx = point.getX() + dx;
                int cy = point.getY() + dy;
                final Point p = ImmutablePoint.of(cx, cy);
                if ((Math.abs(dx) + Math.abs(dy)) == 1 && cx >= 0 && cx < cavern[0].length && cy >= 0 && cy < cavern.length) {
                    neighbours.add(p);
                }
            }
        }
        return neighbours;
    }

    public int[][] addAllTiles(final int[][] cavern, final int tiles) {
        int[][] allTiles = new int[tiles * cavern.length][tiles * cavern[0].length];
        for (int y = 0; y < cavern.length; y++) {
            for (int x = 0; x < cavern[0].length; x++) {
                int originalRisk = cavern[y][x]; // from the 1st tile
                for (int j = 0; j < tiles; j++) {
                    for (int i = 0; i < tiles; i++) {
                        int newRisk = originalRisk + i + j;

                        if (newRisk > 9) {
                            newRisk -= 9;
                        }

                        allTiles[y + j * cavern.length][x + i * cavern[0].length] = newRisk;
                    }
                }
            }
        }

        return allTiles;
    }

    public static void main(String[] args) {
        new Day15().run();
    }
}
