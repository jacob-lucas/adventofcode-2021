package com.jacoblucas.adventofcode2021.day21;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PawnTest {
    @Test
    public void testMove() {
        final Die die = new Die();
        final Pawn playerOne = new Pawn(1, 4);
        final Pawn playerTwo = new Pawn(2, 8);

        playerOne.move(die);
        assertThat(playerOne.getPosition(), is(10));
        assertThat(playerOne.getScore(), is(10));

        playerTwo.move(die);
        assertThat(playerTwo.getPosition(), is(3));
        assertThat(playerTwo.getScore(), is(3));
    }
}
