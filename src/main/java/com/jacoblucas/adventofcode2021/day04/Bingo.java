package com.jacoblucas.adventofcode2021.day04;

import java.util.ArrayList;
import java.util.List;

public class Bingo {
    private final List<List<BingoBoard>> resultOrderList;
    private final List<BingoBoard> boards;

    public Bingo(final List<BingoBoard> boards) {
        this.boards = boards;
        resultOrderList = new ArrayList<>();
    }

    // Draws numbers until there are no numbers left, or until every board has bingo
    public void run(final List<Integer> numbers) {
        int index = 0;
        while (index < numbers.size() && !boards.isEmpty()) {
            int n = numbers.get(index);
            List<BingoBoard> winners = draw(n);
            if (!winners.isEmpty()) {
                resultOrderList.add(winners);
                boards.removeAll(winners);
            }
            index++;
        }
    }

    // Returns the winning List of BingoBoards in case of bingo, empty if there are no winners
    public List<BingoBoard> draw(final int n) {
        // mark the number on all the boards
        boards.forEach(b -> b.mark(n));

        final List<BingoBoard> winners = new ArrayList<>();

        // bingo?
        for (final BingoBoard bingoBoard : boards) {
            if (bingoBoard.bingo()) {
                bingoBoard.calculateScore(n);
                winners.add(bingoBoard);
            }
        }

        return winners;
    }

    // Returns the first winner
    public BingoBoard getFirst() {
        return resultOrderList.get(0).get(0);
    }

    // Returns the last winner
    public BingoBoard getLast() {
        return resultOrderList.get(resultOrderList.size()-1).get(0);
    }
}
