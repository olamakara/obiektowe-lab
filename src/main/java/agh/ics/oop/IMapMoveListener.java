package agh.ics.oop;

public interface IMapMoveListener {

    public void newAnimal(Object animal, Vector2d position);
    public void animalMoved(Vector2d oldPosition, Object animal, Vector2d newPosition);

}
