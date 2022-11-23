package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapAndGrassFieldTest {

    @Test
    void testIfAnimalCanMoveToPosition() {
        IWorldMap mapR = new RectangularMap(5, 5);
        IWorldMap mapG = new GrassField(5);
        Vector2d lionVector = new Vector2d(2, 2);
        Vector2d giraffeVector = new Vector2d(2, 1);
        Vector2d crocodileVector = new Vector2d(3, 2);

        assertTrue(mapR.canMoveTo(lionVector));
        assertTrue(mapR.canMoveTo(giraffeVector));
        assertTrue(mapR.canMoveTo(crocodileVector));
        assertTrue(mapG.canMoveTo(lionVector));
        assertTrue(mapG.canMoveTo(giraffeVector));
        assertTrue(mapG.canMoveTo(crocodileVector));

        Animal lion = new Animal(mapR, lionVector);
        mapR.place(lion);
        mapG.place(lion);
        Animal giraffe = new Animal(mapR, giraffeVector);
        mapR.place(giraffe);
        mapG.place(giraffe);
        Animal crocodile = new Animal(mapR, crocodileVector);
        mapR.place(crocodile);
        mapG.place(crocodile);

        assertFalse(mapR.canMoveTo(lionVector));
        assertFalse(mapR.canMoveTo(giraffeVector));
        assertFalse(mapR.canMoveTo(crocodileVector));
        assertFalse(mapR.canMoveTo(new Vector2d(10, 10)));
        assertFalse(mapG.canMoveTo(lionVector));
        assertFalse(mapG.canMoveTo(giraffeVector));
        assertFalse(mapG.canMoveTo(crocodileVector));
        assertTrue(mapG.canMoveTo(new Vector2d(10, 10)));
    }

    @Test
    void testisOccupiedPosition() {
        IWorldMap mapR = new RectangularMap(5, 5);
        IWorldMap mapG = new GrassField(5);
        Vector2d lionVector = new Vector2d(2, 2);
        Vector2d giraffeVector = new Vector2d(2, 1);
        Vector2d crocodileVector = new Vector2d(3, 2);

        assertFalse(mapR.isOccupied(lionVector));
        assertFalse(mapR.isOccupied(giraffeVector));
        assertFalse(mapR.isOccupied(crocodileVector));
        assertFalse(mapR.isOccupied(new Vector2d(10, 10)));

        Animal lion = new Animal(mapR, lionVector);
        mapR.place(lion);
        mapG.place(lion);
        Animal giraffe = new Animal(mapR, giraffeVector);
        mapR.place(giraffe);
        mapG.place(giraffe);
        Animal crocodile = new Animal(mapR, crocodileVector);
        mapR.place(crocodile);
        mapG.place(crocodile);

        assertTrue(mapR.isOccupied(lionVector));
        assertTrue(mapR.isOccupied(giraffeVector));
        assertTrue(mapR.isOccupied(crocodileVector));
        assertTrue(mapG.isOccupied(lionVector));
        assertTrue(mapG.isOccupied(giraffeVector));
        assertTrue(mapG.isOccupied(crocodileVector));
    }

    @Test
    void testWhatObjectAt() {
        IWorldMap mapR = new RectangularMap(5, 5);
        IWorldMap mapG = new GrassField(5);
        Vector2d lionVector = new Vector2d(2, 2);
        Vector2d giraffeVector = new Vector2d(2, 1);
        Vector2d crocodileVector = new Vector2d(3, 2);

        assertNull(mapR.objectAt(lionVector));
        assertNull(mapR.objectAt(giraffeVector));
        assertNull(mapR.objectAt(crocodileVector));
        assertNull(mapG.objectAt(lionVector));
        assertNull(mapG.objectAt(giraffeVector));
        assertNull(mapG.objectAt(crocodileVector));

        Animal lion = new Animal(mapR, lionVector);
        mapR.place(lion);
        mapG.place(lion);
        Animal giraffe = new Animal(mapR, giraffeVector);
        mapR.place(giraffe);
        mapG.place(giraffe);
        Animal crocodile = new Animal(mapR, crocodileVector);
        mapR.place(crocodile);
        mapG.place(crocodile);

        assertEquals(mapR.objectAt(lionVector), lion);
        assertEquals(mapR.objectAt(giraffeVector), giraffe);
        assertEquals(mapR.objectAt(crocodileVector), crocodile);
        assertEquals(mapG.objectAt(lionVector), lion);
        assertEquals(mapG.objectAt(giraffeVector), giraffe);
        assertEquals(mapG.objectAt(crocodileVector), crocodile);
    }
}
