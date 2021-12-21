package com.jacoblucas.adventofcode2021.day01;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2021.SingleListInputProblem;
import com.jacoblucas.adventofcode2021.day16.OperatorPacket;
import com.jacoblucas.adventofcode2021.day16.Packet;
import com.jacoblucas.adventofcode2021.day16.PacketEncoder;
import com.jacoblucas.adventofcode2021.day16.PacketType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends SingleListInputProblem {
    @Override
    public void run() {
        init("day1-input.txt");
        final List<Integer> depthMeasurements = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int increases = getWindowIncreases(depthMeasurements, 1);
        System.out.println(increases);

        increases = getWindowIncreases(depthMeasurements, 3);
        System.out.println(increases);

        increases = getWindowIncreasesPart1V2(depthMeasurements);
        System.out.println(increases);
    }

    public int getWindowIncreases(final List<Integer> depthMeasurements, final int windowSize) {
        int last = -1;
        int windowIncreases = 0;
        for (int i = windowSize-1; i < depthMeasurements.size(); i++) {
            final int windowSum = depthMeasurements.subList(i - (windowSize-1), i+1)
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .sum();

            if (windowSum > last && last > 0) {
                windowIncreases++;
            }
            last = windowSum;
        }
        return windowIncreases;
    }

    public int getWindowIncreasesPart1V2(final List<Integer> depthMeasurements) {
        final List<Packet> literalPackets = depthMeasurements.stream()
                .map(depth -> PacketEncoder.literalPacket(1, depth))
                .collect(Collectors.toList());

        final List<Packet> lessThanPackets = new ArrayList<>();
        for (int i = 1; i < literalPackets.size(); i++) {
            final OperatorPacket operatorPacket = PacketEncoder.operatorPacket(
                    1, PacketType.LESS, 0, ImmutableList.of(literalPackets.get(i - 1), literalPackets.get(i)));
            lessThanPackets.add(operatorPacket);
        }

        final OperatorPacket operatorPacket = PacketEncoder.operatorPacket(1, PacketType.SUM, 0, lessThanPackets);
        System.out.println(PacketEncoder.toHex(operatorPacket));
        return (int) operatorPacket.get();
    }

    public static void main(String[] args) throws IOException {
        new Day1().run();
    }
}
