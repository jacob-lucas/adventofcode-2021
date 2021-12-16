package com.jacoblucas.adventofcode2021;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.List;

public abstract class SingleListInputProblem implements Runnable {
    protected List<String> input;

    public void init(final String filename) {
        try {
            input = InputReader.read(filename);
        } catch (IOException e) {
            System.out.println("Unable to initialise from " + filename);
            e.printStackTrace();
        }
    }

    public void init(final String path, final String filename) {
        try {
            input = InputReader.readFile(path, filename);
        } catch (IOException e) {
            System.out.println("Unable to initialise from " + filename);
            e.printStackTrace();
        }
    }

    // Execute the solution for the problem
    @Override
    public abstract void run();
}
