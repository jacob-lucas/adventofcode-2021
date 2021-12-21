package com.jacoblucas.adventofcode2021.day21;

public class DiracDice {
    private final Die die;
    private final Pawn playerOne;
    private final Pawn playerTwo;

    public DiracDice(final Pawn playerOne, final Pawn playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.die = new Die();
    }

    public Die getDie() {
        return die;
    }

    public void play() {
        while (true) {
            playerOne.move(die);
            if (playerOne.getScore() >= 1000) {
                break;
            }

            playerTwo.move(die);
            if (playerTwo.getScore() >= 1000) {
                break;
            }
        }
    }
}
