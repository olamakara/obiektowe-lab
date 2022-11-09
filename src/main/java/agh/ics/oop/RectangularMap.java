package agh.ics.oop;

import java.util.ArrayList;

class RectangularMap implements IWorldMap{

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

    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.precedes(position) && upperRight.follows(position) && !isOccupied(position);
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animalsList) {
            if (animal.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal: animalsList) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getInitialPosition())) {
            animalsList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toVisualize.draw(lowerLeft, upperRight);
    }
}
