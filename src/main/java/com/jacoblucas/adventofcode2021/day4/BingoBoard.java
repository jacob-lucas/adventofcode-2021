package com.jacoblucas.adventofcode2021.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BingoBoard {
    public static final int SIZE = 5;

    private final int[][] board;
    private final int[][] marked;

    public BingoBoard(final List<String> input) {
        board = new int[SIZE][SIZE];
        marked = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            final String rowStr = input.get(i);
            final String[] parts = rowStr.split(" ");

            final List<Integer> row = new ArrayList<>();
            for (int j = 0; j < parts.length; j++) {
                if (!parts[j].isEmpty()) {
                    row.add(Integer.parseInt(parts[j]));
                }
            }
            board[i] = row.stream().mapToInt(n -> n).toArray();
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int[][] getMarked() {
        return marked;
    }

    public void mark(final int ...ns) {
        IntStream.range(0, ns.length).forEach(i -> mark(ns[i]));
    }

    public void mark(final int n) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == n) {
                    marked[i][j] = 1;
                }
            }
        }
    }

    public boolean bingo() {
        final int[] bingo = {1, 1, 1, 1, 1};

        // check rows
        for (int i = 0; i < SIZE; i++) {
            final int[] markedAtRow = marked[i];
            if (Arrays.equals(markedAtRow, bingo)) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < SIZE; j++) {
            final int[] markedAtCol = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                markedAtCol[i] = marked[i][j];
            }

            if (Arrays.equals(markedAtCol, bingo)) {
                return true;
            }
        }

        return false;
    }

    public int getScore(final int lastNumberCalled) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (marked[i][j] == 0) {
                    sum += board[i][j];
                }
            }
        }

        return sum * lastNumberCalled;
    }
}
