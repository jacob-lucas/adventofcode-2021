package com.jacoblucas.adventofcode2021.day6;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day6-input.txt");
        final List<Lanternfish> fish = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .map(Lanternfish::new)
                .collect(Collectors.toList());

        final School school = new School(fish);
        final int n = school.simulate(80);
        System.out.println(n);
    }
}
