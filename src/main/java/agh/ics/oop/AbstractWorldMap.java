package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected abstract Vector2d upperRightBorder();
    protected abstract Vector2d lowerLeftBorder();

    public boolean canMoveTo(Vector2d position) {
        return lowerLeftBorder().precedes(position) && upperRightBorder().follows(position)
                && !(objectAt(position) instanceof Animal);
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

    public void updateBorders(Vector2d position) {

    }

    @Override
    public String toString() {
        MapVisualiser toVisualize = new MapVisualiser(this);
        return toVisualize.draw(lowerLeftBorder(), upperRightBorder());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement object = mapElements.get(oldPosition);
        mapElements.remove(oldPosition, object);  // drugie pole opcjonalne
        mapElements.put(newPosition, object);
//        System.out.println(mapElements);
    }
}
