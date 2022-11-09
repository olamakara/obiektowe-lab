package agh.ics.oop;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String direction() {
        switch(this) {
            case NORTH -> {return "Północ";}
            case EAST -> {return "Wschód";}
            case SOUTH -> {return "Południe";}
            case WEST -> {return "Zachód";}
            default -> {return "";}
        }
    }

    public Vector2d coordinates() {
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

    public MapDirection next() {
        switch (this) {
            case NORTH -> {return EAST;}
            case EAST -> {return SOUTH;}
            case SOUTH -> {return WEST;}
            case WEST -> {return NORTH;}
            default -> {return null;}
        }
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH -> {return WEST;}
            case EAST -> {return NORTH;}
            case SOUTH -> {return EAST;}
            case WEST -> {return SOUTH;}
            default -> {return null;}
        }
    }

    private Vector2d toUnitVector() {
        return this.coordinates();
    }
}
