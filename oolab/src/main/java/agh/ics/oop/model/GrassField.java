package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final List<Grass> grassList;

    public GrassField(int grassCount) {
        super();
        this.grassCount = grassCount;
        this.grassList = new ArrayList<>();
        placeGrass();
    }

    private void placeGrass() {
        for (int i = 0; i < grassCount; i++) {
            Vector2d grassPosition;
            do {
                grassPosition = new Vector2d((int) (Math.random() * Math.sqrt(grassCount * 10)),
                        (int) (Math.random() * Math.sqrt(grassCount * 10)));
            } while (isOccupied(grassPosition));
            Grass grass = new Grass(grassPosition);
            grassList.add(grass);
        }
    }
    public WorldElement objectAt(Vector2d position) {
        for (Animal animal : animals.values()) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        for (Grass grass : grassList) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }
    protected Vector2d getLowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Animal animal : animals.values()) {
            minX = Math.min(minX, animal.getPosition().getX());
            minY = Math.min(minY, animal.getPosition().getY());
        }
        for (Grass grass : grassList) {
            minX = Math.min(minX, grass.getPosition().getX());
            minY = Math.min(minY, grass.getPosition().getY());
        }
        return new Vector2d(minX, minY);
    }

    protected Vector2d getUpperRight() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Animal animal : animals.values()) {
            maxX = Math.max(maxX, animal.getPosition().getX());
            maxY = Math.max(maxY, animal.getPosition().getY());
        }
        for (Grass grass : grassList) {
            maxX = Math.max(maxX, grass.getPosition().getX());
            maxY = Math.max(maxY, grass.getPosition().getY());
        }
        return new Vector2d(maxX, maxY);
    }
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)){
            return true;
        }
        else if (objectAt(position).getClass() == Animal.class){
            return false;
        }
        return true;

    }
    public List<WorldElement> getElements(){
        return new ArrayList<>(grassList);
    }
//    private final int grassCount;
//    private final List<Grass> grassList;
//    private final Set<Animal> animals;
//
//    public GrassField(int grassCount) {
//        this.grassCount = grassCount;
//        this.grassList = new ArrayList<>();
//        this.animals = new HashSet<>();
//        placeGrass();
//    }
//
//    private void placeGrass() {
//        for (int i = 0; i < grassCount; i++) {
//            Vector2d grassPosition;
//            do {
//                grassPosition = new Vector2d((int) (Math.random() * Math.sqrt(grassCount * 10)),
//                        (int) (Math.random() * Math.sqrt(grassCount * 10)));
//            } while (isOccupied(grassPosition));
//            Grass grass = new Grass(grassPosition);
//            grassList.add(grass);
//        }
//    }
//
//    public boolean place(Animal animal) {
//        if (canMoveTo(animal.getPosition())) {
//            animals.add(animal);
//            return true;
//        }
//        return false;
//    }
//
//    public void move(Animal animal, MoveDirection direction) {
//        animals.remove(animal);
//        animal.move(direction, this);
//        animals.add(animal);
//    }
//
//    public boolean isOccupied(Vector2d position) {
//        return objectAt(position) != null;
//    }
//
//    public WorldElement objectAt(Vector2d position) {
//        for (Animal animal : animals) {
//            if (animal.getPosition().equals(position)) {
//                return animal;
//            }
//        }
//        for (Grass grass : grassList) {
//            if (grass.getPosition().equals(position)) {
//                return grass;
//            }
//        }
//        return null;
//    }
//
//    public boolean canMoveTo(Vector2d position) {
//        return !isOccupied(position);
//    }
//
//    public String toString() {
//        MapVisualizer visualizer = new MapVisualizer(this);
//        Vector2d lowerLeft = getLowerLeft();
//        Vector2d upperRight = getUpperRight();
//        return visualizer.draw(lowerLeft, upperRight);
//    }
//
//    private Vector2d getLowerLeft() {
//        int minX = Integer.MAX_VALUE;
//        int minY = Integer.MAX_VALUE;
//        for (Animal animal : animals) {
//            minX = Math.min(minX, animal.getPosition().getX());
//            minY = Math.min(minY, animal.getPosition().getY());
//        }
//        for (Grass grass : grassList) {
//            minX = Math.min(minX, grass.getPosition().getX());
//            minY = Math.min(minY, grass.getPosition().getY());
//        }
//        return new Vector2d(minX, minY);
//    }
//
//    private Vector2d getUpperRight() {
//        int maxX = Integer.MIN_VALUE;
//        int maxY = Integer.MIN_VALUE;
//        for (Animal animal : animals) {
//            maxX = Math.max(maxX, animal.getPosition().getX());
//            maxY = Math.max(maxY, animal.getPosition().getY());
//        }
//        for (Grass grass : grassList) {
//            maxX = Math.max(maxX, grass.getPosition().getX());
//            maxY = Math.max(maxY, grass.getPosition().getY());
//        }
//        return new Vector2d(maxX, maxY);
//    }
}
