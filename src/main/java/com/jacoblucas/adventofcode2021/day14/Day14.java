package com.jacoblucas.adventofcode2021.day14;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
            result = pairInsertion(result, rules);
        }

        final Map<Character, Integer> grouped = groupByChar(result);
        final int mostCommon = grouped.values().stream().mapToInt(Integer::valueOf).max().getAsInt();
        final int leastCommon = grouped.values().stream().mapToInt(Integer::valueOf).min().getAsInt();
        System.out.println(mostCommon - leastCommon);
    }

    public static Map<String, String> parseRules(final List<String> strings) {
        final String delim = "->";
        return strings.stream()
                .collect(Collectors.toMap(
                        str -> str.split(delim)[0].trim(),
                        str -> str.split(delim)[1].trim()
                ));
    }

    public static String pairInsertion(final String template, final Map<String, String> rules) {
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

    public static Map<Character, Integer> groupByChar(final String template) {
        return Arrays.stream(ArrayUtils.toObject(template.toCharArray()))
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
