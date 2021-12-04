package com.jacoblucas.adventofcode2021.day4;

import java.util.ArrayList;
import java.util.List;

public class Bingo {
    private final List<List<BingoBoard>> resultOrderMap;
    private final List<BingoBoard> boards;

    public Bingo(final List<BingoBoard> boards) {
        this.boards = boards;
        resultOrderMap = new ArrayList<>();
    }

    public void run(final List<Integer> numbers) {
        int index = 0;
        while (index < numbers.size() && !boards.isEmpty()) {
            int n = numbers.get(index);
            List<BingoBoard> winners = draw(n);
            if (!winners.isEmpty()) {
                resultOrderMap.add(winners);
                boards.removeAll(winners);
            }
            index++;
        }
    }

    // Returns the winning BingoBoard in case of bingo, null otherwise
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

    public BingoBoard getFirst() {
        return resultOrderMap.get(0).get(0);
    }

    public BingoBoard getLast() {
        return resultOrderMap.get(resultOrderMap.size()-1).get(0);
    }
}
