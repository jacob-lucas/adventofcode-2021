package com.jacoblucas.adventofcode2021.day04;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BingoBoardTest {
    public static final List<String> INPUT = ImmutableList.of(
            "22 13 17 11  0",
            "8  2 23  4 24",
            "21  9 14 16  7",
            "6 10  3 18  5",
            "1 12 20 15 19");

    @Test
    public void testBoardSetup() {
        final BingoBoard bingoBoard = new BingoBoard(INPUT);

        assertThat(bingoBoard.getBoard(), is(new int[][]
                {
                        {22, 13, 17, 11, 0},
                        {8, 2, 23, 4, 24},
                        {21, 9, 14, 16, 7},
                        {6, 10, 3, 18, 5},
                        {1, 12, 20, 15, 19}
                }
        ));

        assertThat(bingoBoard.getMarked(), is(new int[][]
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                }
        ));
    }

    @Test
    public void testMark() {
        final BingoBoard bingoBoard = new BingoBoard(INPUT);
        bingoBoard.mark(24);

        assertThat(bingoBoard.getMarked(), is(new int[][]
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                }
        ));
    }

    @Test
    public void testBingoRow() {
        final BingoBoard bingoBoard = new BingoBoard(INPUT);
        bingoBoard.mark(24);
        assertThat(bingoBoard.bingo(), is(false));

        bingoBoard.mark(8, 2, 23, 4);

        assertThat(bingoBoard.bingo(), is(true));
    }

    @Test
    public void testBingoColumn() {
        final BingoBoard bingoBoard = new BingoBoard(INPUT);
        bingoBoard.mark(24);
        assertThat(bingoBoard.bingo(), is(false));

        bingoBoard.mark(0, 7, 5, 19);

        assertThat(bingoBoard.bingo(), is(true));
    }

    @Test
    public void testCalculateScore() {
        final BingoBoard bingoBoard = new BingoBoard(ImmutableList.of(
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
        ));

        bingoBoard.mark(14, 21, 17, 24, 4, 9, 23, 11, 5, 2, 0, 7);

        assertThat(bingoBoard.bingo(), is(true));
        assertThat(bingoBoard.calculateScore(24), is(4512));
    }
}
