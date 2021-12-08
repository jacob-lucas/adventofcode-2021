package com.jacoblucas.adventofcode2021.day7;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day7Test {
    private Map<Integer, Integer> crabs;

    @Before
    public void setUp() {
        crabs = Day7.parse("16,1,2,0,4,2,7,1,2,14");

        final Map<Integer, Integer> expected = new HashMap<>();
        expected.put(16, 1);
        expected.put(1, 2);
        expected.put(2, 3);
        expected.put(0, 1);
        expected.put(4, 1);
        expected.put(7, 1);
        expected.put(14, 1);
        assertThat(crabs, is(expected));
    }

    @Test
    public void testGetMinFuelCostBasicCost() {
        final int fuelCost = Day7.getMinFuelCost(crabs, Day7::basicCost);
        assertThat(fuelCost, is(37));
    }

    @Test
    public void testGetMinFuelCostComplexCost() {
        final int fuelCost = Day7.getMinFuelCost(crabs, Day7::complexCost);
        assertThat(fuelCost, is(168));
    }
}
