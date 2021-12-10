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

        int score = Day10.scoreSyntaxCheck(lines);
        assertThat(score, is(26397));
    }

    @Test
    public void testAutoComplete() {
        assertThat(Day10.autoComplete("[({(<(())[]>[[{[]{<()<>>"), is("}}]])})]"));
        assertThat(Day10.autoComplete("[(()[<>])]({[<{<<[]>>("), is(")}>]})"));
        assertThat(Day10.autoComplete("(((({<>}<{<{<>}{[]{[]{}"), is("}}>}>))))"));
        assertThat(Day10.autoComplete("{<[[]]>}<{[{[{[]{()[[[]"), is("]]}}]}]}>"));
        assertThat(Day10.autoComplete("<{([{{}}[<[[[<>{}]]]>[]]"), is("])}>"));
    }

    @Test
    public void testScoreAutoComplete() {
        assertThat(Day10.scoreAutoComplete("}}]])})]"), is(288957L));
        assertThat(Day10.scoreAutoComplete(")}>]})"), is(5566L));
        assertThat(Day10.scoreAutoComplete("}}>}>))))"), is(1480781L));
        assertThat(Day10.scoreAutoComplete("]]}}]}]}>"), is(995444L));
        assertThat(Day10.scoreAutoComplete("])}>"), is(294L));
    }

    @Test
    public void testScoreAutoCompleteLines() {
        final List<String> lines = new ArrayList<>();
        lines.add("[({(<(())[]>[[{[]{<()<>>");
        lines.add("[(()[<>])]({[<{<<[]>>(");
        lines.add("(((({<>}<{<{<>}{[]{[]{}");
        lines.add("{<[[]]>}<{[{[{[]{()[[[]");
        lines.add("<{([{{}}[<[[[<>{}]]]>[]]");
        assertThat(Day10.scoreAutoComplete(lines), is(288957L));
    }
}
