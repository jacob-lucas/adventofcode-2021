package com.jacoblucas.adventofcode2021.day01;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day1Test {
    private final Day1 day1 = new Day1();

    @Test
    public void testGetWindowIncreases() {
        final List<Integer> depthMeasurements = ImmutableList.of(
                199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        assertThat(day1.getWindowIncreases(depthMeasurements,1), is(7));
        assertThat(day1.getWindowIncreases(depthMeasurements,3), is(5));
    }
}
