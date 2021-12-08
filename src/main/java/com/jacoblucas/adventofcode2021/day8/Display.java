package com.jacoblucas.adventofcode2021.day8;

import java.util.HashMap;
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
}
