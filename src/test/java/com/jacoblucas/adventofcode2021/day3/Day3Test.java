package com.jacoblucas.adventofcode2021.day3;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day3Test {
    private static final List<String> INPUT = ImmutableList.of(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010");

    @Test
    public void testMostCommonBit() {
        assertThat(Day3.mostCommonBit(INPUT, 0), is(1));
    }

    @Test
    public void testGammaRate() {
        assertThat(Day3.getGammaRate(INPUT), is("10110"));
    }

    @Test
    public void testLeastCommonBit() {
        assertThat(Day3.leastCommonBit(INPUT, 0), is(0));
    }

    @Test
    public void testEpsilonRate() {
        assertThat(Day3.getEpsilonRate(INPUT), is("01001"));
    }

    @Test
    public void testGetPowerConsumption() {
        assertThat(Day3.getPowerConsumption(INPUT), is(198));
    }

    @Test
    public void testGetOxygenGeneratorRating() {
        assertThat(Day3.getOxygenGeneratorRating(INPUT), is("10111"));
    }

    @Test
    public void testGetCO2ScrubberRating() {
        assertThat(Day3.getCO2ScrubberRating(INPUT), is("01010"));
    }

    @Test
    public void testGetLifeSupportRating() {
        assertThat(Day3.getLifeSupportRating(INPUT), is(230));
    }
}
