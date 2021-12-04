package com.jacoblucas.adventofcode2021.day4;

import java.util.Arrays;
import java.util.List;

public class Bingo {
    private List<BingoBoard> boards;

    public Bingo(final List<BingoBoard> boards) {
        this.boards = boards;
    }

    public void run(List<Integer> numbers) {
        int index = 0;
        int lastCalled = -1;
        BingoBoard winner = null;
        while (winner == null && index < numbers.size()) {
            int n = numbers.get(index);
            lastCalled = n;
            winner = draw(n);
            index++;
        }

        if (winner == null) {
            System.out.println("There is no winner!");
        } else {
            System.out.println("The winning board is:");
            System.out.println(Arrays.deepToString(winner.getBoard()));
            System.out.println("Winning score: " + winner.getScore(lastCalled));
        }
    }

    // Returns the winning BingoBoard in case of bingo, null otherwise
    public BingoBoard draw(final int n) {
        // mark the number on all the boards
        boards.forEach(b -> b.mark(n));

        // bingo?
        for (final BingoBoard bingoBoard : boards) {
            if (bingoBoard.bingo()) {
                return bingoBoard;
            }
        }

        return null;
    }
}
