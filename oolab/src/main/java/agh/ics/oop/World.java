package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class World {

    public static void main(String[] args) throws InterruptedException {
        List<MoveDirection> directions = null;
        try {
            directions = OptionsParser.parse(args);
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgumentException: " + ex.getMessage());
            System.exit(1);
        }

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 2));

        RectangularMap rectangularMap = new RectangularMap(5, 5);
        GrassField grassMap = new GrassField(10);


        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        grassMap.addObserver(consoleMapDisplay);
        rectangularMap.addObserver(consoleMapDisplay);
        int numberOfSimulations = 10;
        List<Simulation> simulations = new ArrayList<>();
        for (int i = 0; i < numberOfSimulations; i++) {
            GrassField grassField = new GrassField(10);
            grassField.addObserver(consoleMapDisplay);
            simulations.add(new Simulation(directions, positions, grassField));

        }
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runSync();
        simulationEngine.awaitSimulationsEnd();

        System.out.println("System zakończył działanie");
    }
        public static void run (List < MoveDirection > directions) {
            for (MoveDirection direction : directions) {
                switch (direction) {
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

