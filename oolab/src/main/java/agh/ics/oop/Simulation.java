package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{
    private final List<MoveDirection> moves;
    private final List<Animal> animals;
    private final WorldMap map;
    private List<Animal> createAnimals(List<Vector2d> initialPositions) {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d startingPosition : initialPositions) {
            animals.add(new Animal(startingPosition));
        }
        return animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    public List<MoveDirection> getMoves() {
        return moves;
    }
    public WorldMap getMap() {return map;}
    public Simulation(List<MoveDirection> moves, List<Vector2d> initialPositions, WorldMap map) {
        this.moves = moves;
        this.animals = createAnimals(initialPositions);
        this.map = map;
    }
    public void run(){
        int movesNum = moves.size();
        for (Animal animal : animals) {
            try {
                map.place(animal);
            } catch (PositionAlreadyOccupiedException ex) {
                System.out.println("PositionAlreadyOccupiedException: " + ex.getMessage());
            }
        }
        int animalsNum = animals.size();
        for(int i = 0; i < movesNum;i++){
            int currAnimal = i % animalsNum;
            map.move(animals.get(currAnimal),moves.get(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}





