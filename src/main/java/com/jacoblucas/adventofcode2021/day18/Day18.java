package com.jacoblucas.adventofcode2021.day18;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

import java.util.stream.Collectors;

public class Day18 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day18-input.txt");

        final SnailfishNumber[] numbers = input.stream()
                .map(SnailfishNumber::parse)
                .collect(Collectors.toList())
                .toArray(new SnailfishNumber[]{});

        // Part 1
        final SnailfishNumber result = Calculator.add(numbers);
        System.out.println(result.getMagnitude());

        // Part 2
        long magnitude = Integer.MIN_VALUE;
        for (final SnailfishNumber a : numbers) {
            for (final SnailfishNumber b : numbers) {
                if (!a.equals(b)) {
                    magnitude = Math.max(magnitude, Calculator.add(a, b).getMagnitude());
                    magnitude = Math.max(magnitude, Calculator.add(b, a).getMagnitude());
                }
            }
        }
        System.out.println(magnitude);
    }

    public static void main(String[] args) {
        new Day18().run();
    }
}
