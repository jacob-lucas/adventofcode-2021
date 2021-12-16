package com.jacoblucas.adventofcode2021.day15;

import org.junit.Before;
import org.junit.Test;

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
        /*
            [41*, 41,  40,  34,  37,  30,  25,  35,  32,   41 ]
            [40*, 40,  39,  31,  30,  29,  24,  28,  28,   39 ]
            [39*, 37*, 36*, 33*, 27*, 22*, 21*, 22,  21,   37 ]
            [43,  42,  39,  30,  31,  22,  20*, 19*, 19,   29 ]
            [40,  36,  32,  26,  23,  19,  21,  14*, 13,   20 ]
            [33,  32,  29,  28,  19,  18,  21,  13*, 12*,  19 ]
            [32,  31,  32,  34,  25,  16,  15,  13,   9*,  12 ]
            [31,  28,  27,  25,  20,  16,  14,  13,   7*,  11 ]
            [36,  35,  33,  24,  21,  20,  17,   9,   4*,   2*]
            [38,  36,  33,  32,  31,  22,  18,  14,   9,    1*]
         */

        assertThat(day15.getLowestTotalRisk(), is(40));
    }
}
