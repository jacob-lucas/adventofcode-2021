package com.jacoblucas.adventofcode2021.day13;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FoldTest {

    @Test
    public void testFoldUp() {
        int[][] paper = new int[][] {
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] result1 = ImmutableFold.of('y', 7).fold(paper);
        assertThat(result1.length, is(7));
        assertThat(result1[0].length, is(11));
        assertThat(Arrays.equals(result1[0], new int[]{1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0}), is(true));
        assertThat(Arrays.equals(result1[1], new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}), is(true));
        assertThat(Arrays.equals(result1[2], new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1}), is(true));
        assertThat(Arrays.equals(result1[3], new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}), is(true));
        assertThat(Arrays.equals(result1[4], new int[]{0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1}), is(true));
        assertThat(Arrays.equals(result1[5], new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}), is(true));
        assertThat(Arrays.equals(result1[6], new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}), is(true));
    }

    @Test
    public void testFoldLeft() {
        int[][] paper = new int[][] {
                {1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        paper = ImmutableFold.of('x', 5).fold(paper);

        assertThat(paper.length, is(7));
        assertThat(paper[0].length, is(5));

        assertThat(Arrays.toString(paper[0]), Arrays.equals(paper[0], new int[]{1, 1, 1, 1, 1}), is(true));
        assertThat(Arrays.toString(paper[1]), Arrays.equals(paper[1], new int[]{1, 0, 0, 0, 1}), is(true));
        assertThat(Arrays.toString(paper[2]), Arrays.equals(paper[2], new int[]{1, 0, 0, 0, 1}), is(true));
        assertThat(Arrays.toString(paper[3]), Arrays.equals(paper[3], new int[]{1, 0, 0, 0, 1}), is(true));
        assertThat(Arrays.toString(paper[4]), Arrays.equals(paper[4], new int[]{1, 1, 1, 1, 1}), is(true));
        assertThat(Arrays.toString(paper[5]), Arrays.equals(paper[5], new int[]{0, 0, 0, 0, 0}), is(true));
        assertThat(Arrays.toString(paper[6]), Arrays.equals(paper[6], new int[]{0, 0, 0, 0, 0}), is(true));
    }
}
