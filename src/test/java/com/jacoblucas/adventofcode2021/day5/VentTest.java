package com.jacoblucas.adventofcode2021.day5;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VentTest {
    @Test
    public void testParse() {
        final Vent vent = Vent.parse("0,9 -> 5,8");
        final Vent expected = ImmutableVent.builder()
                .point1(ImmutablePoint.of(0, 9))
                .point2(ImmutablePoint.of(5, 8))
                .build();
        assertThat(vent, is(expected));
    }

    @Test
    public void testIsHorizontal() {
        final Vent vent = Vent.parse("9,7 -> 7,7");
        assertThat(vent.isHorizontal(), is(true));
        assertThat(vent.isVertical(), is(false));
    }

    @Test
    public void testIsVertical() {
        final Vent vent = Vent.parse("1,1 -> 1,3");
        assertThat(vent.isHorizontal(), is(false));
        assertThat(vent.isVertical(), is(true));
    }
}
