package agh.ics.oop;

public enum MapDirection {
    NORTH("Północ", 0, 1),
    EAST("Wschód", 1, 0),
    SOUTH("Południe", 0, -1),
    WEST("Zachód", -1, 0);

    public final String direction;
    public final int coordinate_x;
    public final int coordinate_y;

    MapDirection(String direction, int coordinate_x, int coordinate_y) {
        this.direction = direction;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    public String toString() {
        return this.direction;
    }

    public MapDirection next() {
        MapDirection[] arr = values();
        int ind = this.ordinal();
        return arr[(ind + 1) % 4];
    }

    public MapDirection previous() {
        MapDirection[] arr = values();
        int ind = this.ordinal();
        return arr[(ind + 3) % 4];
    }

    private Vector2d toUnitVector() {
        return new Vector2d(this.coordinate_x, this.coordinate_y);
    }
}
