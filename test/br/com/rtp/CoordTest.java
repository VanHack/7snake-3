package br.com.rtp;

import org.junit.Test;

import org.junit.Assert;

public class CoordTest {

    @Test
    public void testEquals() {
        Coord coord1 = new Coord(10, 10, 20);
        Coord coord2 = new Coord(10, 10, 20);
        Coord coord3 = new Coord(9, 10, 20);

        Assert.assertTrue(coord1.equals(coord2));
        Assert.assertTrue(coord2.equals(coord1));

        Assert.assertFalse(coord1.equals(coord3));
        Assert.assertFalse(coord3.equals(coord2));
    }

    @Test
    public void testGet() {
        Coord coord1 = new Coord(10, 10, 20);

        Assert.assertEquals(coord1.get(Direction.UP), new Coord(9, 10, 20));
        Assert.assertEquals(coord1.get(Direction.DOWN), new Coord(11, 10, 20));
        Assert.assertEquals(coord1.get(Direction.LEFT), new Coord(10, 9, 20));
        Assert.assertEquals(coord1.get(Direction.RIGHT), new Coord(10, 11, 20));
    }

    @Test
    public void testDirections() {
        Coord coord1 = new Coord(10, 10, 20);

        Assert.assertEquals(coord1.upRight(), new Coord(9, 11, 20));
        Assert.assertEquals(coord1.downRight(), new Coord(11, 11, 20));
        Assert.assertEquals(coord1.downLeft(), new Coord(11, 9, 20));
        Assert.assertEquals(coord1.upLeft(), new Coord(9, 9, 20));
    }

    @Test
    public void testGridLimits() {
        Coord coord1 = new Coord(10, 10, 20);

        Assert.assertNull(new Coord(0, 0, 20).up());
        Assert.assertNull(new Coord(0, 0, 20).left());

        Assert.assertNull(new Coord(19, 0, 20).down());
        Assert.assertNull(new Coord(19, 0, 20).left());

        Assert.assertNull(new Coord(19, 19, 20).down());
        Assert.assertNull(new Coord(19, 19, 20).right());

        Assert.assertNull(new Coord(0, 19, 20).up());
        Assert.assertNull(new Coord(0, 19, 20).right());
    }

    @Test
    public void testComparator() {
        Assert.assertTrue(new Coord(0, 0, 20).compareTo(new Coord(0, 0, 20)) == 0);

        Assert.assertTrue(new Coord(0, 0, 20).compareTo(new Coord(1, 0, 20)) < 0);
        Assert.assertTrue(new Coord(1, 0, 20).compareTo(new Coord(0, 0, 20)) > 0);

        Assert.assertTrue(new Coord(1, 0, 20).compareTo(new Coord(1, 1, 20)) < 0);
        Assert.assertTrue(new Coord(1, 1, 20).compareTo(new Coord(1, 0, 20)) > 0);
    }
}
