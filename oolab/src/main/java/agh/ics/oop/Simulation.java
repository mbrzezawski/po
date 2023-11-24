package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Vector2d> initialPositions;
    private final List<MoveDirection> moves;
    private final List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }
    public List<MoveDirection> getMoves() {
        return moves;
    }
    public Simulation(List<MoveDirection> moves, List<Vector2d> initialPositions) {
        this.initialPositions = initialPositions;
        this.moves = moves;
        this.animals = createAnimals(initialPositions);
    }
    private List<Animal> createAnimals(List<Vector2d> initialPositions) {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d initialPosition : initialPositions) {
            animals.add(new Animal(initialPosition));
        }
        return animals;
    }
    public void run() {
        int numAnimals = animals.size();
        int numMoves = moves.size();

        for (int i = 0; i < numMoves; i++) {
            Animal currentAnimal = animals.get(i % numAnimals);
            MoveDirection currentMove = moves.get(i);
            currentAnimal.move(currentMove);
            System.out.println("Zwierzę " + i % numAnimals + ": " + currentAnimal.toString());
        }
    }
}


