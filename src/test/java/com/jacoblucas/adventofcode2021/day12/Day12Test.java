package com.jacoblucas.adventofcode2021.day12;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class Day12Test {

    private Map<String, Cave> caves;

    @Before
    public void setUp() {
        caves = Day12.parse(ImmutableList.of(
                "start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"));
    }

    @Test
    public void testParse() {
        assertThat(caves.keySet(), containsInAnyOrder("start", "A", "b", "c", "d", "end"));
        assertThat(caves.get("start").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("A", "b"));
        assertThat(caves.get("A").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("start", "c", "b", "end"));
        assertThat(caves.get("b").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("start", "A", "d", "end"));
        assertThat(caves.get("c").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("A"));
        assertThat(caves.get("d").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("b"));
        assertThat(caves.get("end").getConnections().stream().map(Cave::getId).collect(Collectors.toList()), containsInAnyOrder("A", "b"));
    }

    @Test
    public void testFindPathsExample1() {
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part1VisitRule);
        assertThat(paths.size(), is(10));
    }

    @Test
    public void testFindPathsExample2() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day12-test-input1.txt");
        final Map<String, Cave> caves = Day12.parse(input);
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part1VisitRule);
        assertThat(paths.size(), is(19));
    }

    @Test
    public void testFindPathsExample3() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day12-test-input2.txt");
        final Map<String, Cave> caves = Day12.parse(input);
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part1VisitRule);
        assertThat(paths.size(), is(226));
    }

    @Test
    public void testFindPathsExample1Part2Rule() {
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part2VisitRule);
        assertThat(paths.size(), is(36));
    }

    @Test
    public void testFindPathsExample2Part2Rule() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day12-test-input1.txt");
        final Map<String, Cave> caves = Day12.parse(input);
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part2VisitRule);
        assertThat(paths.size(), is(103));
    }

    @Test
    public void testFindPathsExample3Part2Rule() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day12-test-input2.txt");
        final Map<String, Cave> caves = Day12.parse(input);
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves, Day12::part2VisitRule);
        assertThat(paths.size(), is(3509));
    }

    @Test
    public void testPart1VisitRule() {
        final Cave start = new Cave("start");
        final Cave A = new Cave("A");
        final Cave b = new Cave("b");
        final Cave c = new Cave("c");

        assertThat(Day12.part1VisitRule(b, ImmutableList.of(start, A)), is(true)); // can visit b
        assertThat(Day12.part1VisitRule(b, ImmutableList.of(start, A, b, A)), is(false)); // can't visit b again
        assertThat(Day12.part1VisitRule(c, ImmutableList.of(start, A, b, A)), is(true)); // can visit c
    }

    @Test
    public void testPart2VisitRule() {
        final Cave start = new Cave("start");
        final Cave A = new Cave("A");
        final Cave b = new Cave("b");
        final Cave c = new Cave("c");
        final Cave d = new Cave("d");

        assertThat(Day12.part2VisitRule(b, ImmutableList.of(start, A, b, A)), is(true)); // can visit b again
        assertThat(Day12.part2VisitRule(c, ImmutableList.of(start, A, b, A, b, A, c)), is(false)); // can't visit c again
        assertThat(Day12.part2VisitRule(d, ImmutableList.of(start, b, A, b, A)), is(true)); // can visit d
        assertThat(Day12.part2VisitRule(start, ImmutableList.of(start, A, b, A, b, A, c)), is(false)); // can't visit start again
    }
}
