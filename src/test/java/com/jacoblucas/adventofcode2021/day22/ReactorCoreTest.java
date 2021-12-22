package com.jacoblucas.adventofcode2021.day22;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReactorCoreTest {
    private ReactorCore reactorCore;

    @Before
    public void setUp() {
        reactorCore = new ReactorCore();
    }

    @Test
    public void testExecute() {
        final RebootStep rebootStep = RebootStep.parse("on x=10..12,y=11..13,z=12..14");
        reactorCore.execute(rebootStep);

        for (int z = 12; z <= 14; z++) {
            for (int y = 11; y <= 13; y++) {
                for (int x = 10; x <= 12; x++) {
                    assertThat(reactorCore.get(x,y,z), is(CubeState.ON));
                }
            }
        }
    }

    @Test
    public void testRebootExample1() {
        final List<RebootStep> rebootStepList = ImmutableList.of(
                RebootStep.parse("on x=10..12,y=10..12,z=10..12"),
                RebootStep.parse("on x=11..13,y=11..13,z=11..13"),
                RebootStep.parse("off x=9..11,y=9..11,z=9..11"),
                RebootStep.parse("on x=10..10,y=10..10,z=10..10"));

        reactorCore.reboot(rebootStepList);

        assertThat(reactorCore.count(CubeState.ON), is(39L));
    }

    @Test
    public void testLargerExample() throws IOException {
        final List<RebootStep> rebootStepList = InputReader.readFile("src/test/resources/", "day22-test-input.txt")
                .stream()
                .map(RebootStep::parse)
                .collect(Collectors.toList());

        reactorCore.reboot(rebootStepList);

        assertThat(reactorCore.count(CubeState.ON), is(590784L));
    }
}
