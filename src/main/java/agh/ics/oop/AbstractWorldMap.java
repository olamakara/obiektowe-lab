package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {

    private final ArrayList<Animal> mapElements = new ArrayList<>();
    protected abstract Vector2d upperRightBorder();
    protected abstract Vector2d lowerLeftBorder();

    public boolean canMoveTo(Vector2d position) {
        return lowerLeftBorder().precedes(position) && upperRightBorder().follows(position) && !isOccupiedWithAnimal(position);
    }

    public boolean isOccupiedWithAnimal(Vector2d position) {
        for (IMapElement element: mapElements) {
            if (element.getPosition().equals(position) && element instanceof Animal) {
                return true;
            }
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal animal: mapElements) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal: mapElements) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            mapElements.add(animal);
            return true;
        }
        return false;
    }

    public void updateBorders(Vector2d position) {

    }

    @Override
    public String toString() {
        MapVisualiser toVisualize = new MapVisualiser(this);
        return toVisualize.draw(lowerLeftBorder(), upperRightBorder());
    }
}
