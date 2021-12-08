package com.jacoblucas.adventofcode2021.day8;

import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day8Test {
    @Test
    public void testCountDigits() {
        assertThat(Day8.count("fdgacbe cefdb cefbgd gcbe", ImmutableSet.of(1, 4, 7, 8)), is(2L));
    }

    @Test
    public void testCountDigitsWithMap() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day8-test-input.txt");
        final Map<String, String> signalPatterns = Day8.parseInput(input);
        assertThat(Day8.count(signalPatterns, ImmutableSet.of(1, 4, 7, 8)), is(26L));
    }
}
