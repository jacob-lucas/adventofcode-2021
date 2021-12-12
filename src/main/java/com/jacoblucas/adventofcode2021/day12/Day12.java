package com.jacoblucas.adventofcode2021.day12;

import com.jacoblucas.adventofcode2021.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day12-input.txt");
        final Map<String, Cave> caves = Day12.parse(input);
        final List<List<Cave>> paths = Day12.findAllPaths("start", "end", caves);
        System.out.println(paths.size());
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
            final Map<String, Cave> caveMap
    ) {
        final List<List<Cave>> paths = new ArrayList<>();
        List<Cave> path = new ArrayList<>();
        path.add(caveMap.get(src));
        findAllPaths(src, dest, caveMap, path, paths);

        return paths;
    }

    private static void findAllPaths(
            final String id,
            final String dest,
            final Map<String, Cave> caveMap,
            final List<Cave> path,
            final List<List<Cave>> foundPaths
    ) {
        final List<Cave> localPath = new ArrayList<>(path);
        if (id.equals(dest)) {
            foundPaths.add(localPath);
        }

        final Cave cave = caveMap.get(id);
        for (final Cave connectedCave : cave.getConnections()) {
            final String connectedCaveId = connectedCave.getId();

            if (!connectedCave.isBig() && path.contains(connectedCave)) {
                continue;
            }

            localPath.add(connectedCave);
            findAllPaths(connectedCaveId, dest, caveMap, localPath, foundPaths);
            localPath.remove(connectedCave);
        }
    }
}
