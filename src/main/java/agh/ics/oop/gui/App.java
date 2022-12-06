package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.HPos;

public class App extends Application implements IAppObserver {

    private AbstractWorldMap map;
    private final GridPane gridPane = new GridPane();
    private SimulationEngine engine;
    private final static int moveDelay = 300;

    public void init() {
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        this.engine = new SimulationEngine(map, positions);
        this.engine.setDelay(moveDelay);
        this.engine.addObserver(this);
    }

    public void start(Stage primaryStage) {
        TextField moves = new TextField();
        Button start = new Button("Start");
        VBox top = new VBox(moves, start);
        VBox screen = new VBox(top, gridPane);
        HBox body = new HBox(screen);

        start.setOnAction(click -> {
            String[] args = moves.getText().split(" ");
            MoveDirection[] directions = new OptionsParser().parse(args);
            engine.setDirections(directions);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        createGridPane();
        Scene scene = new Scene(body, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createGridPane() {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();

        Vector2d lowerLeft = map.getLowerLeftBorder();
        Vector2d upperRight = map.getUpperRightBorder();

        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(50));
        gridPane.getRowConstraints().add(new RowConstraints(50));

        Label label = new Label("y\\x");
        gridPane.add(label, 0, 0, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);

        // vertical border
        for (int i = upperRight.getY(); i >= lowerLeft.getY(); i--) {
            label = new Label(Integer.toString(i));
            gridPane.getRowConstraints().add(new RowConstraints(50));
            gridPane.add(label, 0, -i + 1 + upperRight.getY(), 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        // horizontal border
        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            label = new Label(Integer.toString(i));
            gridPane.getColumnConstraints().add(new ColumnConstraints(50));
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
                        GuiElementBox elementBox = new GuiElementBox((IMapElement) object);
                        gridPane.add(elementBox.vBox, i + 1 - lowerLeft.getX(), -j + 1 + upperRight.getY(), 1, 1);
                    }
                }
            }
        }
    }

    @Override
    public void positionChanged() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            createGridPane();
        });
    }
}
