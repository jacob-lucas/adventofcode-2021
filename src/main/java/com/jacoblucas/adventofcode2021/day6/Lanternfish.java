package com.jacoblucas.adventofcode2021.day6;

public class Lanternfish {
    public static final int REGEN_DAYS = 7;

    private boolean maturing;
    private int timer;

    public Lanternfish() {
        this(REGEN_DAYS - 1, true);
    }

    public Lanternfish(final int timer) {
        this(timer, false);
    }

    public Lanternfish(final int timer, final boolean maturing) {
        this.timer = maturing ? timer + 2 : timer;
        this.maturing = maturing;
    }

    public int grow() {
        timer--;
        if (timer < 0) {
            timer = REGEN_DAYS - 1;
        }
        if (timer < REGEN_DAYS) {
            maturing = false;
        }
        return timer;
    }

    public int getTimer() {
        return timer;
    }

    public boolean isMaturing() {
        return maturing;
    }
}
