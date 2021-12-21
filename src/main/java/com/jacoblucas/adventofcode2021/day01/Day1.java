package com.jacoblucas.adventofcode2021.day01;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day1-input.txt");
        final List<Integer> depthMeasurements = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int increases = getWindowIncreases(depthMeasurements, 1);
        System.out.println(increases);

        increases = getWindowIncreases(depthMeasurements, 3);
        System.out.println(increases);
    }

    public int getWindowIncreases(final List<Integer> depthMeasurements, final int windowSize) {
        int last = -1;
        int windowIncreases = 0;
        for (int i = windowSize-1; i < depthMeasurements.size(); i++) {
            final int windowSum = depthMeasurements.subList(i - (windowSize-1), i+1)
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .sum();

            if (windowSum > last && last > 0) {
                windowIncreases++;
            }
            last = windowSum;
        }
        return windowIncreases;
    }

    public static void main(String[] args) throws IOException {
        new Day1().run();
    }
}
