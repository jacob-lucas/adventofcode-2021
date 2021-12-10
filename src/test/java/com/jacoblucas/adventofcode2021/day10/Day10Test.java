package com.jacoblucas.adventofcode2021.day10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day10Test {
    @Test
    public void testSyntaxCheckNoErrors() {
        assertThat(Day10.syntaxCheck("()"), is(0));
        assertThat(Day10.syntaxCheck("[]"), is(0));
        assertThat(Day10.syntaxCheck("([])"), is(0));
        assertThat(Day10.syntaxCheck("{()()()}"), is(0));
        assertThat(Day10.syntaxCheck("<([{}])>"), is(0));
        assertThat(Day10.syntaxCheck("[<>({}){}[([])<>]]"), is(0));
        assertThat(Day10.syntaxCheck("[<>({}){}[([])<>]]"), is(0));
        assertThat(Day10.syntaxCheck("(((((((((())))))))))"), is(0));
    }

    @Test
    public void testCorrupted() {
        assertThat(Day10.syntaxCheck("{()()()>"), is(7));
        assertThat(Day10.syntaxCheck("(((()))}"), is(7));
        assertThat(Day10.syntaxCheck("<([]){()}[{}])"), is(13));
        assertThat(Day10.syntaxCheck("{([(<{}[<>[]}>{[]{[(<()>"), is(12));
        assertThat(Day10.syntaxCheck("[[<[([]))<([[{}[[()]]]"), is(8));
        assertThat(Day10.syntaxCheck("[{[{({}]{}}([{[{{{}}([]"), is(7));
        assertThat(Day10.syntaxCheck("[<(<(<(<{}))><([]([]()"), is(10));
        assertThat(Day10.syntaxCheck("<{([([[(<>()){}]>(<<{{"), is(16));
    }

    @Test
    public void testIncomplete() {
        assertThat(Day10.syntaxCheck("[({(<(())[]>[[{[]{<()<>>"), is(-1));
        assertThat(Day10.syntaxCheck("[(()[<>])]({[<{<<[]>>("), is(-1));
        assertThat(Day10.syntaxCheck("(((({<>}<{<{<>}{[]{[]{}"), is(-1));
        assertThat(Day10.syntaxCheck("{<[[]]>}<{[{[{[]{()[[[]"), is(-1));
        assertThat(Day10.syntaxCheck("<{([{{}}[<[[[<>{}]]]>[]]"), is(-1));
    }

    @Test
    public void testScore() {
        final List<String> lines = new ArrayList<>();
        lines.add("[({(<(())[]>[[{[]{<()<>>");
        lines.add("[(()[<>])]({[<{<<[]>>(");
        lines.add("{([(<{}[<>[]}>{[]{[(<()>");
        lines.add("(((({<>}<{<{<>}{[]{[]{}");
        lines.add("[[<[([]))<([[{}[[()]]]");
        lines.add("[{[{({}]{}}([{[{{{}}([]");
        lines.add("{<[[]]>}<{[{[{[]{()[[[]");
        lines.add("[<(<(<(<{}))><([]([]()");
        lines.add("<{([([[(<>()){}]>(<<{{");
        lines.add("<{([{{}}[<[[[<>{}]]]>[]]");

        int score = Day10.score(lines);
        assertThat(score, is(26397));
    }
}
