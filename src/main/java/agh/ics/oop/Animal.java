package agh.ics.oop;

public class Animal {

    public MapDirection orientation = MapDirection.NORTH;
    public Vector2d position = new Vector2d(2, 2);

    @Override
    public String toString() {
        return position + " " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d startPosition = this.position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> this.position = this.position.add(this.orientation.Coordinates());
            case BACKWARD -> this.position = this.position.add(this.orientation.Coordinates().opposite());
        }
        if (this.position.x > 4 || this.position.y > 4 || this.position.x < 0 || this.position.y < 0) {
            this.position = startPosition;
        }
    }
}
