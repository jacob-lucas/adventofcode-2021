package com.jacoblucas.adventofcode2021.day25;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

public class Day25 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day25-input.txt");

        // Part 1
        final SeaFloor seaFloor = new SeaFloor();
        seaFloor.init(input);
        int count = 0;
        int moves;
        do {
            System.out.println(count);
            moves = seaFloor.step();
            count++;
        } while (moves != 0);
        System.out.println(count);
    }

    public static void main(String[] args) {
        new Day25().run();
    }
}
