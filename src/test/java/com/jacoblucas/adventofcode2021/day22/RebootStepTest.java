package com.jacoblucas.adventofcode2021.day22;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RebootStepTest {
    @Test
    public void testParse() {
        assertThat(RebootStep.parse("on x=10..12,y=11..13,z=12..14"), is(
                ImmutableRebootStep.of(
                        ImmutableCuboid.of(
                            ModifiablePair.create(10, 12),
                            ModifiablePair.create(11, 13),
                            ModifiablePair.create(12, 14)),
                        CubeState.ON)));
    }

    @Test
    public void testToString() {
        final String raw = "on x=10..12,y=11..13,z=12..14";
        final RebootStep rebootStep = RebootStep.parse(raw);
        assertThat(rebootStep.toString(), is(raw));
    }
}
