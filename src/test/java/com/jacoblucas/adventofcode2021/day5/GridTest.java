package com.jacoblucas.adventofcode2021.day5;

import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GridTest {
    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(10,10);
        System.out.println("Before:");
        System.out.println(grid);
    }

    @After
    public void tearDown() {
        System.out.println("After:");
        System.out.println(grid);
    }

    @Test
    public void testTrackHorizontalVent() {
        final Vent vent = Vent.parse("9,7 -> 7,7");
        grid.track(vent);
        assertThat(grid.valueAt(9,7), is(1));
        assertThat(grid.valueAt(8,7), is(1));
        assertThat(grid.valueAt(7,7), is(1));
    }

    @Test
    public void testTrackVerticalVent() {
        final Vent vent = Vent.parse("1,1 -> 1,3");
        grid.track(vent);
        assertThat(grid.valueAt(1,1), is(1));
        assertThat(grid.valueAt(1,2), is(1));
        assertThat(grid.valueAt(1,3), is(1));
    }

    @Test
    public void testTrackManyVents() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        final List<Vent> vents = Day5.parse(input);
        grid.track(vents);
        assertThat(grid.valueAt(0,9), is(2));
    }

    @Test
    public void testCountWhere() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        final List<Vent> vents = Day5.parse(input);
        grid.track(vents);
        assertThat(grid.countWhere(i -> i >= 2), is(5));
    }
}
