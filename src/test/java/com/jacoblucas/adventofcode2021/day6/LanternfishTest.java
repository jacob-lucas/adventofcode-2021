package com.jacoblucas.adventofcode2021.day6;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LanternfishTest {
    @Test
    public void testGrowNewFish() {
        final Lanternfish lf = new Lanternfish();
        assertThat(lf.grow(), is(7));
        assertThat(lf.grow(), is(6));
    }

    @Test
    public void testGrowExistingFish() {
        final Lanternfish lf = new Lanternfish(3, false);
        int g1 = lf.grow();
        int g2 = lf.grow();
        int g3 = lf.grow();
        int g4 = lf.grow();
        int g5 = lf.grow();

        assertThat(g1, is(2));
        assertThat(g2, is(1));
        assertThat(g3, is(0));
        assertThat(g4, is(6));
        assertThat(g5, is(5));
    }
}
