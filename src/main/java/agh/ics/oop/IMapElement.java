package agh.ics.oop;

public interface IMapElement {

    Vector2d getPosition();

    MapDirection getOrientation();

    String toString();

    String getImagePath();
}
