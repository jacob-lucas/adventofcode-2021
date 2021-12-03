package com.jacoblucas.adventofcode2021.day3;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day3-input.txt");

        final int powerConsumption = getPowerConsumption(input);
        System.out.println(powerConsumption);
    }

    public static int getPowerConsumption(final List<String> input) {
        final int gammaRateDecimal = Integer.parseInt(getGammaRate(input), 2);
        final int epsilonRateDecimal = Integer.parseInt(getEpsilonRate(input), 2);

        return gammaRateDecimal * epsilonRateDecimal;
    }

    public static String getGammaRate(final List<String> input) {
        final int bits = input.get(0).length();
        return IntStream.range(0, bits)
                .map(i -> mostCommonBit(input, i))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String getEpsilonRate(final List<String> input) {
        final int bits = input.get(0).length();
        return IntStream.range(0, bits)
                .map(i -> leastCommonBit(input, i))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    public static int mostCommonBit(final List<String> input, final int pos) {
        final Map<String, Integer> counts = getBitCounts(input, pos);
        final String maxKey = Collections.max(counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return Integer.parseInt(maxKey);
    }

    public static int leastCommonBit(final List<String> input, final int pos) {
        final Map<String, Integer> counts = getBitCounts(input, pos);
        final String minKey = Collections.min(counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return Integer.parseInt(minKey);
    }

    private static Map<String, Integer> getBitCounts(List<String> input, int pos) {
        return input.stream()
                .map(str -> "" + str.charAt(pos))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
