package de.birgitkratz.aoc2020.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class RainRisk {

    private final Ship ship;
    private final Waypoint waypoint;

    private final Path path =
            Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());

    public RainRisk(final Ship ship, final Waypoint waypoint) {
        this.ship = ship;
        this.waypoint = waypoint;
    }

    public int processPart1() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart1(lines);
    }

    public int processPart2() throws IOException {
        final List<String> lines = Files.readAllLines(path);
        return processInputPart2(lines);
    }

    public int processInputPart1(final List<String> input) {
        input.forEach(this::moveShipPart1);
        return ship.getManhattenDistance();
    }

    public int processInputPart2(final List<String> input) {
        input.forEach(this::moveShipPart2);
        return ship.getManhattenDistance();
    }

    public void moveShipPart1(final String instruction) {
        final String direction = instruction.substring(0, 1);
        final int amount = Integer.parseInt(instruction.substring(1));

        if (List.of("F", "N", "S", "E", "W").contains(direction)) {
            ship.movePart1(direction, amount);
        }
        if (List.of("R", "L").contains(direction)) {
            ship.turnPart1(direction, amount);
        }
    }

    public void moveShipPart2(final String instruction) {
        final String direction = instruction.substring(0, 1);
        final int amount = Integer.parseInt(instruction.substring(1));

        if ("F".equals(direction)) {
            ship.movePart2(waypoint, amount);
        }
        if (List.of("N", "S", "E", "W").contains(direction)) {
            waypoint.movePart2(direction, amount);
        }
        if (List.of("R", "L").contains(direction)) {
            waypoint.turnPart2(direction, amount);
        }
    }

    public Ship getShip() {
        return ship;
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    static class Ship {
        private Facing facing;
        private int northSouthPosition;
        private int eastWestPosition;

        public Ship(final Facing facing, final int northSouthPosition, final int eastWestPosition) {
            this.facing = facing;
            this.northSouthPosition = northSouthPosition;
            this.eastWestPosition = eastWestPosition;
        }

        public Facing getFacingDirection() {
            return facing;
        }

        public int getManhattenDistance() {
            return Math.abs(northSouthPosition) + Math.abs(eastWestPosition);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Ship ship = (Ship) o;
            return northSouthPosition == ship.northSouthPosition && eastWestPosition == ship.eastWestPosition
                    && facing == ship.facing;
        }

        @Override
        public int hashCode() {
            return Objects.hash(facing, northSouthPosition, eastWestPosition);
        }

        public void movePart1(String direction, final int amount) {
            if ("F".equals(direction)) {
                switch (facing) {
                case E -> eastWestPosition += amount;
                case W -> eastWestPosition -= amount;
                case N -> northSouthPosition += amount;
                case S -> northSouthPosition -= amount;
                }
            } else {
                switch (direction) {
                case "N" -> northSouthPosition += amount;
                case "S" -> northSouthPosition -= amount;
                case "E" -> eastWestPosition += amount;
                case "W" -> eastWestPosition -= amount;
                }
            }
        }

        public void movePart2(final Waypoint waypoint, final int amount) {
            eastWestPosition += waypoint.east * amount;
            eastWestPosition -= waypoint.west * amount;
            northSouthPosition += waypoint.north * amount;
            northSouthPosition -= waypoint.south * amount;
        }

        public void turnPart1(final String direction, final int amount) {
            switch (direction) {
            case "R" -> facing = Facing.getTurningClockwise(facing, amount);
            case "L" -> facing = Facing.getTurningAntiClockwise(facing, amount);
            }
        }
    }

    static class Waypoint {

        private int north;
        private int east;
        private int south;
        private int west;

        public Waypoint(final int north, final int east, final int south, final int west) {
            this.north = north;
            this.east = east;
            this.south = south;
            this.west = west;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Waypoint waypoint = (Waypoint) o;
            return north == waypoint.north && east == waypoint.east && south == waypoint.south && west == waypoint.west;
        }

        @Override
        public int hashCode() {
            return Objects.hash(north, east, south, west);
        }

        public void movePart2(final String direction, final int amount) {
            switch (direction) {
            case "N" -> north += amount;
            case "S" -> south += amount;
            case "E" -> east += amount;
            case "W" -> west += amount;
            }
        }

        public void turnPart2(final String direction, final int amount) {
            switch (direction) {
            case "R" -> turnClockwise(amount);
            case "L" -> turnClockwise(amount *(-1));
            }
        }

        private void turnClockwise(final int amount) {
            int tmpNorth = north;
            int tmpEast = east;
            switch (amount) {
            case 90, -270 -> {
                north = west;
                west = south;
                south = east;
                east = tmpNorth;
            }
            case 180, -180 -> {
                north = south;
                east = west;
                west = tmpEast;
                south = tmpNorth;
            }
            case 270, -90 -> {
                north = east;
                east = south;
                south = west;
                west = tmpNorth;
            }
            }
        }
    }
}
