package br.com.rtp;

public class Coord implements Comparable<Coord> {

    private int i;
    private int j;
    private int gridSize;

    public Coord(int i, int j, int gridSize) {
        this.i = i;
        this.j = j;
        this.gridSize = gridSize;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Coord get(Direction dir) {
        switch (dir) {
            case UP:
                return up();
            case RIGHT:
                return right();
            case DOWN:
                return down();
            case LEFT:
                return left();
            default:
                throw new RuntimeException("Invalid direction");
        }
    }

    public Coord go(int deltaI, int deltaJ) {
        int newI = i + deltaI;
        int newJ = j + deltaJ;
        if (newI < 0 || newI >= gridSize || newJ < 0 || newJ >= gridSize) {
            return null;
        }
        return new Coord(newI, newJ, gridSize);
    }

    public Coord up() {
        return go(-1, 0);
    }

    public Coord down() {
        return go(1, 0);
    }

    public Coord left() {
        return go(0, -1);
    }

    public Coord right() {
        return go(0, 1);
    }

    public Coord upRight() {
        return go(-1, 1);
    }

    public Coord downRight() {
        return go(1, 1);
    }

    public Coord downLeft() {
        return go(1, -1);
    }

    public Coord upLeft() {
        return go(-1, -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (i != coord.i) return false;
        return j == coord.j;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

    @Override
    public int compareTo(Coord o) {
        if (i < o.getI()) {
            return -1;
        } else if (i > o.getI()) {
            return 1;
        } else {
            if (j < o.getJ()) {
                return -1;
            } else if (j > o.getJ()) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("(%d %d)", i+1, j+1); // one-based
    }
}
