package com.jacoblucas.adventofcode2021.day18;

import java.util.Arrays;

public class Calculator {

    public static SnailfishNumber add(final SnailfishNumber ...numbers) {
        return Arrays.stream(numbers)
                .reduce(Calculator::add)
                .get();
    }

    public static SnailfishNumber add(final SnailfishNumber left, final SnailfishNumber right) {
//        System.out.println("Adding " + left + " + " + right);
        SnailfishNumber number = new SnailfishNumber(left.copy(), right.copy());

        // Reduce!
        while (explode(number) || split(number));

//        System.out.println("Result = " + number);
        return number;
    }

    static boolean explode(final SnailfishNumber number) {
        int parentCount = number.countParents();
        if (number.isValue()) {
            return false;
        } else if (parentCount != 4) {
            return explode(number.getLeft()) || explode(number.getRight());
        } else {
            // Explode!
            // To explode a pair:
            //  1. the pair's left value is added to the first regular number to the left of the exploding pair (if any),
            //  2. the pair's right value is added to the first regular number to the right of the exploding pair (if any).
            //  3. Exploding pairs will always consist of two regular numbers.
            //  4. Then, the entire exploding pair is replaced with the regular number 0.
            final SnailfishNumber firstLeft = number.getLeft().getFirstNumberLeft();
            if (firstLeft != null) {
                firstLeft.setValue(firstLeft.getValue() + number.getLeft().getValue());
            }

            final SnailfishNumber firstRight = number.getRight().getFirstNumberRight();
            if (firstRight != null) {
                firstRight.setValue(firstRight.getValue() + number.getRight().getValue());
            }

            number.setValue(0);
            return true;
        }
    }

    static boolean split(final SnailfishNumber number) {
        if (!number.isValue()) {
            return split(number.getLeft()) || split(number.getRight());
        } else {
            final int value = number.getValue();
            if (value < 10) {
                return false;
            } else {
                // Split!
                // If any regular number is 10 or greater, the leftmost such regular number splits.
                final int left = Math.floorDiv(value, 2);
                number.setPair(left, value - left);
                return true;
            }
        }
    }
}
