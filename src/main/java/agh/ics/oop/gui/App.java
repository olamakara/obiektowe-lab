package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.HPos;

public class App extends Application {

    AbstractWorldMap map;

    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }

    public void start(Stage primaryStage) {
        GridPane gridPane = createGridPane();
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        Vector2d lowerLeft = map.getLowerLeftBorder();
        Vector2d upperRight = map.getUpperRightBorder();

        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
        gridPane.getRowConstraints().add(new RowConstraints(30));

        Label label = new Label("y\\x");
        gridPane.add(label, 0, 0, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);

        // vertical border
        for (int i = upperRight.getY(); i >= lowerLeft.getY(); i--) {
            label = new Label(Integer.toString(i));
            gridPane.getRowConstraints().add(new RowConstraints(30));
            gridPane.add(label, 0, -i + 1 + upperRight.getY(), 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        // horizontal border
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            label = new Label(Integer.toString(i));
            gridPane.getColumnConstraints().add(new ColumnConstraints(30));
            gridPane.add(label, i + 1 - lowerLeft.getX(), 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        // objects inside
        Vector2d currentPosition;
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            for (int j = upperRight.getY(); j >= lowerLeft.getY(); j--) {
                currentPosition = new Vector2d(i, j);
                if (map.isOccupied(currentPosition)) {
                    Object object = map.objectAt(currentPosition);
                    if (object != null) {
                        label = new Label(object.toString());
                        gridPane.add(label, i + 1 - lowerLeft.getX(), -j + 1 + upperRight.getY(), 1, 1);
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
            }
        }
        return gridPane;
    }
}
