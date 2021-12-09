package com.jacoblucas.adventofcode2021.day05;

import com.jacoblucas.adventofcode2021.interfaces.ImmutablePoint;
import com.jacoblucas.adventofcode2021.interfaces.Point;
import org.immutables.value.Value;

@Value.Immutable
public abstract class Vent {
    public abstract Point getPoint1();

    public abstract Point getPoint2();

    @Value.Derived
    public int getX1() {
        return getPoint1().getX();
    }

    @Value.Derived
    public int getY1() {
        return getPoint1().getY();
    }

    @Value.Derived
    public int getX2() {
        return getPoint2().getX();
    }

    @Value.Derived
    public int getY2() {
        return getPoint2().getY();
    }

    @Value.Derived
    public boolean isHorizontal() {
        return getY1() == getY2();
    }

    @Value.Derived
    public boolean isVertical() {
        return getX1() == getX2();
    }

    public static Vent parse(final String str) {
        final String[] parts = str.split(" ");
        final String[] coord1 = parts[0].split(",");
        final String[] coord2 = parts[2].split(",");
        return ImmutableVent.builder()
                .point1(ImmutablePoint.of(Integer.parseInt(coord1[0]), Integer.parseInt(coord1[1])))
                .point2(ImmutablePoint.of(Integer.parseInt(coord2[0]), Integer.parseInt(coord2[1])))
                .build();
    }
}
