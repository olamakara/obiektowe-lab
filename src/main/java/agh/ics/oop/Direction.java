package agh.ics.oop;

public enum Direction {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    public static Direction valueOfLabel(String label) {
        switch (label) {
            case "f": {return Direction.FORWARD;}
            case "b": {return Direction.BACKWARD;}
            case "r": {return Direction.RIGHT;}
            case "l": {return Direction.LEFT;}
            default: {return null;}
        }
    }

    public String moveMessage() {
        switch (this) {
            case FORWARD -> {return "Zwierzak idzie do przodu...";}
            case BACKWARD -> {return "Zwierzak idzie do tyłu...";}
            case LEFT -> {return "Zwierzak idzie w lewo...";}
            case RIGHT -> {return "Zwierzak idzie w prawo...";}
            default -> {return "";}
        }
    }
}
