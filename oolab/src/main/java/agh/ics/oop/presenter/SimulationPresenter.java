package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;
    @FXML
    private TextField movesField;
    @FXML
    private Label messageLabel;
    @FXML
    private GridPane mapGrid;


    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        return label;
    }

    public void drawMap(WorldMap worldMap) {
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        Vector2d topRight = boundary.upperRight();
        Vector2d bottomLeft = boundary.lowerLeft();

        int gridWidth = topRight.getX() - bottomLeft.getX() + 2;
        int gridHeight = topRight.getY() - bottomLeft.getY() + 2;
        mapGrid.add(createLabel("y \\ x"), 0, 0);


        int CELL_WIDTH = 40;
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        int CELL_HEIGHT = 40;
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));



        // creating axis x and axis y

        int leftStart = bottomLeft.getX();

        for (int i = 1; i < gridWidth; i++) {
            mapGrid.add(createLabel(String.valueOf(leftStart)), i, 0);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            leftStart++;
        }

        int upperStart = topRight.getY();
        for (int i = 1; i < gridHeight; i++) {
            mapGrid.add(createLabel(String.valueOf(upperStart)), 0, i);
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            upperStart--;
        }

        // filling map with actual objects
        for (int i = bottomLeft.getY(); i <= topRight.getY(); i++) {
            for (int j = bottomLeft.getX(); j <= topRight.getX(); j++) {
                Vector2d currPoint = new Vector2d(j, i);
                if (worldMap.isOccupied(currPoint)) {
                    int columnInd = j - bottomLeft.getX() + 1;
                    int rowInd = gridHeight - (i - bottomLeft.getY()) - 1;
                    mapGrid.add(createLabel(worldMap.objectAt(currPoint).toString()),
                            columnInd,
                            rowInd);
                }
            }
        }
    }

    @FXML
    public void onSimulationStartClicked() {
        String[] args = movesField.getText().split(" ");
        List <MoveDirection> directions = OptionsParser.parse(args);

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 2));

        GrassField grassMap = new GrassField(10);
        this.setWorldMap(grassMap);

        List<Simulation> simulations = new ArrayList<>();
        Simulation simulation = new Simulation(directions, positions, grassMap);
        simulations.add(simulation);

        grassMap.addObserver(this);
        this.setWorldMap(grassMap);

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            messageLabel.setText(message);
        });
    }
}
