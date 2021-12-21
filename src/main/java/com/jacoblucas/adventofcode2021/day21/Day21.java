package com.jacoblucas.adventofcode2021.day21;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

import java.util.List;
import java.util.stream.Collectors;

public class Day21 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day21-input.txt");

        final List<Pawn> pawns = input.stream()
                .map(line -> {
                    final String[] parts = line.split(" ");
                    return new Pawn(Integer.parseInt(parts[1]), Integer.parseInt(parts[4]));
                })
                .collect(Collectors.toList());

        final DiracDice game = new DiracDice(pawns.get(0), pawns.get(1));

        // Part 1
        game.play();
        System.out.println(Math.min(pawns.get(0).getScore(), pawns.get(1).getScore()) * game.getDie().get());
    }

    public static void main(String[] args) {
        new Day21().run();
    }
}
