package agh.ics.oop;

import java.util.LinkedList;

public class World {

    public void run(LinkedList<Direction> args) {
        for (Direction direction: args) {
            System.out.println(direction.label1);
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

        World animal = new World();
        LinkedList<Direction> directions = animal.convert(args);
        animal.run(directions);

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        Animal creature = new Animal();
        System.out.println(creature.position);

        OptionsParser parser = new OptionsParser();
        LinkedList<MoveDirection> listOfMoves = parser.parse(args);
        for (MoveDirection move: listOfMoves) {
            creature.move(move);
        }

        System.out.println(creature.position);
        System.out.println(creature.orientation);

        creature.move(MoveDirection.RIGHT);
        creature.move(MoveDirection.FORWARD);
        creature.move(MoveDirection.FORWARD);
        creature.move(MoveDirection.FORWARD);
        System.out.println(creature.position);
        System.out.println(creature.orientation);

        System.out.println("stop");
    }
}
