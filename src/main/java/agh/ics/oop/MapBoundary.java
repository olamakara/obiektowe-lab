package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IMapMoveListener {

    private final Comparator<Vector2d> positionXComparator = (vectorOne, vectorTwo) -> {
        if (vectorOne.getX() < vectorTwo.getX()) {
            return -1;
        } else if (vectorOne.getX() > vectorTwo.getX()) {
            return 1;
        }
        return 0;
    };

    private final Comparator<Vector2d> positionYComparator = (vectorOne, vectorTwo) -> {
        if (vectorOne.getY() < vectorTwo.getY()) {
            return -1;
        } else if (vectorOne.getY() > vectorTwo.getY()) {
            return 1;
        }
        return 0;
    };

    private final SortedMap<Vector2d, Object> sortedAnimalsXAsc = new TreeMap<>(positionXComparator);

    private final SortedMap<Vector2d, Object> sortedAnimalsYAsc = new TreeMap<>(positionYComparator);

    public Vector2d getLowerLeft() {
        return new Vector2d(sortedAnimalsXAsc.firstKey().getX(), sortedAnimalsYAsc.firstKey().getY());
    }

    public Vector2d getUpperRight() {
        return new Vector2d(sortedAnimalsXAsc.lastKey().getX(), sortedAnimalsYAsc.lastKey().getY());
    }

    @Override
    public void newAnimal(Object animal, Vector2d position) {
        sortedAnimalsXAsc.put(position, animal);
        sortedAnimalsYAsc.put(position, animal);
    }

    @Override
    public void animalMoved(Vector2d oldPosition, Object animal, Vector2d newPosition) {
        sortedAnimalsXAsc.remove(oldPosition);
        sortedAnimalsYAsc.remove(oldPosition);
        newAnimal(animal, newPosition);
    }

}
