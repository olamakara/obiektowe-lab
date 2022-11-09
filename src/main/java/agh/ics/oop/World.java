package agh.ics.oop;

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
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println("stop");
    }
}
