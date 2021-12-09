package com.jacoblucas.adventofcode2021.day06;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    private School school;

    @Before
    public void setUp() {
        school = new School("3,4,3,1,2");
    }

    @Test
    public void testSimulateFiveDays() {
        // Day 1
        long n = school.simulate(1);
        assertThat(n, is(5L));
        final Map<Integer, Long> expected = new HashMap<>();
        expected.put(0, 1L);
        expected.put(1, 1L);
        expected.put(2, 2L);
        expected.put(3, 1L);
        expected.put(4, 0L);
        expected.put(5, 0L);
        expected.put(6, 0L);
        expected.put(7, 0L);
        expected.put(8, 0L);
        assertThat(school.getMap(), is(expected));

        // Day 2
        n = school.simulate(1);
        assertThat(n, is(6L));

        // Day 3
        n = school.simulate(1);
        assertThat(n, is(7L));

        // Day 4
        n = school.simulate(1);
        assertThat(n, is(9L));

        // Day 5
        n = school.simulate(1);
        assertThat(n, is(10L));
        final Map<Integer, Long> expected2 = new HashMap<>(); // 5,6,5,3,4,5,6,7,7,8
        expected2.put(0, 0L);
        expected2.put(1, 0L);
        expected2.put(2, 0L);
        expected2.put(3, 1L);
        expected2.put(4, 1L);
        expected2.put(5, 3L);
        expected2.put(6, 2L);
        expected2.put(7, 2L);
        expected2.put(8, 1L);
        assertThat(school.getMap(), is(expected2));
    }

    @Test
    public void testSimulate18Days() {
        long n = school.simulate(18);
        assertThat(n, is(26L));
    }

    @Test
    public void testSimulateEightyDays() {
        long n = school.simulate(80);
        assertThat(n, is(5934L));
    }

    @Test
    public void testSimulate256Days() {
        long n = school.simulate(256);
        assertThat(n, is(26984457539L));
    }
}
