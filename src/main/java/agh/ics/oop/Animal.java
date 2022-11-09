package agh.ics.oop;

public class Animal {

    private MapDirection orientation = MapDirection.NORTH;
    private final Vector2d position = new Vector2d(2, 2);
    private IWorldMap map;
    private Vector2d initialPosition;

    Animal() {

    }
    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.initialPosition = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
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
        return this.initialPosition.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d move = initialPosition.add(orientation.coordinates());
                if (map.canMoveTo(move)) {
                    initialPosition = move;
                }
            }
            case BACKWARD -> {
                Vector2d move = initialPosition.add(orientation.coordinates().opposite());
                if (map.canMoveTo(move)) {
                    initialPosition = move;
                }
            }
        }
    }

    public Vector2d getInitialPosition() {
        return new Vector2d(initialPosition.x, initialPosition.y);
    }
}
