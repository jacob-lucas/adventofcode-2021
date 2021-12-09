package com.jacoblucas.adventofcode2021.day08;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static com.jacoblucas.adventofcode2021.day08.Display.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DisplayTest {
    @Test
    public void testDigitToStringList() {
        final List<String> expected = ImmutableList.of(
                " aaaa ",
                "b    c",
                "b    c",
                " .... ",
                "e    f",
                "e    f",
                " gggg ");
        assertThat(Display.digitToStringList(ZERO), is(expected));
    }

    @Test
    public void testPrint() {
        final List<String> result = Display.print(1234);
        assertThat(result, is(ImmutableList.of(
                " ....    aaaa    aaaa    ....   ",
                ".    c  .    c  .    c  b    c  ",
                ".    c  .    c  .    c  b    c  ",
                " ....    dddd    dddd    dddd   ",
                ".    f  e    .  .    f  .    f  ",
                ".    f  e    .  .    f  .    f  ",
                " ....    gggg    gggg    ....   ")));
    }
}
