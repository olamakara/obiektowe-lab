package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    void testIfCorrectOrientation() {
       IWorldMap map = new RectangularMap(5, 5);
       Vector2d startPosition = new Vector2d(2, 2);
       Animal pikachu = new Animal(map, startPosition);

       pikachu.move(MoveDirection.RIGHT);
       assertEquals(pikachu.getOrientation(), MapDirection.EAST);

        pikachu.move(MoveDirection.RIGHT);
        assertEquals(pikachu.getOrientation(), MapDirection.SOUTH);

        pikachu.move(MoveDirection.RIGHT);
        assertEquals(pikachu.getOrientation(), MapDirection.WEST);

        pikachu.move(MoveDirection.RIGHT);
        assertEquals(pikachu.getOrientation(), MapDirection.NORTH);

        pikachu.move(MoveDirection.LEFT);
        assertEquals(pikachu.getOrientation(), MapDirection.WEST);

        pikachu.move(MoveDirection.LEFT);
        assertEquals(pikachu.getOrientation(), MapDirection.SOUTH);

        pikachu.move(MoveDirection.LEFT);
        assertEquals(pikachu.getOrientation(), MapDirection.EAST);

        pikachu.move(MoveDirection.LEFT);
        assertEquals(pikachu.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void testIfCorrectPosition() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d startPosition = new Vector2d(2, 2);
        Animal pikachu = new Animal(map, startPosition);

        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(2, 3));

        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(2, 4));

        pikachu.move(MoveDirection.RIGHT);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(2, 4));

        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(3, 4));

        pikachu.move(MoveDirection.LEFT);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(3, 4));

        pikachu.move(MoveDirection.BACKWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(3, 3));
    }
    @Test
    void testIfParseCorrect() {
        String[] movesString = {"f", "fl", "b", "forward", "backward", "r", "blackword", "left", "rb",
                "ll", "right", "lefty", "left"};
        OptionsParser parser = new OptionsParser();
        MoveDirection[] movesDirections = parser.parse(movesString);
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT};

        assertArrayEquals(expectedDirections, movesDirections);
    }

    @Test
    void testIfAnimalsStayInTheMap() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d startPosition = new Vector2d(2, 2);
        Animal pikachu = new Animal(map, startPosition);

        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(2, 4));

        pikachu.move(MoveDirection.RIGHT);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(4, 4));

        pikachu.move(MoveDirection.RIGHT);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(4, 0));

        pikachu.move(MoveDirection.RIGHT);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        pikachu.move(MoveDirection.FORWARD);
        assertEquals(pikachu.getInitialPosition(), new Vector2d(0, 0));
    }

    @Test
    void testIfEngineRunWorks() {
        String[] movesString = {"f", "f", "f", "b", "r", "l", "b", "f", "f"};
        OptionsParser parser = new OptionsParser();
        MoveDirection[] animalsDirections = parser.parse(movesString);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] animalsInitialPositions = {new Vector2d(2, 2), new Vector2d(0, 0), new Vector2d(2, 3)};
        SimulationEngine engine = new SimulationEngine(animalsDirections, map, animalsInitialPositions);
        engine.run();

        assertEquals(engine.getAnimal(0).getOrientation(), MapDirection.NORTH);
        assertEquals(engine.getAnimal(0).getInitialPosition(), new Vector2d(2 ,0));

        assertEquals(engine.getAnimal(1).getOrientation(), MapDirection.EAST);
        assertEquals(engine.getAnimal(1).getInitialPosition(), new Vector2d(1 ,1));

        assertEquals(engine.getAnimal(2).getOrientation(), MapDirection.WEST);
        assertEquals(engine.getAnimal(2).getInitialPosition(), new Vector2d(1, 4));
    }

}
