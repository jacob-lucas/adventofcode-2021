package com.jacoblucas.adventofcode2021.day9;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day9Test {
    private final int[][] heightmap = {
            {2,1,9,9,9,4,3,2,1,0},
            {3,9,8,7,8,9,4,9,2,1},
            {9,8,5,6,7,8,9,8,9,2},
            {8,7,6,7,8,9,6,7,8,9},
            {9,8,9,9,9,6,5,6,7,8}
    };
    @Test
    public void testParse() {
        final List<String> input = ImmutableList.of(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678");

        final int[][] parsed = Day9.parse(input);

        for (int i = 0; i < parsed.length; i++) {
            assertThat(Arrays.equals(parsed[i], heightmap[i]), is(true));
        }
    }

    @Test
    public void testFindLowPoints() {
        assertThat(Day9.findLowPoints(heightmap), is(ImmutableList.of(
                ImmutablePoint.of(1, 0),
                ImmutablePoint.of(9, 0),
                ImmutablePoint.of(2, 2),
                ImmutablePoint.of(6, 4)
        )));
    }

    @Test
    public void testGetRiskLevel() {
        assertThat(Day9.riskLevel(ImmutablePoint.of(1, 0), heightmap), is(2));
        assertThat(Day9.riskLevel(ImmutablePoint.of(9, 0), heightmap), is(1));
        assertThat(Day9.riskLevel(ImmutablePoint.of(2, 2), heightmap), is(6));
        assertThat(Day9.riskLevel(ImmutablePoint.of(6, 4), heightmap), is(6));
    }
}
