package com.jacoblucas.adventofcode2021.day6;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day6-input.txt");

        // Part 1
        final School school = new School(input.get(0));
        long n = school.simulate(80);
        System.out.println(n);

        // Part 2
        n = school.simulate(256 - 80);
        System.out.println(n);
    }
}
