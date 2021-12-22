package com.jacoblucas.adventofcode2021.day17;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day17Test {
    @Test
    public void testFire() {
        final int maxHeight = new Day17().findMaxHeight(20, 30, -10, -5);
        assertThat(maxHeight, is(45));
    }
}
