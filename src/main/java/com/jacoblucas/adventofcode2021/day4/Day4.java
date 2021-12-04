package com.jacoblucas.adventofcode2021.day4;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day4-input.txt");

        final List<Integer> numbers = Arrays.stream(input.get(0).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        final List<BingoBoard> boards = IntStream.range(1, input.size())
                .mapToObj(i -> new BingoBoard(input.get(i)))
                .collect(Collectors.toList());

        final Bingo bingo = new Bingo(boards);
        bingo.run(numbers);
    }
}
