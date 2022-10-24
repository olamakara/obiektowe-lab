package agh.ics.oop;
// nazwy dluzsze lepiej
// posprzatac zeby wszytsko mialo ten sam styl
// nazwa ma mowic co jest w srodku

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String Direction() {
        switch(this) {
            case NORTH -> {return "Północ";}
            case EAST -> {return "Wschód";}
            case SOUTH -> {return "Południe";}
            case WEST -> {return "Zachód";}
            default -> {return "";}
        }
    }

    public Vector2d Coordinates() {
        int x = 0;
        int y = 0;
        switch(this) {
            case NORTH -> y += 1;
            case SOUTH -> y -= 1;
            case EAST -> x += 1;
            case WEST -> x -= 1;
            default -> {}
        }
        return new Vector2d(x, y);
    }

//    public Vector2d CoordinateY() {
//        int y = 0;
//        switch(this) {
//            case NORTH -> y += 1;
//            case SOUTH -> y -= 1;
//            default -> {}
//        }
//        return new Vector2d(0, y);
//    }

//    public String toString() {
//        return this.Direction();
//    }

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
        return this.Coordinates();
    }
}
