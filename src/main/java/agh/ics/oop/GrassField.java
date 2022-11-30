package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.shuffle;

public class GrassField extends AbstractWorldMap implements IPositionChangeObserver {

    private final int numberOfGrass;
    public final Map<Vector2d, IMapElement> mapElements;
    private final MapVisualiser toVisualize;
    private final MapBoundary mapListener = new MapBoundary();
    Vector2d lowerLeft;
    Vector2d upperRight;

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        this.mapElements = new HashMap<>();
        this.toVisualize = new MapVisualiser(this);
        this.upperRight = new Vector2d(0, 0);
        this.lowerLeft = new Vector2d((int)Math.sqrt(numberOfGrass * 10), (int)Math.sqrt(numberOfGrass * 10));
        putGrass();
    }

    private void putGrass() {
        ArrayList<Vector2d> possibleGrassPositions = new ArrayList<>();
        for (int i = 0; i <= lowerLeft.getX(); i++) {
            for (int j = 0; j <= lowerLeft.getX(); j++) {
                possibleGrassPositions.add(new Vector2d(i, j));
            }
        }
        shuffle(possibleGrassPositions);
        int grassToPut = Math.min(possibleGrassPositions.toArray().length, numberOfGrass);
        for (int i = 0; i <  grassToPut; i++) {
            Vector2d newPosition = possibleGrassPositions.get(i);
            mapElements.put(newPosition, new Grass(newPosition));
            lowerLeft = lowerLeft.lowerLeft(newPosition);
            upperRight = upperRight.upperRight(newPosition);
        }
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
            mapListener.newAnimal(animal, animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException("Cannot move to the given position: " + animal.getPosition());
    }

    public Vector2d upperRightBorder() {
        return mapListener.getUpperRight().upperRight(upperRight);
    }

    public Vector2d lowerLeftBorder() {
        return mapListener.getLowerLeft().lowerLeft(lowerLeft);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement object = mapElements.get(oldPosition);
        mapElements.remove(oldPosition, object);
        mapElements.put(newPosition, object);
        mapListener.animalMoved(oldPosition, object, newPosition);
    }
}
