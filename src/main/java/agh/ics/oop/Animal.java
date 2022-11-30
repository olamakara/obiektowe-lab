package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class Animal implements IMapElement {

    private MapDirection orientation = MapDirection.NORTH;
    private final Vector2d initialPosition = new Vector2d(2, 2);
    private IWorldMap map;
    private Vector2d position;
    private List<IPositionChangeObserver> observers;

    Animal(IWorldMap worldMap, Vector2d initialPosition) {
        this.map = worldMap;
        this.position = initialPosition;
        this.observers = new LinkedList<>();
    }
    Animal() {

    }
    Animal(IWorldMap worldMap) {
        this();
        map = worldMap;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        switch (orientation) {
            case NORTH -> {return "N";}
            case SOUTH -> {return "S";}
            case WEST -> {return "W";}
            case EAST -> {return "E";}
            default -> {return "";}
        }
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d move = position.add(orientation.coordinates());
                if (map.canMoveTo(move)) {
                    positionChanged(move);
                }
            }
            case BACKWARD -> {
                Vector2d move = position.add(orientation.coordinates().opposite());
                if (map.canMoveTo(move)) {
                    positionChanged(move);
                }
            }
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d newPosition) {
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(position, newPosition);
        }
        position = newPosition;
    }
}
