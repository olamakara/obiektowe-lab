package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    void testIfCorrectOrientation() {
        Animal animal = new Animal();
        assertEquals(animal.orientation, MapDirection.NORTH);

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.LEFT);

        assertEquals(animal.orientation, MapDirection.EAST);
    }

    @Test
    void testIfCorrectPosition() {
        Animal animal = new Animal();
        assertEquals(animal.position, new Vector2d(2, 2));

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.position, new Vector2d(3, 4));
    }

    @Test
    void testIfAnimalStayInMap() {
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.position, new Vector2d(2, 4));
    }

    @Test
    void testIfArgumentsAreInterpretedCorrectly() {
        OptionsParser parser = new OptionsParser();
        String[] stringArray = {"f", "right", "r", "b", "backward", "left"};

        LinkedList<MoveDirection> moveDirectionsArray = parser.parse(stringArray);
        LinkedList<MoveDirection> expectedArray = new LinkedList<>();
        expectedArray.add(MoveDirection.FORWARD);
        expectedArray.add(MoveDirection.RIGHT);
        expectedArray.add(MoveDirection.RIGHT);
        expectedArray.add(MoveDirection.BACKWARD);
        expectedArray.add(MoveDirection.BACKWARD);
        expectedArray.add(MoveDirection.LEFT);

        assertEquals(moveDirectionsArray, expectedArray);
    }

}
