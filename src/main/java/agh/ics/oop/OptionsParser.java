package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] args){
        int final_length = 0;
        for(String argument:args){
            switch (argument) {
                case "f", "forward", "b", "backward", "r", "right", "l", "left" -> final_length += 1;
            }
        }
        MoveDirection[] changed = new MoveDirection[final_length];
        int curr = 0;
        for(String argument:args){
            switch (argument) {
                case "f", "forward" -> {
                    changed[curr] = MoveDirection.FORWARD;
                    curr += 1;
                }
                case "b", "backward" -> {
                    changed[curr] = MoveDirection.BACKWARD;
                    curr += 1;
                }
                case "r", "right" -> {
                    changed[curr] = MoveDirection.RIGHT;
                    curr += 1;
                }
                case "l", "left" -> {
                    changed[curr] = MoveDirection.LEFT;
                    curr += 1;
                }
            }
        }
        return changed;
    }
}
