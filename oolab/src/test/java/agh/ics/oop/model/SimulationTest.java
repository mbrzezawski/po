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

        return new Simulation(moves, initialPositions);
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
}
