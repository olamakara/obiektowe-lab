package agh.ics.oop;

import java.util.LinkedList;

public class OptionsParser {

    public LinkedList<MoveDirection> parse(String[] stringArray) {
        LinkedList<MoveDirection> moveDirectionsArray = new LinkedList<>();
        for (String move: stringArray) {
            switch (move) {
                case "f", "forward" -> moveDirectionsArray.add(MoveDirection.FORWARD);
                case "b", "backward" -> moveDirectionsArray.add(MoveDirection.BACKWARD);
                case "r", "right" -> moveDirectionsArray.add(MoveDirection.RIGHT);
                case "l", "left" -> moveDirectionsArray.add(MoveDirection.LEFT);
            }
        }
        return moveDirectionsArray;
    }

}
