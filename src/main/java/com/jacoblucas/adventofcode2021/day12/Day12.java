package com.jacoblucas.adventofcode2021.day12;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day12-input.txt");
        final Map<String, Cave> caves = Day12.parse(input);

        // Part 1
        final List<List<Cave>> paths1 = Day12.findAllPaths("start", "end", caves, Day12::part1VisitRule);
        System.out.println(paths1.size());

        // Part 2
        final List<List<Cave>> paths2 = Day12.findAllPaths("start", "end", caves, Day12::part2VisitRule);
        System.out.println(paths2.size());
    }

    public static Map<String, Cave> parse(final List<String> input) {
        final Map<String, Cave> caves = new HashMap<>();
        for (final String connection : input) {
            final String[] caveIds = connection.split("-");
            final String id1 = caveIds[0];
            final String id2 = caveIds[1];

            final Cave cave1 = caves.getOrDefault(id1, new Cave(id1));
            final Cave cave2 = caves.getOrDefault(id2, new Cave(id2));
            cave1.connect(cave2);
            cave2.connect(cave1);
            caves.put(id1, cave1);
            caves.put(id2, cave2);
        }

        return caves;
    }

    public static List<List<Cave>> findAllPaths(
            final String src,
            final String dest,
            final Map<String, Cave> caveMap,
            final BiFunction<Cave, List<Cave>, Boolean> visitFunc
    ) {
        final List<List<Cave>> paths = new ArrayList<>();
        List<Cave> path = new ArrayList<>();
        path.add(caveMap.get(src));
        findAllPaths(src, dest, caveMap, path, paths, visitFunc);

        return paths;
    }

    private static void findAllPaths(
            final String id,
            final String dest,
            final Map<String, Cave> caveMap,
            final List<Cave> path,
            final List<List<Cave>> foundPaths,
            final BiFunction<Cave, List<Cave>, Boolean> visitFunc
    ) {
        final List<Cave> localPath = new ArrayList<>(path);
        if (id.equals(dest)) {
            foundPaths.add(localPath);
            return;
        }

        final Cave cave = caveMap.get(id);
        for (final Cave connectedCave : cave.getConnections()) {
            final String connectedCaveId = connectedCave.getId();

            if (visitFunc.apply(connectedCave, localPath)) {
                localPath.add(connectedCave);
                findAllPaths(connectedCaveId, dest, caveMap, localPath, foundPaths, visitFunc);
                localPath.remove(connectedCave);
            }
        }
    }

    static boolean part1VisitRule(final Cave cave, final List<Cave> path) {
        return cave.isBig() || !path.contains(cave);
    }

    static boolean part2VisitRule(final Cave cave, final List<Cave> path) {
        if (cave.getId().equals("start")) {
            return false;
        } else if (!path.contains(cave)) {
            return true;
        } else if (!cave.isBig()) {
            final Map<String, List<Cave>> visitCounts = path.stream()
                    .filter(c -> !c.isBig())
                    .collect(Collectors.groupingBy(Cave::getId));

            return visitCounts.values().stream().noneMatch(cs -> cs.size() == 2);
        } else {
            return true;
        }
    }
}
