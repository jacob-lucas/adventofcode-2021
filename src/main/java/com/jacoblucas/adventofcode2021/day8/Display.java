package com.jacoblucas.adventofcode2021.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Display {
    public static Map<String, Integer> NUMBERS = new HashMap<>();

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
    }

    //    aaaa (0)
    // b (1)    c (2)
    // b        c
    //    dddd (3)
    // e (4)    f (5)
    // e        f
    //    gggg (6)
    public static char[] POSITIONS = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    public static int[] ZERO       = { 1,   1,   1,   0,   1,   1,   1 };
    public static int[] ONE        = { 0,   0,   1,   0,   0,   1,   0 };
    public static int[] TWO        = { 1,   0,   1,   1,   1,   0,   1 };
    public static int[] THREE      = { 1,   0,   1,   1,   0,   1,   1 };
    public static int[] FOUR       = { 0,   1,   1,   1,   0,   1,   0 };
    public static int[] FIVE       = { 1,   1,   0,   1,   0,   1,   1 };
    public static int[] SIX        = { 1,   1,   0,   1,   1,   1,   1 };
    public static int[] SEVEN      = { 1,   0,   1,   0,   0,   1,   0 };
    public static int[] EIGHT      = { 1,   1,   1,   1,   1,   1,   1 };
    public static int[] NINE       = { 1,   1,   1,   1,   0,   1,   1 };

    public static List<String> print(final int n) {
        final String str = String.valueOf(n);
        final List<List<String>> digitStrings = new ArrayList<>();
        for (char c : str.toCharArray()) {
            final int i = Integer.parseInt(""+c);
            int[] digitArr = {};
            if (i == 0) {
                digitArr = ZERO;
            } else if (i == 1) {
                digitArr = ONE;
            } else if (i == 2) {
                digitArr = TWO;
            } else if (i == 3) {
                digitArr = THREE;
            } else if (i == 4) {
                digitArr = FOUR;
            } else if (i == 5) {
                digitArr = FIVE;
            } else if (i == 6) {
                digitArr = SIX;
            } else if (i == 7) {
                digitArr = SEVEN;
            } else if (i == 8) {
                digitArr = EIGHT;
            } else if (i == 9) {
                digitArr = NINE;
            }
            digitStrings.add(digitToStringList(digitArr));
        }
        return print(digitStrings);
    }

    static List<String> digitToStringList(final int[] digit) {
        final String digitTemplate = " 0000 |1    2|1    2| 3333 |4    5|4    5| 6666 ";
        final String positionsStr = new String(POSITIONS);
        String result = digitTemplate;
        for (int i = 0; i < 7; i++) {
            final String replacement = digit[i] == 1 ? ""+ positionsStr.charAt(i) : ".";
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
}
