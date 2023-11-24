package agh.ics.oop.model;
public enum MoveDirection{
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT;

    public Vector2d toUnitVector() {
        switch (this) {
            case FORWARD:
                return new Vector2d(0, 1);
            case BACKWARD:
                return new Vector2d(0, -1);
            case LEFT:
                return new Vector2d(-1, 0);
            case RIGHT:
                return new Vector2d(1, 0);
            default:
                return new Vector2d(0, 0);
        }
    }
}
