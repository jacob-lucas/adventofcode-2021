package com.jacoblucas.adventofcode2021.day13;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day13-input.txt");
        final List<Point> dots = parseDots(input.get(0));
        final List<Fold> folds = parseFolds(input.get(1));

        int[][] paper = init(dots);

        // Part 1
        final Fold first = folds.get(0);
        paper = first.fold(paper);
        System.out.println(count(paper));

        // Part 2
        for (int i = 1; i < folds.size(); i++) {
            final Fold f = folds.get(i);
            paper = f.fold(paper);
        }
        print(paper);
    }

    private static void print(final int[][] paper) {
        for (int[] ints : paper) {
            final StringBuilder sb = new StringBuilder();
            for (int anInt : ints) {
                sb.append(anInt == 1 ? "#" : ".");
            }
            System.out.println(sb);
        }
    }

    public static List<Point> parseDots(final List<String> input) {
        return input.stream()
                .map(str -> {
                    final String[] parts = str.split(",");
                    return ImmutablePoint.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }

    public static List<Fold> parseFolds(final List<String> input) {
        return input.stream()
                .map(str -> {
                    final String[] parts = str.split("=");
                    return ImmutableFold.of(parts[0].charAt(parts[0].length() - 1), Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }

    public static int[][] init(final List<Point> dots) {
        final int maxX = dots.stream()
                .mapToInt(Point::getX)
                .max()
                .getAsInt();
        final int maxY = dots.stream()
                .mapToInt(Point::getY)
                .max()
                .getAsInt();

        final int[][] paper = new int[maxY + 1][maxX + 1];
        dots.forEach(dot -> paper[dot.getY()][dot.getX()] = 1);
        return paper;
    }

    public static int count(final int[][] paper) {
        int count = 0;
        for (int[] ints : paper) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
