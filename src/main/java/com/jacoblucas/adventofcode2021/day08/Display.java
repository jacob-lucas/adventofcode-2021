package com.jacoblucas.adventofcode2021.day08;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Display {
    public static Map<String, Integer> NUMBERS = new HashMap<>();
    public static Map<List<Integer>, Integer> DISPLAY_VALUE_MAP = new HashMap<>();

    //    aaaa (0)
    // b (1)    c (2)
    // b        c
    //    dddd (3)
    // e (4)    f (5)
    // e        f
    //    gggg (6)
    public static List<Character> POSITIONS = ImmutableList.of('a', 'b', 'c', 'd', 'e', 'f', 'g');
    public static List<Integer>   ZERO      = ImmutableList.of( 1,   1,   1,   0,   1,   1,   1);
    public static List<Integer>   ONE       = ImmutableList.of( 0,   0,   1,   0,   0,   1,   0);
    public static List<Integer>   TWO       = ImmutableList.of( 1,   0,   1,   1,   1,   0,   1);
    public static List<Integer>   THREE     = ImmutableList.of( 1,   0,   1,   1,   0,   1,   1);
    public static List<Integer>   FOUR      = ImmutableList.of( 0,   1,   1,   1,   0,   1,   0);
    public static List<Integer>   FIVE      = ImmutableList.of( 1,   1,   0,   1,   0,   1,   1);
    public static List<Integer>   SIX       = ImmutableList.of( 1,   1,   0,   1,   1,   1,   1);
    public static List<Integer>   SEVEN     = ImmutableList.of( 1,   0,   1,   0,   0,   1,   0);
    public static List<Integer>   EIGHT     = ImmutableList.of( 1,   1,   1,   1,   1,   1,   1);
    public static List<Integer>   NINE      = ImmutableList.of( 1,   1,   1,   1,   0,   1,   1);

    static {
        NUMBERS.put("abcefg", 0);  // 6
        NUMBERS.put("cf", 1);      // 2
        NUMBERS.put("acdeg", 2);   // 5
        NUMBERS.put("acdfg", 3);   // 5
        NUMBERS.put("bcdf", 4);    // 4
        NUMBERS.put("abdfg", 5);   // 5
        NUMBERS.put("abdefg", 6);  // 6
        NUMBERS.put("acf", 7);     // 3
        NUMBERS.put("abcdefg", 8); // 7
        NUMBERS.put("acbdfg", 9);  // 5

        DISPLAY_VALUE_MAP.put(ZERO, 0);
        DISPLAY_VALUE_MAP.put(ONE, 1);
        DISPLAY_VALUE_MAP.put(TWO, 2);
        DISPLAY_VALUE_MAP.put(THREE, 3);
        DISPLAY_VALUE_MAP.put(FOUR, 4);
        DISPLAY_VALUE_MAP.put(FIVE, 5);
        DISPLAY_VALUE_MAP.put(SIX, 6);
        DISPLAY_VALUE_MAP.put(SEVEN, 7);
        DISPLAY_VALUE_MAP.put(EIGHT, 8);
        DISPLAY_VALUE_MAP.put(NINE, 9);
    }

    public static List<String> print(final int n) {
        final String str = String.valueOf(n);
        final List<List<String>> digitStrings = new ArrayList<>();
        for (char c : str.toCharArray()) {
            final int i = Integer.parseInt(""+c);
            final List<Integer> digitList = DISPLAY_VALUE_MAP.entrySet().stream()
                    .filter(e -> e.getValue() == i)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(ImmutableList.of());

            digitStrings.add(digitToStringList(digitList));
        }
        return print(digitStrings);
    }

    static List<String> digitToStringList(final List<Integer> digit) {
        final String digitTemplate = " 0000 |1    2|1    2| 3333 |4    5|4    5| 6666 ";
        final String positionsStr = Joiner.on("").join(POSITIONS);
        String result = digitTemplate;
        for (int i = 0; i < 7; i++) {
            final String replacement = digit.get(i) == 1 ? ""+ positionsStr.charAt(i) : ".";
            result = result.replaceAll(""+i, replacement);
        }

        return Arrays.asList(result.split("\\|"));
    }

    private static List<String> print(final List<List<String>> digitStrings) {
        final int digitHeight = 7;
        final String spacing = "  ";

        final List<String> result = new ArrayList<>();
        for (int i = 0; i < digitHeight; i++) {
            final StringBuilder sb = new StringBuilder();
            for (final List<String> digitString : digitStrings) {
                sb.append(digitString.get(i));
                sb.append(spacing);
            }
            result.add(sb.toString());
        }

        System.out.println("------------------------------");
        result.forEach(System.out::println);
        System.out.println("------------------------------\n");
        return result;
    }

    static List<Integer> toIntegerList(final int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}
