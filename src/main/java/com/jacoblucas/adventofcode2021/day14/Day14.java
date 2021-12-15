package com.jacoblucas.adventofcode2021.day14;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import com.jacoblucas.adventofcode2021.interfaces.Pair;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day14-input.txt");
        final String template = input.get(0).get(0);
        final Map<String, String> rules = parseRules(input.get(1));

        // Part 1
        String result = template;
        for (int i = 0; i < 10; i++) {
            result = pairInsertionV1(result, rules);
        }
        Map<Character, Integer> grouped = groupByChar(result);
        int mostCommon = grouped.values().stream().mapToInt(Integer::valueOf).max().getAsInt();
        int leastCommon = grouped.values().stream().mapToInt(Integer::valueOf).min().getAsInt();
        System.out.println(mostCommon - leastCommon);

        // Part2
        Map<Character, BigInteger> resultPart2 = pairInsertionV2(template, rules, 40);
        BigInteger mc = resultPart2.values().stream().max(Comparator.naturalOrder()).get();
        BigInteger lc = resultPart2.values().stream().min(Comparator.naturalOrder()).get();
        long delta = mc.subtract(lc).longValue();
        System.out.println(delta);
    }

    public static Map<String, String> parseRules(final List<String> strings) {
        final String delim = "->";
        return strings.stream()
                .collect(Collectors.toMap(
                        str -> str.split(delim)[0].trim(),
                        str -> str.split(delim)[1].trim()
                ));
    }

    public static String pairInsertionV1(final String template, final Map<String, String> rules) {
        final Queue<ModifiablePair<String, Integer>> insertIndices = new ArrayDeque<>();
        for (int i = 0; i < template.length() - 1; i++) {
            final char c1 = template.charAt(i);
            final char c2 = template.charAt(i + 1);
            final String pair = "" + c1 + c2;
            if (rules.containsKey(pair)) {
                final String toInsert = rules.get(pair);
                insertIndices.add(ModifiablePair.create(toInsert, i+1));
            }
        }

        String result = template;
        while (!insertIndices.isEmpty()) {
            final ModifiablePair<String, Integer> next = insertIndices.remove();
            result = result.substring(0, next.getSecond()) + next.getFirst() + result.substring(next.getSecond());

            insertIndices.forEach(pair -> pair.setSecond(pair.getSecond() + 1));
        }

        return result;
    }

    // Since we don't need the resultant string, focus on tracking pairs of elements in the string that are
    // created / destroyed as the insertions happen
    public static Map<Character, BigInteger> pairInsertionV2(
            final String template,
            final Map<String, String> rules,
            final int insertions
    ) {
        // seed the initial pair count map / char count map
        Map<Character, BigInteger> charCounts = new HashMap<>();
        Map<Pair<Character, Character>, BigInteger> pairCountMap = new HashMap<>();
        for (int i = 0; i < template.length() - 1; i++) {
            final char c1 = template.charAt(i);
            final char c2 = template.charAt(i + 1);
            final Pair<Character, Character> pair = ModifiablePair.create(c1, c2);
            pairCountMap.put(pair, pairCountMap.getOrDefault(pair, BigInteger.ZERO).add(BigInteger.ONE));
        }

        for (int i = 0; i < template.length(); i++) {
            charCounts.put(template.charAt(i), charCounts.getOrDefault(template.charAt(i), BigInteger.ZERO).add(BigInteger.ONE));
        }

        for (int i = 0; i < insertions; i++) {
            System.out.println("Iteration: " + (i+1));
            charCounts = pairInsertionV2(charCounts, pairCountMap, rules);
            System.out.println(charCounts);
        }

        return charCounts;
    }

    public static Map<Character, BigInteger> pairInsertionV2(
            Map<Character, BigInteger> charCounts,
            Map<Pair<Character, Character>, BigInteger> pairCountMap,
            final Map<String, String> rules
    ) {
        // 1. Look up each pair in the pair count map in the rules
        // 2. If found, we need to split up that pair
        //      - decrement the count of that pair
        //      - create two new pairs, from each character in the pair, and the result of the map look up
        //      - add those two new pairs (and their respective counts) to the map

        final Map<Pair<Character, Character>, BigInteger> updatedPairCountMap = new HashMap<>(pairCountMap);
        final Set<Pair<Character, Character>> pairs = new HashSet<>(pairCountMap.keySet());
        for (final Pair<Character, Character> pair : pairs) {
            // The count of characters to add (we'll insert this many times for this many occurrences)
            final BigInteger count = pairCountMap.get(pair);

            // decrement the count for the pair we're splitting up
            updatedPairCountMap.put(pair, updatedPairCountMap.get(pair).subtract(count));

            final String element = rules.get("" + pair.getFirst() + pair.getSecond());
            if (element != null) {
                // create two new pairs
                final char elementChar = element.charAt(0);
                final Pair<Character, Character> pair1 = ModifiablePair.create(pair.getFirst(), elementChar);
                final Pair<Character, Character> pair2 = ModifiablePair.create(elementChar, pair.getSecond());

                // add the new pairs
                updatedPairCountMap.put(pair1, updatedPairCountMap.getOrDefault(pair1, BigInteger.ZERO).add(count));
                updatedPairCountMap.put(pair2, updatedPairCountMap.getOrDefault(pair2, BigInteger.ZERO).add(count));

                // count the new char
                charCounts.put(elementChar, charCounts.getOrDefault(elementChar, BigInteger.ZERO).add(count));
            }
        }

        pairCountMap.clear();
        pairCountMap.putAll(updatedPairCountMap);

        return charCounts;
    }

    public static Map<Character, Integer> groupByChar(final String template) {
        return Arrays.stream(ArrayUtils.toObject(template.toCharArray()))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
