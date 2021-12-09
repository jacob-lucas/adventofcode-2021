package com.jacoblucas.adventofcode2021.day02;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day2-input.txt");

        final Submarine submarine1 = new Submarine();
        input.forEach(cmd -> submarine1.execute(cmd, 1));
        System.out.println(submarine1.getDepth() * submarine1.getPosition());

        final Submarine submarine2 = new Submarine();
        input.forEach(submarine2::execute);
        System.out.println(submarine2.getDepth() * submarine2.getPosition());
    }
}
