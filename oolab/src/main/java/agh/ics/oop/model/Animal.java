package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(Vector2d position){
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    public void setPosition(Vector2d newPosition){
        this.position = newPosition;
    }
    public void setOrientation(MapDirection newOrientation){
        this.orientation = newOrientation;
    }
    public String toString() {
        switch (orientation) {
            case NORTH:
                return "^";
            case SOUTH:
                return "v";
            case WEST:
                return "<";
            case EAST:
                return ">";
            default:
                return "";
        }
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator moveValidator){
            switch(direction) {
                case RIGHT -> this.orientation = orientation.next();
                case LEFT -> this.orientation = orientation.previous();
                case FORWARD -> {
                    if(moveValidator.canMoveTo(position.add(orientation.toUnitVector()))){
                        this.position = position.add(orientation.toUnitVector());
                    }
                }
                case BACKWARD -> {
                    if(moveValidator.canMoveTo(position.add(orientation.toUnitVector().opposite()))) {
                        this.position = position.add(orientation.toUnitVector().opposite());
                    }
                }
            }
    }
}

