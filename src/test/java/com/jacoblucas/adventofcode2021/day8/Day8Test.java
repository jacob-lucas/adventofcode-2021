package com.jacoblucas.adventofcode2021.day8;

import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day8Test {
    @Test
    public void testCountDigits() {
        assertThat(Day8.count("fdgacbe cefdb cefbgd gcbe", ImmutableSet.of(1, 4, 7, 8)), is(2L));
    }

    @Test
    public void testCountDigitsWithMap() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day8-test-input.txt");
        final Map<String, String> signalPatterns = Day8.parseInput(input);
        assertThat(Day8.count(signalPatterns, ImmutableSet.of(1, 4, 7, 8)), is(26L));
    }

    @Test
    public void testDecode() {
        final Map<String, Integer> decoded = new HashMap<>();
        decoded.put(Day8.sortString("acedgfb"), 8);
        decoded.put(Day8.sortString("cdfbe"), 5);
        decoded.put(Day8.sortString("gcdfa"), 2);
        decoded.put(Day8.sortString("fbcad"), 3);
        decoded.put(Day8.sortString("dab"), 7);
        decoded.put(Day8.sortString("cefabd"), 9);
        decoded.put(Day8.sortString("cdfgeb"), 6);
        decoded.put(Day8.sortString("eafb"), 4);
        decoded.put(Day8.sortString("cagedb"), 0);
        decoded.put(Day8.sortString("ab"), 1);
        assertThat(Day8.decode("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab"), is(decoded));
    }

    @Test
    public void testDecodeStringWithKey() {
        assertThat(Day8.decode("abcefg", Display.POSITIONS), is(0));
        assertThat(Day8.decode("cf", Display.POSITIONS), is(1));
        assertThat(Day8.decode("acdeg", Display.POSITIONS), is(2));
        assertThat(Day8.decode("acdfg", Display.POSITIONS), is(3));
        assertThat(Day8.decode("bcdf", Display.POSITIONS), is(4));
        assertThat(Day8.decode("abdfg", Display.POSITIONS), is(5));
        assertThat(Day8.decode("abdefg", Display.POSITIONS), is(6));
        assertThat(Day8.decode("acf", Display.POSITIONS), is(7));
        assertThat(Day8.decode("abcdefg", Display.POSITIONS), is(8));
        assertThat(Day8.decode("acbdfg", Display.POSITIONS), is(9));
    }

    @Test
    public void testDecodeOutput() {
        final Map<String, Integer> decoded = new HashMap<>();
        decoded.put(Day8.sortString("acedgfb"), 8);
        decoded.put(Day8.sortString("cdfbe"), 5);
        decoded.put(Day8.sortString("gcdfa"), 2);
        decoded.put(Day8.sortString("fbcad"), 3);
        decoded.put(Day8.sortString("dab"), 7);
        decoded.put(Day8.sortString("cefabd"), 9);
        decoded.put(Day8.sortString("cdfgeb"), 6);
        decoded.put(Day8.sortString("eafb"), 4);
        decoded.put(Day8.sortString("cagedb"), 0);
        decoded.put(Day8.sortString("ab"), 1);

        assertThat(Day8.decodeOutput("cdfeb fcadb cdfeb cdbaf", decoded), is(5353));
    }

    @Test
    public void testLargerExamplePart2() throws IOException {
        final List<String> input = InputReader.readFile("src/test/resources/", "day8-test-input.txt");
        final Map<String, String> signalPatterns = Day8.parseInput(input);

        final int outputSum = Day8.getOutputSum(signalPatterns);
        assertThat(outputSum, is(61229));
    }
}
