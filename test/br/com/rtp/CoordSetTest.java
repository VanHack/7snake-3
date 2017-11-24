package br.com.rtp;

import org.junit.Assert;
import org.junit.Test;

public class CoordSetTest {

    @Test
    public void testIntersect() {
        CoordSet set1 = new CoordSet(new Coord(10, 10, 20));
        CoordSet set2 = new CoordSet(new Coord(10, 10, 20));
        CoordSet set3 = new CoordSet(new Coord(11, 10, 20));

        Assert.assertTrue(set1.intersect(set2));
        Assert.assertTrue(set2.intersect(set1));

        Assert.assertFalse(set1.intersect(set3));
        Assert.assertFalse(set3.intersect(set2));
    }

    @Test
    public void testAdd() {
        Coord coord1 = new Coord(10, 10, 20);
        Coord coord2 = new Coord(11, 11, 20);

        CoordSet set1 = new CoordSet(coord1);
        CoordSet set2 = set1.add(coord2);

        Assert.assertEquals(set1.getSet().size(), 1);
        Assert.assertTrue(set1.getSet().contains(coord1));

        Assert.assertEquals(set2.getSet().size(), 2);
        Assert.assertTrue(set2.getSet().contains(coord1));
        Assert.assertTrue(set2.getSet().contains(coord2));
    }

}
