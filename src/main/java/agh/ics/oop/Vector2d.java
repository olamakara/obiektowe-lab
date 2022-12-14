package agh.ics.oop;


import java.util.Objects;

public class Vector2d {

    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        int resX = Math.max(x, other.x);
        int resY = Math.max(y, other.y);
        return new Vector2d(resX, resY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int resX = Math.min(x, other.x);
        int resY = Math.min(y, other.y);
        return new Vector2d(resX, resY);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2d that))
            return false;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

}
