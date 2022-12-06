package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    private Image image;
    public VBox vBox = new VBox();
    private IMapElement mapElement;

    public GuiElementBox(IMapElement mapElement) {
        try {
            this.image = new Image(new FileInputStream(mapElement.getImagePath()));
            this.mapElement = mapElement;
        }
        catch (FileNotFoundException file) {
            System.out.println("File " + file + " doesn't exist");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label(mapElement.toString());
        this.vBox.getChildren().add(imageView);
        this.vBox.getChildren().add(label);
        this.vBox.setAlignment(Pos.CENTER);
    }
}
