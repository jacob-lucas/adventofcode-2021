package com.jacoblucas.adventofcode2021.day5;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day5-input.txt");
        final List<Vent> vents = parse(input);

        final int width = getXLimit(vents) + 1;
        final int height = getYLimit(vents) + 1;
        final Grid grid = new Grid(width, height);

        grid.track(vents);

        int overlapping = grid.countWhere(i -> i >= 2);
        System.out.println(overlapping);
    }

    static List<Vent> parse(final List<String> input) {
        return input.stream()
                .map(Vent::parse)
                .collect(Collectors.toList());
    }

    static int getXLimit(final List<Vent> vents) {
        return vents.stream()
                .map(vent -> ImmutableList.of(vent.getX1(), vent.getX2()))
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    static int getYLimit(final List<Vent> vents) {
        return vents.stream()
                .map(vent -> ImmutableList.of(vent.getY1(), vent.getY2()))
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

}
