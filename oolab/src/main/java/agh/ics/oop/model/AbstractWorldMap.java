package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();
    protected List<MapChangeListener> observers = new ArrayList<>();
    protected Map<Vector2d, Animal> animals;
    private UUID id;
    public AbstractWorldMap() {
        this.animals = new HashMap<>();
        this.id = UUID.randomUUID();
    }
    public UUID getID(){
        return this.id;
    }
    public Boundary getCurrentBounds() {
        return new Boundary(getLowerLeft(), getUpperRight());
    }
    public abstract boolean canMoveTo(Vector2d position);
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(),animal);
            notifyObservers("Placed animal at " + animal.getPosition());
            return true;
        }
        else {
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
    }

    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal);
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
        notifyObservers("Moved animal to " + animal.getPosition() + " in direction " + direction);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public abstract WorldElement objectAt(Vector2d position);
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Boundary boundary = getCurrentBounds();
        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }
}