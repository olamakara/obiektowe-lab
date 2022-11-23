package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.shuffle;

public class GrassField extends AbstractWorldMap implements IPositionChangeObserver {

    private final int numberOfGrass;
    private final Map<Vector2d, IMapElement> mapElements;
    private final MapVisualiser toVisualize;
    Vector2d lowerLeft;
    Vector2d upperRight;

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        this.mapElements = new HashMap<>();
        this.toVisualize = new MapVisualiser(this);
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d((int)Math.sqrt(numberOfGrass * 10), (int)Math.sqrt(numberOfGrass * 10));
        putGrass();
    }

    private void putGrass() {
        ArrayList<Vector2d> possibleGrassPositions = new ArrayList<>();
        for (int i = 0; i <= upperRight.x; i++) {
            for (int j = 0; j <= upperRight.x; j++) {
                possibleGrassPositions.add(new Vector2d(i, j));
            }
        }
        shuffle(possibleGrassPositions);
        int grassToPut = Math.min(possibleGrassPositions.toArray().length, numberOfGrass);
        for (int i = 0; i <  grassToPut; i++) {
            Vector2d newPosition = possibleGrassPositions.get(i);
            mapElements.put(newPosition, new Grass(newPosition));
        }
//        System.out.println(mapElements);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public boolean isOccupied(Vector2d position) {
        return mapElements.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            mapElements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException("Cannot move to the given position: " + animal.getPosition());
    }

    public Vector2d upperRightBorder() {
        return upperRight;
    }

    public Vector2d lowerLeftBorder() {
        return lowerLeft;
    }

    public void updateBorders(Vector2d position) {
        lowerLeft = lowerLeft.lowerLeft(position);
        upperRight = upperRight.upperRight(position);
    }
}
