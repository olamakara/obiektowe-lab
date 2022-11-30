package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

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
        try {
            System.out.println("start");

            Application.launch(App.class, args);

            System.out.println("stop");
        }

        catch (IllegalArgumentException exception) {
            System.out.println("Exception caught: " + exception);
        }

    }
}
