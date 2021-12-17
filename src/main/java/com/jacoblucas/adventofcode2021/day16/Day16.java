package com.jacoblucas.adventofcode2021.day16;

import com.jacoblucas.adventofcode2021.SingleListInputProblem;

public class Day16 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day16-input.txt");
        final Packet packet = PacketDecoder.decodeHex(input.get(0));

        // Part 1
        System.out.println(packet.getVersionSum());

        // Part 2
        System.out.println(packet.get());
    }

    public static void main(String[] args) {
        new Day16().run();
    }
}
