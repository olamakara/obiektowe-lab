package agh.ics.oop;

public class Animal implements IMapElement {

    private MapDirection orientation = MapDirection.NORTH;
    private final Vector2d initialPosition = new Vector2d(2, 2);
    private IWorldMap map;
    private Vector2d position;

    Animal() {

    }
    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
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
                    position = move;
                }
            }
            case BACKWARD -> {
                Vector2d move = position.add(orientation.coordinates().opposite());
                if (map.canMoveTo(move)) {
                    position = move;
                }
            }
        }
    }

    public Vector2d getPosition() {
        return position;
    }
}
