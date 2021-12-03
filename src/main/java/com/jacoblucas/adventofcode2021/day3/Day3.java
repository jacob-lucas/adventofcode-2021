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

        final int lifeSupportRating = getLifeSupportRating(input);
        System.out.println(lifeSupportRating);
    }

    public static int getPowerConsumption(final List<String> input) {
        final int gammaRateDecimal = Integer.parseInt(getGammaRate(input), 2);
        final int epsilonRateDecimal = Integer.parseInt(getEpsilonRate(input), 2);

        return gammaRateDecimal * epsilonRateDecimal;
    }

    public static int getLifeSupportRating(final List<String> input) {
        final int oxygenRating = Integer.parseInt(getOxygenGeneratorRating(input), 2);
        final int co2ScrubberRating = Integer.parseInt(getCO2ScrubberRating(input), 2);

        return oxygenRating * co2ScrubberRating;
    }

    public static String getGammaRate(final List<String> input) {
        return getRate(input, 1);
    }

    public static String getEpsilonRate(final List<String> input) {
        return getRate(input, 0);
    }

    public static String getOxygenGeneratorRating(final List<String> input) {
        return getRating(input, 0, 1);
    }

    public static String getCO2ScrubberRating(final List<String> input) {
        return getRating(input, 0, 0);
    }

    private static String getRate(final List<String> input, final int precedence) {
        final int bits = input.get(0).length();
        return IntStream.range(0, bits)
                .map(i -> precedence == 1 ? mostCommonBit(input, i) : leastCommonBit(input, i))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    private static String getRating(final List<String> input, final int pos, final int precedence) {
        if (input.size() == 1) {
            return input.get(0);
        }

        final Map<String, List<String>> bitsByPosition = input.stream()
                .collect(Collectors.groupingBy(str -> "" + str.charAt(pos)));

        final List<String> zeroes = bitsByPosition.get("0");
        final List<String> ones = bitsByPosition.get("1");
        final int zeroCount = zeroes.size();
        final int oneCount = ones.size();

        if (precedence == 1) {
            return getRating(oneCount >= zeroCount ? ones : zeroes, pos + 1, precedence);
        } else {
            return getRating(oneCount >= zeroCount ? zeroes : ones, pos + 1, precedence);
        }
    }

    static int mostCommonBit(final List<String> input, final int pos) {
        final Map<String, Integer> counts = getBitCounts(input, pos);
        final String maxKey = Collections.max(counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return Integer.parseInt(maxKey);
    }

    static int leastCommonBit(final List<String> input, final int pos) {
        final Map<String, Integer> counts = getBitCounts(input, pos);
        final String minKey = Collections.min(counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return Integer.parseInt(minKey);
    }

    private static Map<String, Integer> getBitCounts(final List<String> input, final int pos) {
        return input.stream()
                .map(str -> "" + str.charAt(pos))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
