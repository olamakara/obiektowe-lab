package agh.ics.oop;
// zamiast robic enterki zrobic dodatkowe metody
// stale -> static (final) public/private (zaleznie od tego na co ma wplyw), wielkimi literami (+snake_case)
// wzorce projektowe -> refactoring.guru -> design patterns -> template method
// uml? -> unified modified language, diagram class, data flow diagram, sequence diagram

import java.util.Arrays;
import java.util.LinkedList;

public class World {

    public void run(LinkedList<Direction> args) {
        for (Direction direction: args) {
            System.out.println(direction.moveMessage());
        }
    }

    public LinkedList<Direction> convert(String[] args) {
        LinkedList<Direction> directionLinkedList = new LinkedList<>();
        for (String arg : args) {
            Direction direction = Direction.valueOfLabel(arg);
            if (direction != null) {
                directionLinkedList.add(direction);
            }
        }
        return directionLinkedList;
    }

    public static void main(String[] args) {
        System.out.println("start");

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map.toString());

        System.out.println("stop");
    }
}
