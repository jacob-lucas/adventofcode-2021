package com.jacoblucas.adventofcode2021.day6;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class School {
    private final Map<Integer, Long> fishMap;

    public School(final String fish) {
        this.fishMap = getLanternfishCountsByTimer(fish);
    }

    public Map<Integer, Long> getMap() {
        return fishMap;
    }

    // Simulate Lanternfish for n days
    public long simulate(final int n) {
        IntStream.range(0, n).forEach(day -> {
            final long zeroes = fishMap.getOrDefault(0, 0L);
            final long sevens = fishMap.getOrDefault(7, 0L);

            // Adjust counts for each possible timer value
            for (int i = 0; i <= 8; i++) {
                if (i == 8) {
                    // Fish in 8 are all the new fish from all the fish who were zero
                    fishMap.put(8, zeroes);
                } else if (i == 6) {
                    // Fish in 6 are from all the new spawns, plus those in zero
                    fishMap.put(6, sevens + zeroes);
                } else {
                    // Fish in every other number are the counts in the number above it
                    fishMap.put(i, fishMap.getOrDefault(i + 1, 0L));
                }
            }
        });

        return fishMap.values().stream().mapToLong(Long::longValue).sum();
    }

    private Map<Integer, Long> getLanternfishCountsByTimer(final String fish) {
        return Arrays.stream(fish.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Long.valueOf(e.getValue().size())));
    }
}
