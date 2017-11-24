package br.com.rtp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionSetTest {
    @Test
    public void without() throws Exception {

        DirectionSet set = DirectionSet.ALL;

        DirectionSet set1 = set.without(Direction.UP);
        DirectionSet set2 = set.without(Direction.DOWN);
        DirectionSet set3 = set.without(Direction.LEFT);
        DirectionSet set4 = set.without(Direction.RIGHT);

        Assert.assertEquals(3, set1.getSet().size());
        Assert.assertFalse(set1.getSet().contains(Direction.UP));
        Assert.assertTrue(set1.getSet().contains(Direction.DOWN));
        Assert.assertTrue(set1.getSet().contains(Direction.LEFT));
        Assert.assertTrue(set1.getSet().contains(Direction.RIGHT));

        Assert.assertEquals(3, set2.getSet().size());
        Assert.assertTrue(set2.getSet().contains(Direction.UP));
        Assert.assertFalse(set2.getSet().contains(Direction.DOWN));
        Assert.assertTrue(set2.getSet().contains(Direction.LEFT));
        Assert.assertTrue(set2.getSet().contains(Direction.RIGHT));

        Assert.assertEquals(3, set3.getSet().size());
        Assert.assertTrue(set3.getSet().contains(Direction.UP));
        Assert.assertTrue(set3.getSet().contains(Direction.DOWN));
        Assert.assertFalse(set3.getSet().contains(Direction.LEFT));
        Assert.assertTrue(set3.getSet().contains(Direction.RIGHT));

        Assert.assertEquals(3, set4.getSet().size());
        Assert.assertTrue(set4.getSet().contains(Direction.UP));
        Assert.assertTrue(set4.getSet().contains(Direction.DOWN));
        Assert.assertTrue(set4.getSet().contains(Direction.LEFT));
        Assert.assertFalse(set4.getSet().contains(Direction.RIGHT));

    }

}