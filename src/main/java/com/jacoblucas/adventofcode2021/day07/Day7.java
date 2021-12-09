package com.jacoblucas.adventofcode2021.day07;

import com.jacoblucas.adventofcode2021.interfaces.IntIntFunction;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day7 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day7-input.txt");
        final Map<Integer, Integer> crabs = parse(input.get(0));

        // Part 1
        int minFuelCost = getMinFuelCost(crabs, Day7::basicCost);
        System.out.println(minFuelCost);

        // Part 2
        minFuelCost = getMinFuelCost(crabs, Day7::complexCost);
        System.out.println(minFuelCost);
    }

    public static Map<Integer, Integer> parse(final String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), e -> e.getValue().size()));
    }

    public static int getMinFuelCost(final Map<Integer, Integer> crabs, final IntIntFunction<Integer> costFunc) {
        int minFuelCost = Integer.MAX_VALUE;
        for (int i=0; i<2000; i++) { // eyeballed the approx max value from the input list
            final int fuelCost = moveCrabsToPosition(new HashMap<>(crabs), i, costFunc);
            if (fuelCost < minFuelCost) {
                minFuelCost = fuelCost;
            }
        }
        return minFuelCost;
    }

    public static int moveCrabsToPosition(
            final Map<Integer, Integer> crabs,
            final int position,
            final IntIntFunction<Integer> costFunc
    ) {
        int fuelCost = 0;
        final List<Integer> positionsToMove = crabs.keySet()
                .stream()
                .filter(pos -> pos != position)
                .collect(Collectors.toList());

        for (final int pos : positionsToMove) {
            final int crabsAtPosition = crabs.getOrDefault(pos, 0);
            crabs.put(position, crabs.getOrDefault(position, 0) + crabsAtPosition);
            crabs.remove(pos);
            final int cost = costFunc.apply(position, pos);
            fuelCost += cost * crabsAtPosition;
        }

        return fuelCost;
    }

    public static int basicCost(int from, int to) {
        return Math.abs(from - to);
    }

    public static int complexCost(int from, int to) {
        return IntStream.range(1, basicCost(from, to) + 1).sum();
    }
}
