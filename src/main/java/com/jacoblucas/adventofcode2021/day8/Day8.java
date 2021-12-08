package com.jacoblucas.adventofcode2021.day8;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jacoblucas.adventofcode2021.day8.Display.EIGHT;
import static com.jacoblucas.adventofcode2021.day8.Display.FIVE;
import static com.jacoblucas.adventofcode2021.day8.Display.FOUR;
import static com.jacoblucas.adventofcode2021.day8.Display.NINE;
import static com.jacoblucas.adventofcode2021.day8.Display.ONE;
import static com.jacoblucas.adventofcode2021.day8.Display.POSITIONS;
import static com.jacoblucas.adventofcode2021.day8.Display.SEVEN;
import static com.jacoblucas.adventofcode2021.day8.Display.SIX;
import static com.jacoblucas.adventofcode2021.day8.Display.THREE;
import static com.jacoblucas.adventofcode2021.day8.Display.TWO;
import static com.jacoblucas.adventofcode2021.day8.Display.ZERO;

public class Day8 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day8-input.txt");
        final Map<String, String> signalPatterns = parseInput(input);

        // Part 1
        final Set<Integer> digits = ImmutableSet.of(1, 4, 7, 8);
        final long count = count(signalPatterns, digits);
        System.out.println(count);

        // Part 2
        int sum = getOutputSum(signalPatterns);
        System.out.println(sum);
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
        return signalPatterns.values().stream()
                .mapToLong(sp -> count(sp, digits))
                .sum();
    }

    public static long count(final String signalPattern, final Set<Integer> digits) {
        final Set<Integer> matchingKeyLengths = Display.NUMBERS.entrySet().stream()
                .filter(e -> digits.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .map(String::length)
                .collect(Collectors.toSet());

        return Arrays.stream(signalPattern.split(" "))
                .map(Day8::sortString)
                .filter(str -> matchingKeyLengths.contains(str.length()))
                .count();
    }

    static String sortString(String str) {
        final char[] chars = str.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static Map<String, Integer> decode(final String signalPatterns) {
        final List<Integer> boxed = new String(POSITIONS).chars()
                .boxed()
                .collect(Collectors.toList());

        final List<String> patterns = Arrays.stream(signalPatterns.split(" ")).collect(Collectors.toList());
        final Collection<List<Integer>> permutations = Collections2.permutations(boxed);

        // One of these must have success in decoding everything!
        final List<Integer> intListKey = permutations.stream()
                .filter(key -> {
                    char[] keyCharArray = new char[7];
                    for (int i = 0; i < 7; i++) {
                        keyCharArray[i] = (char) key.get(i).intValue();
                    }
                    return patterns.stream()
                            .mapToInt(p -> decode(p, keyCharArray))
                            .noneMatch(i -> i == -1);
                })
                .findFirst()
                .get();

        final char[] key = new char[7];
        for (int i = 0; i < 7; i++) {
            key[i] = (char) intListKey.get(i).intValue();
        }

        return patterns.stream()
                .collect(Collectors.toMap(Day8::sortString, p -> decode(p, key)));
    }

    public static int decode(final String str, final char[] key) {
        int[] result = new int[7];
        for (final char c : str.toCharArray()) {
            for (int i = 0; i < 7; i++) {
                if (key[i] == c) {
                    result[i] = 1;
                }
            }
        }

        if (Arrays.equals(result, ZERO)) {
            return 0;
        } else if (Arrays.equals(result, ONE)) {
            return 1;
        } else if (Arrays.equals(result, TWO)) {
            return 2;
        } else if (Arrays.equals(result, THREE)) {
            return 3;
        } else if (Arrays.equals(result, FOUR)) {
            return 4;
        } else if (Arrays.equals(result, FIVE)) {
            return 5;
        } else if (Arrays.equals(result, SIX)) {
            return 6;
        } else if (Arrays.equals(result, SEVEN)) {
            return 7;
        } else if (Arrays.equals(result, EIGHT)) {
            return 8;
        } else if (Arrays.equals(result, NINE)) {
            return 9;
        }

        return -1;
    }

    public static int decodeOutput(final String outputPattern, final Map<String, Integer> decoded) {
        final List<Integer> vals = Arrays.stream(outputPattern.split(" "))
                .map(val -> decoded.getOrDefault(sortString(val), 0))
                .collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        vals.forEach(sb::append);
        return Integer.parseInt(sb.toString());
    }

    public static int getOutputSum(final Map<String, String> signalPatterns) {
        return signalPatterns.entrySet().stream()
                .mapToInt(e -> {
                    final Map<String, Integer> decoded = decode(e.getKey());
                    final List<Integer> vals = Arrays.stream(e.getValue().split(" "))
                            .map(val -> decoded.getOrDefault(sortString(val), 0))
                            .collect(Collectors.toList());
                    final StringBuilder sb = new StringBuilder();
                    vals.forEach(sb::append);
                    final int result = Integer.parseInt(sb.toString());
                    System.out.println(e.getValue() + ": " + result);
                    return result;
                })
                .sum();
    }
}
