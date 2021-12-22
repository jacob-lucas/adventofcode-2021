package com.jacoblucas.adventofcode2021.day22;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

import java.util.List;
import java.util.stream.Collectors;

public class Day22 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day22-input.txt");

        // Part 1
        final ReactorCore reactorCore = new ReactorCore();
        final List<RebootStep> rebootStepList = input.stream()
                .map(RebootStep::parse)
                .collect(Collectors.toList());
        reactorCore.reboot(rebootStepList);

        final long onCount = reactorCore.count(CubeState.ON);
        System.out.println(onCount);
    }

    public static void main(String[] args) {
        new Day22().run();
    }
}
