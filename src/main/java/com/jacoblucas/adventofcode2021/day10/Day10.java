package com.jacoblucas.adventofcode2021.day10;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day10 {
    public static final List<Character> OPENS = ImmutableList.of('(', '[', '{', '<');
    public static final List<Character> CLOSES = ImmutableList.of(')', ']', '}', '>');
    public static final Map<Character, Integer> SYNTAX_CHECK_SCORES = ImmutableMap.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137);
    public static final Map<Character, Integer> AUTOCOMPLETE_SCORES = ImmutableMap.of(
            ')', 1,
            ']', 2,
            '}', 3,
            '>', 4);

    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.read("day10-input.txt");

        // Part 1
        System.out.println(scoreSyntaxCheck(lines));

        // Part 2
        final List<String> incomplete = lines.stream()
                .filter(line -> syntaxCheck(line) < 0)
                .collect(Collectors.toList());
        System.out.println(scoreAutoComplete(incomplete));
    }

    public static int syntaxCheck(final String line) {
        final Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            final char c = line.charAt(i);
            if (OPENS.contains(c)) {
                stack.push(c);
            } else if (CLOSES.contains(c)) {
                final char top = stack.peek();
                if (CLOSES.indexOf(c) == OPENS.indexOf(top)) {
                    // matching closing character
                    stack.pop();
                } else {
                    // corrupt stack detected
                    System.out.println(line + " - Expected " + CLOSES.get(OPENS.indexOf(top)) + ", but found " + c);
                    return i;
                }
            }
        }

        // 0 if valid, -1 if the line is valid up to the end of the string, but hasn't been closed out (i.e. incomplete)
        return stack.isEmpty() ? 0 : -1;
    }

    public static int scoreSyntaxCheck(final List<String> lines) {
        final Map<String, Integer> scoresByLine = lines.stream()
                .collect(Collectors.toMap(Function.identity(), Day10::syntaxCheck));

        return scoresByLine.entrySet().stream()
                .mapToInt(e -> e.getValue() > 0 ? SYNTAX_CHECK_SCORES.getOrDefault(e.getKey().charAt(e.getValue()), 0) : 0)
                .sum();
    }

    public static String autoComplete(final String incomplete) {
        final Stack<Character> stack = new Stack<>();

        for (int i = 0; i < incomplete.length(); i++) {
            final char c = incomplete.charAt(i);
            if (OPENS.contains(c)) {
                stack.push(c);
            } else if (CLOSES.contains(c)) {
                final char top = stack.peek();
                if (CLOSES.indexOf(c) == OPENS.indexOf(top)) {
                    // matching closing character
                    stack.pop();
                }
            }
        }

        // we can assume that what's left in the stack needs to be paired up for the autocompletion
        final StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            final char top = stack.pop();
            sb.append(CLOSES.get(OPENS.indexOf(top)));
        }

        return sb.toString();
    }

    public static long scoreAutoComplete(final List<String> lines) {
        final List<Long> scores = lines.stream()
                .map(Day10::autoComplete)
                .map(Day10::scoreAutoComplete)
                .sorted()
                .collect(Collectors.toList());

        // always an odd length, take the middle number
        return scores.get((scores.size() - 1) / 2);
    }

    public static long scoreAutoComplete(final String completionStr) {
        long score = 0;
        for (int i = 0; i < completionStr.length(); i++) {
            char c = completionStr.charAt(i);
            score *= 5;
            score += AUTOCOMPLETE_SCORES.get(c);
        }
        return score;
    }
}
