package com.jacoblucas.adventofcode2021.day6;

import java.util.ArrayList;
import java.util.List;

public class School {
    private final List<Lanternfish> fish;

    public School(final List<Lanternfish> fish) {
        this.fish = fish;
    }

    // Simulate Lanternfish for n days
    public int simulate(final int n) {
        for (int i = 0; i < n; i++) {
            final List<Lanternfish> newFish = new ArrayList<>();
            for (final Lanternfish f : fish) {
                boolean maturing = f.isMaturing();
                int timer = f.grow();
                if (timer == Lanternfish.REGEN_DAYS - 1 && !maturing) {
                    newFish.add(new Lanternfish());
                }
            }
            fish.addAll(newFish);
        }
        return fish.size();
    }

    public List<Lanternfish> getFish() {
        return fish;
    }
}
