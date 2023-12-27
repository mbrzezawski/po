package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();
        for(String arg : args){
            switch(arg) {
                case "f", "forward" -> directions.add(MoveDirection.FORWARD);
                case "b", "backward" -> directions.add(MoveDirection.BACKWARD);
                case "r", "right" -> directions.add(MoveDirection.RIGHT);
                case "l", "left" -> directions.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
            }
        }
        return directions;
    }
}


