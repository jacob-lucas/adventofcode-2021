package com.jacoblucas.adventofcode2021.day14;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
        assertThat(Day14.pairInsertion("NNCB", rules), is("NCNBCHB"));
    }

    @Test
    public void testPairInsertion4x() {
        String result = Day14.pairInsertion("NNCB", rules); // 1
        result = Day14.pairInsertion(result, rules); // 2
        result = Day14.pairInsertion(result, rules); // 3
        result = Day14.pairInsertion(result, rules); // 4

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
}
