package agh.ics.oop;

public enum Direction {
    FORWARD("Zwierzak idzie do przodu...", "f"),
    BACKWARD("Zwierzak idzie do tyłu...", "b"),
    RIGHT("Zwierzak idzie w prawo...", "r"),
    LEFT("Zwierzak idzie w lewo...", "l");

    public final String label1;
    public final String label2;
    Direction(String label1, String label2) {
        this.label1 = label1;
        this.label2 = label2;
    }

    public static Direction valueOfLabel(String label) {
        for (Direction e : values()) {
            if ((e.label2).equals(label)) {
                return e;
            }
        }
        return null;
    }
}
