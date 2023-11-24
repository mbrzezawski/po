package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height))) {
            return !isOccupied(position);
        }
        return false;
    }
    public WorldElement objectAt(Vector2d position) {
        for(Animal animal : animals.values()){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    protected Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    protected Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }
}

//    private final int width;
//    private final int height;
//    private final Map<Vector2d, Animal> animals = new HashMap<>();
//
//    public RectangularMap(int width, int height) {
//        this.width = width;
//        this.height = height;
//    }
//
//    public boolean canMoveTo(Vector2d position) {
//        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height))) {
//            return !isOccupied(position);
//        }
//        return false;
//    }
//
//    public boolean place(Animal animal) {
//        if (canMoveTo(animal.getPosition())) {
//            animals.put(animal.getPosition(), animal);
//            return true;
//        }
//        return false;
//    }
//
//    public void move(Animal animal, MoveDirection direction) {
//        Vector2d newPosition = animal.getPosition().add(direction.toUnitVector());
//
//        if (canMoveTo(newPosition)) {
//            animals.remove(animal.getPosition());
//            animal.move(direction,this);
//            animals.put(animal.getPosition(), animal);
//        }
//    }
//
//    public boolean isOccupied(Vector2d position) {
//        return objectAt(position) != null;
//    }
//
//    public WorldElement objectAt(Vector2d position) {
//        return animals.get(position);
//    }
//
//    public String toString() {
//        MapVisualizer mapVisualizer = new MapVisualizer(this);
//        Vector2d lowerLeft = new Vector2d(0, 0);
//        Vector2d upperRight = new Vector2d(width , height);
//        return mapVisualizer.draw(lowerLeft, upperRight);
//    }

