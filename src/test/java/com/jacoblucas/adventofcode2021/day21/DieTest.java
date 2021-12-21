package com.jacoblucas.adventofcode2021.day21;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DieTest {
    @Test
    public void testRoll() {
        final Die die = new Die();

        for (int i = 1; i <= 100; i++) {
            assertThat(die.roll(), is(i));
        }

        assertThat(die.roll(), is(1));
    }

    @Test
    public void testGet() {
        final Die die = new Die();

        for (int i = 1; i <= 1000; i++) {
            die.roll();
        }

        assertThat(die.roll(), is(1));
        assertThat(die.get(), is(1001));
    }
}
