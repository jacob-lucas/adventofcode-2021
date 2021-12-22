package com.jacoblucas.adventofcode2021.day17;

import com.jacoblucas.adventofcode2021.interfaces.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProbeLauncherTest {
    private final int minX = 20, maxX = 30, minY = -10, maxY = -5;
    private ProbeLauncher probeLauncher;

    @Before
    public void setUp() {
        probeLauncher = new ProbeLauncher();
    }

    @Test
    public void testFireStep() {
        final Pair<Integer, Integer> result = probeLauncher.step(7, 2);
        assertThat(probeLauncher.getProbePosition().getFirst(), is(7));
        assertThat(probeLauncher.getProbePosition().getSecond(), is(2));
        assertThat(result.getFirst(), is(6));
        assertThat(result.getSecond(), is(1));
    }

    @Test
    public void testFire() {
        probeLauncher.fire(minX, maxX, minY, maxY,7, 2);
        assertThat(probeLauncher.getProbePosition().getFirst(), is(28));
        assertThat(probeLauncher.getProbePosition().getSecond(), is(-7));
    }

    @Test
    public void withinArea() {
        probeLauncher.fire(minX, maxX, minY, maxY,7, 2);
        assertThat(probeLauncher.withinArea(20, 30, -10, -5), is(true));
    }

    @Test
    public void testMissedNotYetReachedTarget() {
        probeLauncher.fire(minX, maxX, minY, maxY,7, 2);
        assertThat(probeLauncher.missed(20, 30, -10, -5), is(false));
    }

    @Test
    public void testMissedOnTarget() {
        probeLauncher.fire(minX, maxX, minY, maxY,7, 2);
        assertThat(probeLauncher.missed(20, 30, -10, -5), is(false));
    }

    @Test
    public void testMissedPastTarget() {
        probeLauncher.fire(minX, maxX, minY, maxY,9, 2);
        assertThat(probeLauncher.missed(20, 30, -10, -5), is(true));
    }

    @Test
    public void testGetMaxHeight() {
        probeLauncher.fire(minX, maxX, minY, maxY,7, 2);
        assertThat(probeLauncher.getMaxHeight(), is(3));
    }
}
