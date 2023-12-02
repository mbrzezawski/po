package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCounter = 0;

    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (this){
            updatesCounter++;
            System.out.println("MapID: " + worldMap.getID());
            System.out.println("Update #" + updatesCounter + ": " + message);
            System.out.println(worldMap.toString());
        }
    }
}