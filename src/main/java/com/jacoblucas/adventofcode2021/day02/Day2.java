package com.jacoblucas.adventofcode2021.day02;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

import java.io.IOException;

public class Day2 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day2-input.txt");

        final Submarine submarine1 = new Submarine();
        input.forEach(cmd -> submarine1.execute(cmd, 1));
        System.out.println(submarine1.getDepth() * submarine1.getPosition());

        final Submarine submarine2 = new Submarine();
        input.forEach(submarine2::execute);
        System.out.println(submarine2.getDepth() * submarine2.getPosition());
    }

    public static void main(String[] args) throws IOException {
        new Day2().run();
    }
}
