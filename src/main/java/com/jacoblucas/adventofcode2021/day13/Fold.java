package com.jacoblucas.adventofcode2021.day13;

import org.immutables.value.Value;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

@Value.Immutable
public abstract class Fold {
    @Value.Parameter
    public abstract char getAxis();

    @Value.Parameter
    public abstract int getValue();

    public int[][] fold(final int[][] array) {
        if (getAxis() == 'y') {
            return foldUp(array, getValue());
        } else {
            return foldLeft(array, getValue());
        }
    }

    public int[][] foldUp(final int[][] paper, final int yValue) {
        // Shift around the rows in the array, no need for column shifting

        final List<int[]> result = new ArrayList<>();
        final Queue<int[]> prefix = new ArrayDeque<>();
        int k = 1;

        for (int i = yValue + 1; i < paper.length; i++) {
            final int dest = yValue - k;
            if (dest >= 0) {
                final int[] merged = new int[paper[i].length];
                for (int j = 0; j < merged.length; j++) {
                    merged[j] = paper[i][j] | paper[dest][j];
                }
                result.add(0, merged);
            } else {
                prefix.add(paper[i]);
            }
            k++;
        }

        while (!prefix.isEmpty()) {
            result.add(0, prefix.remove());
        }

        if (yValue - k >= 0) {
            for (int i = yValue - k; i >=0; i--) {
                result.add(0, paper[i]);
            }
        }

        final int[][] folded = new int[result.size()][0];
        for (int i = 0; i < result.size(); i++) {
            folded[i] = result.get(i);
        }
        return folded;
    }

    public int[][] foldLeft(final int[][] paper, final int xValue) {
        // init the new paper
        final int[][] folded = new int[paper.length][0];
        for (int y = 0; y < folded.length; y++) {
            folded[y] = foldLeftArr(paper[y], xValue);
        }
        return folded;
    }

    int[] foldLeftArr(int[] ints, int xValue) {
        final List<Integer> result = new ArrayList<>();

        final Stack<Integer> prefix = new Stack<>();
        int j = 1;
        for (int i = xValue + 1; i < ints.length; i++) {
            final int dest = xValue - j;
            if (dest >= 0) {
                result.add(0, ints[i] | ints[dest]);
            } else {
                prefix.push(ints[i]);
            }
            j++;
        }

        while (!prefix.isEmpty()) {
            result.add(0, prefix.pop());
        }

        if (xValue - j >= 0) {
            for (int i = xValue - j; i >= 0; i--) {
                result.add(0, ints[i]);
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
