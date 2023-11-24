package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.Arrays;
import java.util.List;


public class World {

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,2));
        WorldMap map = new RectangularMap(5, 5);
        GrassField grassmap = new GrassField(10);

        Simulation simulation = new Simulation(directions, positions, grassmap);
        simulation.run();
        System.out.println(map.toString());
    }

    public static void run(List<MoveDirection> directions) {
        for (MoveDirection direction : directions) {
            switch(direction){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                default:
                    break;
            }
        }
    }
}

