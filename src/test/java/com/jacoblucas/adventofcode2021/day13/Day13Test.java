package com.jacoblucas.adventofcode2021.day13;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class Day13Test {
    private List<Point> dots;

    @Before
    public void setUp() throws IOException {
        final List<List<String>> input = InputReader.readGroups("src/test/resources/", "day13-test-input.txt");
        dots = Day13.parseDots(input.get(0));
    }

    @Test
    public void testParseDots() {
        final List<Point> dots = Day13.parseDots(ImmutableList.of(
                "6,10",
                "0,14",
                "1,2"));

        assertThat(dots, containsInAnyOrder(
                ImmutablePoint.of(6, 10),
                ImmutablePoint.of(0, 14),
                ImmutablePoint.of(1, 2)));
    }

    @Test
    public void testParseFolds() {
        final List<Fold> folds = Day13.parseFolds(ImmutableList.of(
                "fold along y=7",
                "fold along x=5"));

        assertThat(folds, containsInAnyOrder(
                ImmutableFold.of('y', 7),
                ImmutableFold.of('x', 5)));
    }

    @Test
    public void testInit() {
        final List<Point> dots = ImmutableList.of(
                ImmutablePoint.of(1,2),
                ImmutablePoint.of(2,3),
                ImmutablePoint.of(3,4),
                ImmutablePoint.of(0,0),
                ImmutablePoint.of(4,0));

        final int[][] paper = Day13.init(dots);

        assertThat(paper.length, is(5));
        assertThat(paper[0].length, is(5));

        assertThat(Arrays.equals(paper[0], new int[]{1, 0, 0, 0, 1}), is(true));
        assertThat(Arrays.equals(paper[1], new int[]{0, 0, 0, 0, 0}), is(true));
        assertThat(Arrays.equals(paper[2], new int[]{0, 1, 0, 0, 0}), is(true));
        assertThat(Arrays.equals(paper[3], new int[]{0, 0, 1, 0, 0}), is(true));
        assertThat(Arrays.equals(paper[4], new int[]{0, 0, 0, 1, 0}), is(true));
    }

    @Test
    public void testMultipleFolds() {
        int[][] paper = Day13.init(dots);
        Arrays.stream(paper).forEach(arr -> System.out.println(Arrays.toString(arr)));
        System.out.println("\n");

        paper = ImmutableFold.of('y', 7).fold(paper);
        paper = ImmutableFold.of('x', 5).fold(paper);

        Arrays.stream(paper).forEach(arr -> System.out.println(Arrays.toString(arr)));

        assertThat(paper.length, is(7));
        assertThat(paper[0].length, is(5));
    }

    @Test
    public void testCount() {
        int[][] paper = new int[][] {
                {1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        assertThat(Day13.count(paper), is(17));
    }
}
