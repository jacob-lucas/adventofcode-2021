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
    public static final Map<Character, Integer> SCORES = ImmutableMap.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137);

    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.read("day10-input.txt");

        // Part 1
        System.out.println(score(lines));
    }

    public static int syntaxCheck(final String line) {
        final List<Character> opens = ImmutableList.of('(', '[', '{', '<');
        final List<Character> closes = ImmutableList.of(')', ']', '}', '>');
        final Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            final char c = line.charAt(i);
            if (opens.contains(c)) {
                stack.push(c);
            } else if (closes.contains(c)) {
                final char top = stack.peek();
                if (closes.indexOf(c) == opens.indexOf(top)) {
                    // matching closing character
                    stack.pop();
                } else {
                    // corrupt stack detected
                    System.out.println(line + " - Expected " + closes.get(opens.indexOf(top)) + ", but found " + c);
                    return i;
                }
            }
        }

        // 0 if valid, -1 if the line is valid up to the end of the string, but hasn't been closed out (i.e. incomplete)
        return stack.isEmpty() ? 0 : -1;
    }

    public static int score(final List<String> lines) {
        final Map<String, Integer> scoresByLine = lines.stream()
                .collect(Collectors.toMap(Function.identity(), Day10::syntaxCheck));

        return scoresByLine.entrySet().stream()
                .mapToInt(e -> e.getValue() > 0 ? SCORES.getOrDefault(e.getKey().charAt(e.getValue()), 0) : 0)
                .sum();
    }
}
