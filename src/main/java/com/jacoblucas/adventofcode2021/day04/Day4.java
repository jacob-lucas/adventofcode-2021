package com.jacoblucas.adventofcode2021.day04;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day4-input.txt");

        final List<Integer> numbers = parseNumbers(input);
        final List<BingoBoard> boards = parseBoards(input);

        final Bingo bingo = new Bingo(boards);
        bingo.run(numbers);

        final BingoBoard firstPlace = bingo.getFirst();
        System.out.println("First winning score: " + firstPlace.getScore());

        final BingoBoard lastPlace = bingo.getLast();
        System.out.println("Last winning score: " + lastPlace.getScore());
    }

    static List<Integer> parseNumbers(final List<List<String>> input) {
        return Arrays.stream(input.get(0).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static List<BingoBoard> parseBoards(final List<List<String>> input) {
        return IntStream.range(1, input.size())
                .mapToObj(i -> new BingoBoard(input.get(i)))
                .collect(Collectors.toList());
    }
}
