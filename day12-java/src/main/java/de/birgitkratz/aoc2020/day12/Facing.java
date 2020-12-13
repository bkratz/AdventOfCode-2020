package de.birgitkratz.aoc2020.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Facing {
    N, E, S, W;

    static Map<Facing, List<Facing>> clockwiseTurningMap = new HashMap<>(){{
        put(E, List.of(S, W, N));
        put(S, List.of(W, N, E));
        put(W, List.of(N, E, S));
        put(N, List.of(E, S, W));
    }};

    static Map<Facing, List<Facing>> antiClockwiseTurningMap = new HashMap<>(){{
        put(E, List.of(N, W, S));
        put(S, List.of(E, N, W));
        put(W, List.of(S, E, N));
        put(N, List.of(W, S, E));
    }};

    static Facing getTurningClockwise(Facing start, int degrees) {
        int i = degrees / 90 - 1;
        return clockwiseTurningMap.get(start).get(i);
    }

    static Facing getTurningAntiClockwise(Facing start, int degrees) {
        int i = degrees / 90 - 1;
        return antiClockwiseTurningMap.get(start).get(i);
    }
}
