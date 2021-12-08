package com.jacoblucas.adventofcode2021.day8;

import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day8-input.txt");
        final Map<String, String> signalPatterns = parseInput(input);

        // Part 1
        final Set<Integer> digits = ImmutableSet.of(1, 4, 7, 8);
        final long count = count(signalPatterns, digits);
        System.out.println(count);
    }

    public static Map<String, String> parseInput(List<String> input) {
        final Map<String, String> signalPatterns = new HashMap<>();
        for (final String pattern : input) {
            final int pipe = pattern.indexOf('|');
            final String signalPattern = pattern.substring(0, pipe).trim();
            final String output = pattern.substring(pipe + 1).trim();
            signalPatterns.put(signalPattern, output);
        }
        return signalPatterns;
    }

    public static long count(final Map<String, String> signalPatterns, final Set<Integer> digits) {
        long n = signalPatterns.values().stream()
                .mapToLong(sp -> count(sp, digits))
                .sum();
        return n;
    }

    public static long count(final String signalPattern, final Set<Integer> digits) {
        final Set<Integer> matchingKeyLengths = Display.NUMBERS.entrySet().stream()
                .filter(e -> digits.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .map(String::length)
                .collect(Collectors.toSet());

        return Arrays.stream(signalPattern.split(" "))
                .map(str -> {
                    final char[] chars = str.toLowerCase().toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                })
                .filter(str -> matchingKeyLengths.contains(str.length()))
                .count();
    }
}
