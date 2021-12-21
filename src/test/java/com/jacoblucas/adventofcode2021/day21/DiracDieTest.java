package com.jacoblucas.adventofcode2021.day21;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DiracDieTest {
    @Test
    public void testPlay() {
        final Pawn playerOne = new Pawn(1, 4);
        final Pawn playerTwo = new Pawn(2, 8);
        final DiracDice game = new DiracDice(playerOne, playerTwo);

        game.play();

        assertThat(game.getDie().get(), is(993));
    }
}
