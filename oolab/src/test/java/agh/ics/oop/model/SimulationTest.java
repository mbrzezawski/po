package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    private Simulation simulation;

    @Before
    public Simulation setUp() {
        List<Vector2d> initialPositions = Arrays.asList(
                new Vector2d(2, 2),
                new Vector2d(3, 3)
        );

        List<MoveDirection> moves = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.LEFT
        );
        WorldMap map = new RectangularMap(5, 5);
        return new Simulation(moves, initialPositions, map);
    }

    @Test
    public void AnimalOrientation() {
        simulation = setUp();
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
        assertEquals(MapDirection.NORTH, animals.get(1).getOrientation());
    }
    @Test
    public void AnimalPositions() {
        simulation = setUp();
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        assertEquals(new Vector2d(2, 4), animals.get(0).getPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getPosition());
    }

    @Test
    public void AnimalInBoundaries() {
        simulation = setUp();
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        for (Animal animal : animals) {
            assertTrue(animal.getPosition().follows(new Vector2d(0, 0)));
            assertTrue(animal.getPosition().precedes(new Vector2d(4, 4)));
        }
    }

    @Test
    public void InputInterpretation() {
        simulation = setUp();
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        assertEquals(2, animals.size());
        assertEquals(4, simulation.getMoves().size());
    }
    @Test
    public void testSimulation() {
        simulation = setUp();
        simulation.run();

        String expectedMapState = " y\\x  0 1 2 3 4 5\n" +
                "  6: -------------\n" +
                "  5: | | | | | | |\n" +
                "  4: | | |^| | | |\n" +
                "  3: | | | |^| | |\n" +
                "  2: | | | | | | |\n" +
                "  1: | | | | | | |\n" +
                "  0: | | | | | | |\n" +
                " -1: -------------\n";
        expectedMapState = expectedMapState.replaceAll("\n", "\r\n");

        assertEquals(expectedMapState, simulation.getMap().toString());
    }
}
