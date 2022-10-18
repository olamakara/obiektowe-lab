package agh.ics.oop;

import java.util.LinkedList;

public class World {

    public void run(LinkedList<Direction> args) {
        for (Direction elem: args) {
            System.out.println(elem.label1);
        }
    }

    public LinkedList<Direction> convert(String[] args) {
        LinkedList<Direction> ll = new LinkedList<>();
        for (String arg : args) {
            Direction dir = Direction.valueOfLabel(arg);
            if (dir != null) {
                ll.add(dir);
            }
        }
        return ll;
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

        System.out.println("stop");
    }
}
