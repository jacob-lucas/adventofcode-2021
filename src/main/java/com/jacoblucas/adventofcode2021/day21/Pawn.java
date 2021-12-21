package com.jacoblucas.adventofcode2021.day21;

import java.util.ArrayList;
import java.util.List;

public class Pawn {
    public static final int ROLLS_PER_TURN = 3;

    private final int id;

    private int position;
    private int score;

    public Pawn(final int id, final int position) {
        this.id = id;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public void move(final Die die) {
        final List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < ROLLS_PER_TURN; i++) {
            int value = die.roll();
            rolls.add(value);
            position += value;
            if (position > 10) position = position % 10;
            if (position == 0) position = 10;
        }

        score += position;

        System.out.println("Player " + getId() + " rolls " + rolls + " and moves to space " + getPosition() + " for a total score of " + getScore());
    }
}
