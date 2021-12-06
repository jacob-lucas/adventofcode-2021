package com.jacoblucas.adventofcode2021.day6;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    private List<Lanternfish> fish;

    private School school;

    @Before
    public void setUp() {
        fish = new ArrayList<>();
        fish.add(new Lanternfish(3));
        fish.add(new Lanternfish(4));
        fish.add(new Lanternfish(3));
        fish.add(new Lanternfish(1));
        fish.add(new Lanternfish(2));

        school = new School(fish);
    }

    @Test
    public void testSimulateFiveDays() {
        // Day 1
        int n = school.simulate(1);
        assertThat(n, is(5));
        assertThat(fish.stream().map(Lanternfish::getTimer).collect(Collectors.toList()), is(ImmutableList.of(2,3,2,0,1)));

        // Day 2
        n = school.simulate(1);
        assertThat(n, is(6));
        assertThat(fish.stream().map(Lanternfish::getTimer).collect(Collectors.toList()), is(ImmutableList.of(1,2,1,6,0,8)));

        // Day 3
        n = school.simulate(1);
        assertThat(n, is(7));
        assertThat(fish.stream().map(Lanternfish::getTimer).collect(Collectors.toList()), is(ImmutableList.of(0,1,0,5,6,7,8)));

        // Day 4
        n = school.simulate(1);
        assertThat(n, is(9));
        assertThat(fish.stream().map(Lanternfish::getTimer).collect(Collectors.toList()), is(ImmutableList.of(6,0,6,4,5,6,7,8,8)));

        // Day 5
        n = school.simulate(1);
        assertThat(n, is(10));
        assertThat(fish.stream().map(Lanternfish::getTimer).collect(Collectors.toList()), is(ImmutableList.of(5,6,5,3,4,5,6,7,7,8)));
    }

    @Test
    public void testSimulateEightyDays() {
        int n = school.simulate(80);
        assertThat(n, is(5934));
    }
}
