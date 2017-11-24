package br.com.rtp;

public enum Direction {

    UP,
    RIGHT,
    DOWN,
    LEFT;

    static Direction opposite(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                throw new RuntimeException("Invalid direction");
        }
    }

}
