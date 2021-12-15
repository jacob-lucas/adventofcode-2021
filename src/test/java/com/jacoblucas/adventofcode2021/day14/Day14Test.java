package com.jacoblucas.adventofcode2021.day14;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class Day14Test {
    private Map<String, String> rules;

    @Before
    public void setUp() {
        rules = new HashMap<>();
        rules.put("CH", "B");
        rules.put("HH", "N");
        rules.put("CB", "H");
        rules.put("NH", "C");
        rules.put("HB", "C");
        rules.put("HC", "B");
        rules.put("HN", "C");
        rules.put("NN", "C");
        rules.put("BH", "H");
        rules.put("NC", "B");
        rules.put("NB", "B");
        rules.put("BN", "B");
        rules.put("BB", "N");
        rules.put("BC", "B");
        rules.put("CC", "N");
        rules.put("CN", "C");
    }

    @Test
    public void testParseRules() {
        final List<String> input = new ArrayList<>();
        input.add("CH -> B");
        input.add("HH -> N");
        input.add("CB -> H");
        input.add("NH -> C");
        input.add("HB -> C");
        input.add("HC -> B");
        input.add("HN -> C");
        input.add("NN -> C");
        input.add("BH -> H");
        input.add("NC -> B");
        input.add("NB -> B");
        input.add("BN -> B");
        input.add("BB -> N");
        input.add("BC -> B");
        input.add("CC -> N");
        input.add("CN -> C");
        assertThat(Day14.parseRules(input), is(rules));
    }

    @Test
    public void testPairInsertion() {
        assertThat(Day14.pairInsertionV1("NNCB", rules), is("NCNBCHB"));
    }

    @Test
    public void testPairInsertion4x() {
        String result = Day14.pairInsertionV1("NNCB", rules); // 1
        result = Day14.pairInsertionV1(result, rules); // 2
        result = Day14.pairInsertionV1(result, rules); // 3
        result = Day14.pairInsertionV1(result, rules); // 4

        assertThat(result, is("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"));
    }

    @Test
    public void testGroupByChar() {
        final Map<Character, Integer> grouped = Day14.groupByChar("NCNBCHB");
        assertThat(grouped.get('N'), is(2));
        assertThat(grouped.get('C'), is(2));
        assertThat(grouped.get('B'), is(2));
        assertThat(grouped.get('C'), is(2));
        assertThat(grouped.get('H'), is(1));
    }

    @Test
    public void testPairInsertionV2Example1() {
        // result is "NCNBCHB"
        final Map<Character, BigInteger> expected = new HashMap<>();
        expected.put('N', BigInteger.valueOf(2L));
        expected.put('B', BigInteger.valueOf(2L));
        expected.put('C', BigInteger.valueOf(2L));
        expected.put('H', BigInteger.valueOf(1L));

        assertThat(Day14.pairInsertionV2("NNCB", rules, 1), is(expected));
    }

    @Test
    public void testPairInsertionV2Example2() {
        // result is "NBCCNBBBCBHCB"
        final Map<Character, BigInteger> expected = new HashMap<>();
        expected.put('N', BigInteger.valueOf(2L));
        expected.put('B', BigInteger.valueOf(6L));
        expected.put('C', BigInteger.valueOf(4L));
        expected.put('H', BigInteger.valueOf(1L));

        assertThat(Day14.pairInsertionV2("NCNBCHB", rules, 1), is(expected));
    }

    @Test
    public void testPairInsertionIteration() {
        // result is
        // "NNCB" -> "NCNBCHB" ->
        // "NCNBCHB" -> "NBCCNBBBCBHCB" ->
        // "NBCCNBBBCBHCB" -> "NBBBCNCCNBBNBNBBCHBHHBCHB" ->
        // "NBBBCNCCNBBNBNBBCHBHHBCHB" -> "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"
        final Map<Character, BigInteger> expected = new HashMap<>();
        expected.put('N', BigInteger.valueOf(11L));
        expected.put('B', BigInteger.valueOf(23L));
        expected.put('C', BigInteger.valueOf(10L));
        expected.put('H', BigInteger.valueOf(5L));

        assertThat(Day14.pairInsertionV2("NNCB", rules, 4), is(expected));
    }

//    @Test
//    public void testGroupByCharV2() {
//        final Map<Pair<Character, Character>, BigInteger> pairCountMap = new HashMap<>();
//        pairCountMap.put(ModifiablePair.create('N', 'B'), BigInteger.valueOf(2L));
//        pairCountMap.put(ModifiablePair.create('B', 'C'), BigInteger.valueOf(2L));
//        pairCountMap.put(ModifiablePair.create('C', 'C'), BigInteger.valueOf(1L));
//        pairCountMap.put(ModifiablePair.create('C', 'N'), BigInteger.valueOf(1L));
//        pairCountMap.put(ModifiablePair.create('B', 'B'), BigInteger.valueOf(2L));
//        pairCountMap.put(ModifiablePair.create('C', 'B'), BigInteger.valueOf(2L));
//        pairCountMap.put(ModifiablePair.create('B', 'H'), BigInteger.valueOf(1L));
//        pairCountMap.put(ModifiablePair.create('H', 'C'), BigInteger.valueOf(1L));
//
//        final Map<Character, BigInteger> expected = new HashMap<>();
//        expected.put('N', BigInteger.valueOf(1*2 + 1*1));
//        expected.put('B', BigInteger.valueOf(1*2 + 1*2 + 2*2 + 1*2 + 1*1));
//        expected.put('C', BigInteger.valueOf(1*1 + 1*2 + 1*1 + 2*1 + 1*2));
//        expected.put('H', BigInteger.valueOf(1*1 + 1*1));
//
//        final Map<Character, BigInteger> result = Day14.groupByCharV2(pairCountMap);
//        assertThat(result, is(expected));
//    }

    @Test
    public void testPart2FullExample() {
        final Map<Character, BigInteger> result = Day14.pairInsertionV2("NNCB", rules, 40);
        BigInteger mostCommon = result.values().stream().max(Comparator.naturalOrder()).get();
        BigInteger leastCommon = result.values().stream().min(Comparator.naturalOrder()).get();
        long delta = mostCommon.subtract(leastCommon).longValue();
        assertThat(delta, is(2188189693529L));
    }
}
