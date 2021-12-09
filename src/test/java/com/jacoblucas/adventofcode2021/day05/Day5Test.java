package com.jacoblucas.adventofcode2021.day05;

import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day5Test {
    private List<Vent> vents;

    @Before
    public void setUp() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        vents = Day5.parse(input);
    }

    @Test
    public void testGetXLimit() {
        final int limit = Day5.getXLimit(vents);
        assertThat(limit, is(9));
    }

    @Test
    public void testGetYLimit() {
        final int limit = Day5.getYLimit(vents);
        assertThat(limit, is(9));
    }
}
