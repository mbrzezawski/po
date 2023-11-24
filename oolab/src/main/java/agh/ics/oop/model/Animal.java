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

    public Animal(Vector2d location){
        this.orientation = MapDirection.NORTH;
        this.position = location;
    }

    public String toString(){
        return this.position.toString() + " " + this.orientation.toString();
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT:
                this.orientation = orientation.next();
                break;
            case LEFT:
                this.orientation = orientation.previous();
                break;
            case FORWARD:
                Vector2d newPositionForward = this.position.add(this.orientation.toUnitVector());
                if (newPositionForward.follows(new Vector2d(0, 0)) && newPositionForward.precedes(new Vector2d(4, 4))) {
                    this.position = newPositionForward;
                }
                break;
            case BACKWARD:
                Vector2d newPositionBackward = this.position.substract(this.orientation.toUnitVector());
                if (newPositionBackward.follows(new Vector2d(0, 0)) && newPositionBackward.precedes(new Vector2d(4, 4))) {
                    this.position = newPositionBackward;
                }
                break;
        }
    }
}
