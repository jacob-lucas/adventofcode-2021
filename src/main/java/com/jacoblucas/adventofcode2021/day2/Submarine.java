package com.jacoblucas.adventofcode2021.day2;

import static com.jacoblucas.adventofcode2021.day2.Command.DOWN;
import static com.jacoblucas.adventofcode2021.day2.Command.FORWARD;
import static com.jacoblucas.adventofcode2021.day2.Command.UP;

public class Submarine {
    private int position;
    private int depth;
    private int aim;

    public Submarine() {
        this.position = 0;
        this.depth = 0;
        this.aim = 0;
    }

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    public int getAim() {
        return aim;
    }

    public void execute(final String commandStr) {
        execute(commandStr, 2);
    }

    public void execute(final String commandStr, final int version) {
        final String[] parts = commandStr.split(" ");
        final Command command = Command.valueOf(parts[0].toUpperCase());
        final int n = Integer.parseInt(parts[1]);

        if (command == FORWARD) {
            forward(n);
        } else if (command == DOWN) {
            if (version == 1) depth(n); else down(n);
        } else if (command == UP) {
            if (version == 1) depth(n * -1); else up(n);
        }
    }

    private void forward(final int n) {
        position += n;
        depth(aim * n);
    }

    private void depth(final int n) {
        depth += n;
    }

    private void up(final int n) {
        aim -= n;
    }

    private void down(final int n) {
        aim += n;
    }
}
