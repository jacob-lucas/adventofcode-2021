package com.jacoblucas.adventofcode2021.day15;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day15Test {
    private Day15 day15;

    @Before
    public void setUp() {
        day15 = new Day15();
        day15.init("src/test/resources/", "day15-test-input.txt");
        day15.buildCavern();
    }

    @Test
    public void testSmallExample() {
        day15.init("src/test/resources/", "day15-test-input1.txt");
        day15.buildCavern();
        assertThat(day15.getLowestTotalRisk(), is(17));
    }

    @Test
    public void testBiggerExample() {
        day15.init("src/test/resources/", "day15-test-input2.txt");
        day15.buildCavern();
        assertThat(day15.getLowestTotalRisk(), is(27));
    }

    @Test
    public void testGetLowestTotalRisk() {
        // [040*, 040,  034,  031,  030,  025,  024,  025,  028,  030 ]
        // [039*, 037,  031,  030,  027,  022,  021,  022,  021,  028 ]
        // [037*, 036*, 033*, 027*, 022*, 021*, 020*, 019,  019,  021 ]
        // [039,  036,  030,  026,  022,  019,  019*, 014*, 013,  014 ]
        // [033,  032,  026,  023,  019,  018,  014,  013*, 012*, 013 ]
        // [032,  029,  028,  019,  018,  016,  013,  012,  009*, 010 ]
        // [031,  028,  027,  025,  016,  015,  013,  009,  007*, 009 ]
        // [028,  027,  025,  020,  016,  014,  013,  007,  004*, 002 ]
        // [030,  028,  023,  020,  019,  016,  009,  004,  002*, 001*]
        // [028,  025,  024,  023,  020,  018,  014,  009,  001,  000*]

        assertThat(day15.getLowestTotalRisk(), is(40));
    }

    @Test
    public void testAddAllTiles() {
        final int[][] allTiles = day15.addAllTiles(day15.cavern, 5);

        day15.init("src/test/resources/", "day15-expanded.txt");
        day15.buildCavern();

        assertThat(allTiles.length, is(day15.cavern.length));

        for (int i = 0; i < allTiles.length; i++) {
            assertThat(Arrays.equals(allTiles[i], day15.cavern[i]), is(true));
        }
    }
}
