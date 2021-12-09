package com.jacoblucas.adventofcode2021.day8;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jacoblucas.adventofcode2021.day8.Display.DISPLAY_VALUE_MAP;
import static com.jacoblucas.adventofcode2021.day8.Display.POSITIONS;

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

    public static Map<String, String> parseInput(final List<String> input) {
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
        final List<Integer> boxed = POSITIONS.stream()
                .mapToInt(c -> (int) c)
                .boxed()
                .collect(Collectors.toList());

        final List<String> patterns = Arrays.stream(signalPatterns.split(" ")).collect(Collectors.toList());
        final Collection<List<Integer>> permutations = Collections2.permutations(boxed);

        // One of these must have success in decoding everything!
        final List<Integer> intListKey = permutations.stream()
                .filter(key -> patterns.stream()
                        .mapToInt(p -> decode(p, buildCharacterKey(key)))
                        .noneMatch(i -> i == -1))
                .findFirst()
                .orElse(ImmutableList.of());

        final List<Character> key = buildCharacterKey(intListKey);

        return patterns.stream()
                .collect(Collectors.toMap(Day8::sortString, p -> decode(p, key)));
    }

    /**
     * Decodes a string by forming an integer array of 0s and 1s against the provided key.
     * This int array will be matched against the intended int arrays (by index) for each number
     * formed on the display to determine its integer value.
     * @param str the input string to decode, e.g. "acf".
     * @param key the char array key with which to match against the input string, e.g. {'a', 'b', 'c', 'd', 'e', 'f', 'g'}
     * @return the display number represented by this string, as decoded by this key, or -1 if it could not be decoded.
     */
    public static int decode(final String str, final List<Character> key) {
        int[] result = new int[7];

        // Turn the string into an integer array, based on the provided key and the indices of the characters in the key
        for (final char c : str.toCharArray()) {
            for (int i = 0; i < 7; i++) {
                if (key.get(i) == c) {
                    result[i] = 1;
                }
            }
        }

        // If the resultant array matches any of the numbers, we can successfully decode with this key.
        return DISPLAY_VALUE_MAP.getOrDefault(Display.toIntegerList(result), -1);
    }

    // For a string of output patterns, decode them against the provided map, and return their result.
    // The result is an integer formed by the interpreted string value of all the output numbers concatenated.
    // E.g. ("cdfeb fcadb cdfeb cdbaf", <map>) -> 5353
    public static int decodeOutput(final String outputPatterns, final Map<String, Integer> decoded) {
        final String result = Arrays.stream(outputPatterns.split(" "))
                .map(pattern -> decoded.getOrDefault(sortString(pattern), 0))
                .map(String::valueOf)
                .collect(Collectors.joining());
        int n = Integer.parseInt(result);
        System.out.println(outputPatterns + ":");
        Display.print(n);
        return n;
    }

    // Takes in a map of strings (signal patterns -> output), decodes them, and returns the sum of the result values.
    public static int getOutputSum(final Map<String, String> signalPatterns) {
        return signalPatterns.entrySet().stream()
                .mapToInt(e -> decodeOutput(e.getValue(), decode(e.getKey())))
                .sum();
    }

    private static List<Character> buildCharacterKey(final List<Integer> ints) {
        final List<Character> key = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            key.add((char) ints.get(i).intValue());
        }
        return key;
    }
}
