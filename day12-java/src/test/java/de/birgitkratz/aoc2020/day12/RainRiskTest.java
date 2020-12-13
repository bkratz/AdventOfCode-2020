package de.birgitkratz.aoc2020.day12;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RainRiskTest {

    RainRisk rainRisk = new RainRisk(
            new RainRisk.Ship(Facing.E, 0, 0),
            new RainRisk.Waypoint(1, 10, 0, 0));

    @Test
    void processPart1() throws IOException {
        assertThat(rainRisk.processPart1()).isEqualTo(2228);
    }

    @Test
    void processPart2() throws IOException {
        assertThat(rainRisk.processPart2()).isEqualTo(42908);
    }

    @Test
    void processInputPart1() {
        List<String> input = List.of("F10", "N3", "F7", "R90", "F11");

        assertThat(rainRisk.processInputPart1(input)).isEqualTo(25);
    }

    @Test
    void processInputPart2() {
        List<String> input = List.of("F10", "N3", "F7", "R90", "F11");

        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, -72, 214);
        RainRisk.Waypoint expectedWaypoint = new RainRisk.Waypoint(0, 4, 10, 0);

        assertThat(rainRisk.processInputPart2(input)).isEqualTo(286);
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
        assertThat(rainRisk.getWaypoint()).isEqualTo(expectedWaypoint);
    }

    @Test
    void shipFacingEastWithManhattenPosition0_0() {
        assertThat(rainRisk.getShip()).isNotNull();
        assertThat(rainRisk.getShip().getFacingDirection()).isEqualTo(Facing.E);
        assertThat(rainRisk.getShip().getManhattenDistance()).isEqualTo(0);
    }

    @Test
    void moveShip_F10_Part1() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, 0, 10);

        rainRisk.moveShipPart1("F10");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
    }

    @Test
    void moveShip_N3_Part1() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, 3, 0);

        rainRisk.moveShipPart1("N3");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
    }

    @Test
    void moveShip_R90_Part1() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.S, 0, 0);

        rainRisk.moveShipPart1("R90");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
    }

    @Test
    void moveShip_F10_Part2() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, 10, 100);
        RainRisk.Waypoint expectedWaypoint = new RainRisk.Waypoint(1, 10, 0, 0);

        rainRisk.moveShipPart2("F10");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
        assertThat(rainRisk.getWaypoint()).isEqualTo(expectedWaypoint);
    }

    @Test
    void moveWaypoint_N3_Part2() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, 0, 0);
        RainRisk.Waypoint expectedWaypoint = new RainRisk.Waypoint(4, 10, 0, 0);

        rainRisk.moveShipPart2("N3");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
        assertThat(rainRisk.getWaypoint()).isEqualTo(expectedWaypoint);
    }

    @Test
    void rotateWaypoint_R90_Part2() {
        RainRisk.Ship expectedShip = new RainRisk.Ship(Facing.E, 0, 0);
        RainRisk.Waypoint expectedWaypoint = new RainRisk.Waypoint(0, 1, 10, 0);

        rainRisk.moveShipPart2("R90");
        assertThat(rainRisk.getShip()).isEqualTo(expectedShip);
        assertThat(rainRisk.getWaypoint()).isEqualTo(expectedWaypoint);
    }
}