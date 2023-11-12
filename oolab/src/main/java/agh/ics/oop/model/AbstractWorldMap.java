package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractWorldMap implements WorldMap{
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();
    protected Set<Animal> animals;
    public AbstractWorldMap() {
        this.animals = new HashSet<>();
    }
    public abstract boolean canMoveTo(Vector2d position);
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal);
        animal.move(direction, this);
        animals.add(animal);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public abstract WorldElement objectAt(Vector2d position);
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
