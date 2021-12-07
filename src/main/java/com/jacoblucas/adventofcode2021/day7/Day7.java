package com.jacoblucas.adventofcode2021.day7;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day7-input.txt");
        final Map<Integer, Integer> crabs = parse(input.get(0));

        // Part 1
        int minFuelCost = Integer.MAX_VALUE;
        for (int i=0; i<2000; i++) { // eyeballed the approx max value from the input list
            final int fuelCost = moveCrabsToPosition(new HashMap<>(crabs), i);
            if (fuelCost < minFuelCost) {
                minFuelCost = fuelCost;
            }
        }
        System.out.println(minFuelCost);
    }

    public static Map<Integer, Integer> parse(final String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), e -> e.getValue().size()));
    }

    public static int moveCrabsToPosition(final Map<Integer, Integer> crabs, final int position) {
        int fuelCost = 0;
        final List<Integer> positionsToMove = crabs.keySet()
                .stream()
                .filter(pos -> pos != position)
                .collect(Collectors.toList());

        for (final int pos : positionsToMove) {
            final int crabsAtPosition = crabs.getOrDefault(pos, 0);
            crabs.put(position, crabs.getOrDefault(position, 0) + crabsAtPosition);
            crabs.remove(pos);
            final int cost = Math.abs(position - pos);
            fuelCost += cost * crabsAtPosition;
        }

        return fuelCost;
    }
}
