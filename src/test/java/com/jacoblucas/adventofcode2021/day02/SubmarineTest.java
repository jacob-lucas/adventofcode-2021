package com.jacoblucas.adventofcode2021.day02;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubmarineTest {
    private Submarine submarine;

    @Before
    public void setUp() {
        submarine = new Submarine();
    }

    @Test
    public void testForwardV1() {
        submarine.execute("forward 5", 1);
        assertThat(submarine.getPosition(), is(5));
        assertThat(submarine.getDepth(), is(0));
    }

    @Test
    public void testDownV1() {
        submarine.execute("down 5", 1);
        assertThat(submarine.getPosition(), is(0));
        assertThat(submarine.getDepth(), is(5));
    }

    @Test
    public void testUpV1() {
        submarine.execute("down 5", 1);
        submarine.execute("up 3", 1);
        assertThat(submarine.getPosition(), is(0));
        assertThat(submarine.getDepth(), is(2));
    }

    @Test
    public void testForwardV2() {
        submarine.execute("down 1");
        submarine.execute("forward 5");
        assertThat(submarine.getPosition(), is(5));
        assertThat(submarine.getDepth(), is(5));
        assertThat(submarine.getAim(), is(1));
    }

    @Test
    public void testDownV2() {
        submarine.execute("down 5");
        assertThat(submarine.getPosition(), is(0));
        assertThat(submarine.getDepth(), is(0));
        assertThat(submarine.getAim(), is(5));
    }

    @Test
    public void testUpV2() {
        submarine.execute("down 5");
        submarine.execute("up 3");
        assertThat(submarine.getPosition(), is(0));
        assertThat(submarine.getDepth(), is(0));
        assertThat(submarine.getAim(), is(2));
    }
}
