package br.com.rtp;

public class Snake {

    private CoordSet coords;
    private Long value;

    public Snake(int[][] grid, CoordSet coords) {
        this.coords = coords;

        value = 0L;
        for (Coord coord : coords) {
            value += grid[coord.getI()][coord.getJ()];
        }
    }

    public CoordSet getCoords() {
        return coords;
    }

    public Long getValue() {
        return value;
    }

    public boolean intersect(Snake other) {
        return coords.intersect(other.getCoords());
    }

    public boolean matches(Snake other) {
        return value.equals(other.getValue()) && !intersect(other);
    }

    @Override
    public String toString() {
        return coords.toString();
    }
}
