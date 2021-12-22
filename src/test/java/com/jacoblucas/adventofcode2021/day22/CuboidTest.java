package com.jacoblucas.adventofcode2021.day22;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CuboidTest {
    @Test
    public void testGetCubes() {
        final Cuboid cuboid = ImmutableCuboid.of(
                ModifiablePair.create(10, 12),
                ModifiablePair.create(11, 13),
                ModifiablePair.create(12, 14));

        assertThat(cuboid.getCubes().size(), is(27));
    }

    @Test
    public void testToString() {
        final Cuboid cuboid = ImmutableCuboid.of(
                ModifiablePair.create(10, 12),
                ModifiablePair.create(11, 13),
                ModifiablePair.create(12, 14));

        assertThat(cuboid.toString(), is("x=10..12,y=11..13,z=12..14"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckInvalidX() {
        ImmutableCuboid.of(
                ModifiablePair.create(10, 1),
                ModifiablePair.create(11, 13),
                ModifiablePair.create(12, 14));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckInvalidY() {
        ImmutableCuboid.of(
                ModifiablePair.create(10, 12),
                ModifiablePair.create(11, 1),
                ModifiablePair.create(12, 14));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckInvalidZ() {
        ImmutableCuboid.of(
                ModifiablePair.create(10, 12),
                ModifiablePair.create(11, 13),
                ModifiablePair.create(12, 1));
    }
}
