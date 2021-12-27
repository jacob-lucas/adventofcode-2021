package com.jacoblucas.adventofcode2021.day25;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SeaFloor {
    private Map<Point, SeaCucumber> map;
    private int width, height;

    public SeaFloor() {
        map = new HashMap<>();
    }

    public void init(final List<String> input) {
        height = input.size();
        width = input.get(0).length();
        for (int y = 0; y < height; y++) {
            final String row = input.get(y);
            for (int x = 0; x < width; x++) {
                final Point point = ImmutablePoint.of(x, y);
                SeaCucumberType.of(""+row.charAt(x)).ifPresent(type -> map.put(point, ImmutableSeaCucumber.of(type)));
            }
        }

//        System.out.println("Initialised sea floor:");
//        print();
    }

    private void print() {
        get().forEach(System.out::println);
    }

    public Map<Point, SeaCucumber> getMap() {
        return map;
    }

    /**
     * Every step, the sea cucumbers in the east-facing herd attempt to move forward one location, then the sea cucumbers
     * in the south-facing herd attempt to move forward one location. When a herd moves forward, every sea cucumber in
     * the herd first simultaneously considers whether there is a sea cucumber in the adjacent location it's facing (even
     * another sea cucumber facing the same direction), and then every sea cucumber facing an empty location simultaneously
     * moves into that location.
     */
    public int step() {
        AtomicInteger moves = new AtomicInteger();

        final Map<Point, SeaCucumber> updated = new HashMap<>(map);

        get(SeaCucumberType.EAST)
                .filter(e -> isAdjacentEmpty(e.getKey(), e.getValue().getType()))
                .forEach(e -> {
                    move(e.getKey(), updated);
                    moves.getAndIncrement();
                });

        map = new HashMap<>(updated);

        get(SeaCucumberType.SOUTH)
                .filter(e -> isAdjacentEmpty(e.getKey(), e.getValue().getType()))
                .forEach(e -> {
                    move(e.getKey(), updated);
                    moves.getAndIncrement();
                });

        map = new HashMap<>(updated);
        return moves.get();

//        System.out.println("After step:");
//        print();
    }

    public Point getAdjacent(Point point, SeaCucumberType type) {
        final Point adjacent;
        if (type == SeaCucumberType.EAST) {
            adjacent = ImmutablePoint.of((point.getX() + 1) % width, point.getY());
        } else {
            adjacent = ImmutablePoint.of(point.getX(), (point.getY() + 1) % height);
        }
        return adjacent;
    }

    public boolean isAdjacentEmpty(final Point point, SeaCucumberType type) {
        final Point adjacent = getAdjacent(point, type);
        return !map.containsKey(adjacent);
    }

    public void move(final Point point, final Map<Point, SeaCucumber> map) {
        final SeaCucumber seaCucumber = map.get(point);
        map.remove(point);
        map.put(getAdjacent(point, seaCucumber.getType()), seaCucumber);
    }

    public Stream<Map.Entry<Point, SeaCucumber>> get(final SeaCucumberType type) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().getType() == type);
    }

    public List<String> get() {
        final List<String> strings = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            final StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                final Point point = ImmutablePoint.of(x, y);
                if (map.containsKey(point)) {
                    sb.append(map.get(point).getType().getId());
                } else {
                    sb.append(".");
                }
            }
            strings.add(sb.toString());
        }
        return strings;
    }
}
