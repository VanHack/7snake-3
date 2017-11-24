package br.com.rtp;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void testOpposite() {
        Assert.assertEquals(Direction.opposite(Direction.LEFT), Direction.RIGHT);
        Assert.assertEquals(Direction.opposite(Direction.RIGHT), Direction.LEFT);
        Assert.assertEquals(Direction.opposite(Direction.UP), Direction.DOWN);
        Assert.assertEquals(Direction.opposite(Direction.DOWN), Direction.UP);
    }

}