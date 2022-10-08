package agh.ics.oop;


public class World {

    public void run(Direction[] args) {
        for (Direction elem: args) {
            switch (elem) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu...");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu...");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo...");
                case LEFT -> System.out.println("Zwierzak skręca w lewo...");
                default -> {}
            }
        }
    }

    public Direction[] convert(String[] args) {
        int n = args.length;
        Direction[] directionArray = new Direction[n];
        for (int i = 0; i < n; i++) {
            switch (args[i]) {
                case "f" -> directionArray[i] = Direction.FORWARD;
                case "b" -> directionArray[i] = Direction.BACKWARD;
                case "r" -> directionArray[i] = Direction.RIGHT;
                case "l" -> directionArray[i] = Direction.LEFT;
                default -> directionArray[i] = Direction.IGNORE;
            }
        }
        return directionArray;
    }

    public static void main(String[] args) {
        System.out.println("start");

        World animal = new World();
        Direction[] directions = animal.convert(args);
        animal.run(directions);

        System.out.println("stop");
    }
}
