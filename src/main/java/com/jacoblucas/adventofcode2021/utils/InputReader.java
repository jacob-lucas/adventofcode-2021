package com.jacoblucas.adventofcode2021.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputReader {
    private static final String DEFAULT_PATH = "src/main/resources/";

    public static List<String> read(final String filename) throws IOException {
        return readFile(DEFAULT_PATH, filename);
    }

    public static List<List<String>> readGroups(final String filename) throws IOException {
        return readGroups(DEFAULT_PATH, filename);
    }

    public static List<List<String>> readGroups(final String path, final String filename) throws IOException {
        final List<String> lines = readFile(path, filename);
        final List<Integer> indices = new ArrayList<>();
        indices.add(-1);
        IntStream.range(0, lines.size())
                .filter(i -> lines.get(i).isEmpty())
                .forEach(indices::add);
        if (indices.get(indices.size() - 1) < lines.size()) {
            indices.add(lines.size());
        }
        return IntStream.range(0, indices.size() - 1)
                .mapToObj(i -> lines.subList(indices.get(i) + 1, indices.get(i + 1)))
                .collect(Collectors.toList());
    }

    public static List<String> readFile(final String path, final String filename) throws IOException {
        return Files.readAllLines(Paths.get(path + filename), StandardCharsets.UTF_8);
    }
}
