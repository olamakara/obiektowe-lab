package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final ArrayList<Animal> animalsList;
    private final MapVisualiser toVisualize;

    public RectangularMap(Integer width, Integer height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.animalsList = new ArrayList<>();
        this.toVisualize = new MapVisualiser(this);
    }

    public Vector2d upperRightBorder() {
        return upperRight;
    }

    public Vector2d lowerLeftBorder() {
        return lowerLeft;
    }
}
