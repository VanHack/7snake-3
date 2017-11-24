package br.com.rtp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class SnakeTest {

    @Test
    public void matches() throws Exception {

        int[][] grid = {
                {1, 2, 5, 4, 3, 6},
                {6, 5, 4, 3, 4, 1},
                {6, 1, 5, 2, 2, 3},
                {6, 1, 5, 2, 4, 3},
                {6, 5, 4, 3, 2, 1},
                {1, 2, 3, 4, 5, 6}
        };

        Snake snake1 = new Snake(grid, new CoordSet(new HashSet<Coord>(Arrays.asList(
                new Coord(1, 1, 6),
                new Coord(2, 1, 6),
                new Coord(3, 1, 6),
                new Coord(4, 1, 6),
                new Coord(4, 2, 6),
                new Coord(4, 3, 6),
                new Coord(4, 4, 6)
        ))));

        Assert.assertEquals(21L, snake1.getValue().longValue());

        Snake snake2 = new Snake(grid, new CoordSet(new HashSet<Coord>(Arrays.asList(
                new Coord(0, 2, 6),
                new Coord(0, 3, 6),
                new Coord(0, 4, 6),
                new Coord(0, 5, 6),
                new Coord(1, 5, 6),
                new Coord(2, 5, 6),
                new Coord(3, 5, 6)
        ))));

        Assert.assertEquals(25L, snake2.getValue().longValue());

        Assert.assertFalse(snake1.matches(snake2));

        Snake snake3 = new Snake(grid, new CoordSet(new HashSet<Coord>(Arrays.asList(
                new Coord(3, 1, 6),
                new Coord(3, 2, 6),
                new Coord(3, 3, 6),
                new Coord(3, 4, 6),
                new Coord(2, 4, 6),
                new Coord(1, 4, 6),
                new Coord(0, 4, 6)
        ))));

        Assert.assertEquals(21L, snake3.getValue().longValue());

        Assert.assertFalse(snake1.matches(snake3));

        Snake snake4 = new Snake(grid, new CoordSet(new HashSet<Coord>(Arrays.asList(
                new Coord(5, 5, 6),
                new Coord(4, 5, 6),
                new Coord(3, 5, 6),
                new Coord(2, 5, 6),
                new Coord(1, 5, 6),
                new Coord(0, 4, 6),
                new Coord(0, 3, 6)
        ))));

        Assert.assertEquals(21L, snake4.getValue().longValue());

        Assert.assertTrue(snake1.matches(snake4));

    }

}