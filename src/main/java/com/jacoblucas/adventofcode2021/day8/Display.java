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
}
