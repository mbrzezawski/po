package agh.ics.oop.model;

import java.util.Objects;

import static java.lang.Math.abs;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }

    public boolean precedes(Vector2d other) {
        return getX() <= other.getX() && getY() <= other.getY();
    }

    public boolean follows(Vector2d other) {
        return getX() >= other.getX() && getY() >= other.getY();
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(getX() + other.getX(), getY() + other.getY());
    }
    public Vector2d substract(Vector2d other){
        return new Vector2d(getX() - other.getX(), getY() - other.getY());
    }
    public Vector2d upperRight(Vector2d other){
        int maxX = Math.max(getX(), other.getX());
        int maxY = Math.max(getY(), other.getY());
        return new Vector2d(maxX, maxY);
    }
    public Vector2d lowerLeft(Vector2d other){
        int minX = Math.min(getX(), other.getX());
        int minY = Math.min(getY(), other.getY());
        return new Vector2d(minX, minY);
    }
    public Vector2d opposite(){
        return new Vector2d(-getX(), -getY());
    }
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if(!(other instanceof Vector2d vector2d)){
            return false;
        }
        return getX() == vector2d.getX() && getY() == vector2d.getY();
    }
    public int hashCode(){
        return Objects.hash(x,y);
    }
}
