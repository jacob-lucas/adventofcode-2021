package com.jacoblucas.adventofcode2021.day25;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SeaFloorTest {
    private SeaFloor seaFloor;

    @Before
    public void setUp() {
        seaFloor = new SeaFloor();
    }

    @Test
    public void testInit() {
        final List<String> input = new ArrayList<>();
        input.add("v...>>.vv>");
        input.add(".vv>>.vv..");
        input.add(">>.>v>...v");
        input.add(">>v>>.>.v.");
        input.add("v>v.vv.v..");
        input.add(">.>>..v...");
        input.add(".vv..>.>v.");
        input.add("v.v..>>v.v");
        input.add("....v..v.>");

        seaFloor = new SeaFloor();
        seaFloor.init(input);

        assertThat(seaFloor.get(SeaCucumberType.EAST).count(), is(23L));
        assertThat(seaFloor.get(SeaCucumberType.SOUTH).count(), is(26L));
    }

    @Test
    public void testGetAdjacentEast() {
        seaFloor.init(ImmutableList.of(
                "...v>>>>...",
                "...v>>>>..."
        ));

        final Point adjacent = seaFloor.getAdjacent(ImmutablePoint.of(3, 1), SeaCucumberType.EAST);
        assertThat(adjacent, is(ImmutablePoint.of(4, 1)));
    }

    @Test
    public void testGetAdjacentEastWrapWest() {
        seaFloor.init(ImmutableList.of(
                "...v>>>>...",
                "...v>>>>..."
        ));

        final Point adjacent = seaFloor.getAdjacent(ImmutablePoint.of(10, 1), SeaCucumberType.EAST);
        assertThat(adjacent, is(ImmutablePoint.of(0, 1)));
    }

    @Test
    public void testGetAdjacentSouth() {
        seaFloor.init(ImmutableList.of(
                "...v>>>>...",
                "...v>>>>..."
        ));

        final Point adjacent = seaFloor.getAdjacent(ImmutablePoint.of(3, 0), SeaCucumberType.SOUTH);
        assertThat(adjacent, is(ImmutablePoint.of(3, 1)));
    }

    @Test
    public void testGetAdjacentSouthWrapNorth() {
        seaFloor.init(ImmutableList.of(
                "...v>>>>...",
                "...v>>>>...",
                "...v>>>>..."
        ));

        final Point adjacent = seaFloor.getAdjacent(ImmutablePoint.of(3, 2), SeaCucumberType.SOUTH);
        assertThat(adjacent, is(ImmutablePoint.of(3, 0)));
    }

    @Test
    public void testIsAdjacentEmpty() {
        seaFloor.init(ImmutableList.of(
                "...v>>>>...",
                "...v>>>>...",
                "....>>>>..."
        ));

        assertThat(seaFloor.isAdjacentEmpty(ImmutablePoint.of(6, 0), SeaCucumberType.EAST), is(false));
        assertThat(seaFloor.isAdjacentEmpty(ImmutablePoint.of(7, 0), SeaCucumberType.EAST), is(true));

        assertThat(seaFloor.isAdjacentEmpty(ImmutablePoint.of(3, 0), SeaCucumberType.SOUTH), is(false));
        assertThat(seaFloor.isAdjacentEmpty(ImmutablePoint.of(3, 1), SeaCucumberType.SOUTH), is(true));
    }

    @Test
    public void testMoveEast() {
        seaFloor.init(ImmutableList.of(
                "........>...",
                "............",
                "............"
        ));

        final Map<Point, SeaCucumber> updated = new HashMap<>(seaFloor.getMap());
        assertThat(updated, is(seaFloor.getMap()));

        seaFloor.move(ImmutablePoint.of(8, 0), updated);

        assertThat(updated, is(not(seaFloor.getMap())));
        assertThat(updated.get(ImmutablePoint.of(8, 0)), is(nullValue()));
        assertThat(updated.get(ImmutablePoint.of(9, 0)), is(ImmutableSeaCucumber.of(SeaCucumberType.EAST)));
    }

    @Test
    public void testMoveSouth() {
        seaFloor.init(ImmutableList.of(
                "........v...",
                "............",
                "............"
        ));

        final Map<Point, SeaCucumber> updated = new HashMap<>(seaFloor.getMap());
        assertThat(updated, is(seaFloor.getMap()));

        seaFloor.move(ImmutablePoint.of(8, 0), updated);

        assertThat(updated, is(not(seaFloor.getMap())));
        assertThat(updated.get(ImmutablePoint.of(8, 0)), is(nullValue()));
        assertThat(updated.get(ImmutablePoint.of(8, 1)), is(ImmutableSeaCucumber.of(SeaCucumberType.SOUTH)));
    }

    @Test
    public void testSimpleStep() {
        seaFloor.init(ImmutableList.of("...>>>>>..."));

        seaFloor.step();
        assertThat(seaFloor.get().get(0), is("...>>>>.>.."));

        seaFloor.step();
        assertThat(seaFloor.get().get(0), is("...>>>.>.>."));
    }

    @Test
    public void testExampleOne() {
        final List<String> input = new ArrayList<>();
        input.add("..........");
        input.add(".>v....v..");
        input.add(".......>..");
        input.add("..........");

        seaFloor.init(input);

        seaFloor.step();

        final List<String> expected = new ArrayList<>();
        expected.add("..........");
        expected.add(".>........");
        expected.add("..v....v>.");
        expected.add("..........");

        final List<String> actual = seaFloor.get();
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i), is(expected.get(i)));
        }
    }

    @Test
    public void testExampleTwo() {
        final List<String> input = new ArrayList<>();
        input.add("...>...");
        input.add(".......");
        input.add("......>");
        input.add("v.....>");
        input.add("......>");
        input.add(".......");
        input.add("..vvv..");

        seaFloor.init(input);

        seaFloor.step();
        seaFloor.step();
        seaFloor.step();
        seaFloor.step();

        final List<String> expected = new ArrayList<>();
        expected.add(">......");
        expected.add("..v....");
        expected.add("..>.v..");
        expected.add(".>.v...");
        expected.add("...>...");
        expected.add(".......");
        expected.add("v......");

        final List<String> actual = seaFloor.get();
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i), is(expected.get(i)));
        }
    }

    @Test
    public void testExampleThree() {
        final List<String> input = new ArrayList<>();
        input.add("v...>>.vv>");
        input.add(".vv>>.vv..");
        input.add(">>.>v>...v");
        input.add(">>v>>.>.v.");
        input.add("v>v.vv.v..");
        input.add(">.>>..v...");
        input.add(".vv..>.>v.");
        input.add("v.v..>>v.v");
        input.add("....v..v.>");

        seaFloor.init(input);

        IntStream.range(0, 58).forEach(i -> seaFloor.step());

        final List<String> expected = new ArrayList<>();
        expected.add("..>>v>vv..");
        expected.add("..v.>>vv..");
        expected.add("..>>v>>vv.");
        expected.add("..>>>>>vv.");
        expected.add("v......>vv");
        expected.add("v>v....>>v");
        expected.add("vvv.....>>");
        expected.add(">vv......>");
        expected.add(".>v.vv.v..");

        final List<String> actual = seaFloor.get();
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i), is(expected.get(i)));
        }
    }
}
