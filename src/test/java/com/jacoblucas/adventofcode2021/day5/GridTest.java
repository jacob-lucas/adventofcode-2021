package com.jacoblucas.adventofcode2021.day5;

import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public void testTrackDiagonalVent() {
        final Vent vent1 = Vent.parse("1,1 -> 3,3");
        grid.track(vent1);
        assertThat(grid.valueAt(1,1), is(1));
        assertThat(grid.valueAt(2,2), is(1));
        assertThat(grid.valueAt(3,3), is(1));

        final Vent vent2 = Vent.parse("9,7 -> 7,9");
        grid.track(vent2);
        assertThat(grid.valueAt(9,7), is(1));
        assertThat(grid.valueAt(8,8), is(1));
        assertThat(grid.valueAt(7,9), is(1));

        final Vent vent3 = Vent.parse("6,4 -> 2,0");
        grid.track(vent3);
        assertThat(grid.valueAt(6,4), is(1));
        assertThat(grid.valueAt(5,3), is(1));
        assertThat(grid.valueAt(4,2), is(1));
        assertThat(grid.valueAt(3,1), is(1));
        assertThat(grid.valueAt(2,0), is(1));

        final Vent vent4 = Vent.parse("5,5 -> 8,2");
        grid.track(vent4);
        assertThat(grid.valueAt(5,5), is(1));
        assertThat(grid.valueAt(6,4), is(2));
        assertThat(grid.valueAt(7,3), is(1));
        assertThat(grid.valueAt(8,2), is(1));
        assertThat(grid.valueAt(9,1), is(0));
    }

    @Test
    public void testTrackManyVents() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        final List<Vent> vents = Day5.parse(input);
        grid.track(vents);
        assertThat(grid.valueAt(0,9), is(2));
    }

    @Test
    public void testCountWhereExcludingDiagonals() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        final List<Vent> vents = Day5.parse(input);
        grid.track(vents.stream()
                .filter(v -> v.isVertical() || v.isHorizontal())
                .collect(Collectors.toList()));
        assertThat(grid.countWhere(i -> i >= 2), is(5));
    }

    @Test
    public void testCountWhereIncludingDiagonals() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day5-test-input.txt");
        final List<Vent> vents = Day5.parse(input);
        grid.track(vents);
        assertThat(grid.countWhere(i -> i >= 2), is(12));
    }
}
