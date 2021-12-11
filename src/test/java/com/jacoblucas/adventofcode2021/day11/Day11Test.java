package com.jacoblucas.adventofcode2021.day11;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day11Test {
    private List<String> initialInput;
    private int[][] grid;

    @Before
    public void setUp() {
        initialInput = ImmutableList.of(
                "5483143223",
                "2745854711",
                "5264556173",
                "6141336146",
                "6357385478",
                "4167524645",
                "2176841721",
                "6882881134",
                "4846848554",
                "5283751526");

        grid = new int[Day11.GRID_SIZE][Day11.GRID_SIZE];
        Day11.init(grid, initialInput);
    }

    @Test
    public void testStep() {
        final List<String> expected = ImmutableList.of(
                "6594254334",
                "3856965822",
                "6375667284",
                "7252447257",
                "7468496589",
                "5278635756",
                "3287952832",
                "7993992245",
                "5957959665",
                "6394862637");

        int[][] expectedGrid = new int[Day11.GRID_SIZE][Day11.GRID_SIZE];
        Day11.init(expectedGrid, expected);

        Day11.step(grid);
        for (int i = 0; i < grid.length; i++) {
            assertThat(Arrays.equals(grid[i], expectedGrid[i]), is(true));
        }
    }
}
