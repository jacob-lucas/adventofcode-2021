package com.jacoblucas.adventofcode2021.day05;

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

        // Part 1
        Grid grid = new Grid(width, height);
        grid.track(vents.stream()
                .filter(v -> v.isVertical() || v.isHorizontal())
                .collect(Collectors.toList()));
        System.out.println(grid.countWhere(i1 -> i1 >= 2));

        // Part 2
        grid = new Grid(width, height);
        grid.track(vents);
        System.out.println(grid.countWhere(i -> i >= 2));
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
