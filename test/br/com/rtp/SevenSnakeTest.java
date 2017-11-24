package br.com.rtp;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SevenSnakeTest {
    @Test
    public void testSuccessfulRun() throws Exception {

        int[][] grid = {
                { 17,  2,  5,  4},
                { 6,   5,  4,  3},
                { 6,   1,  3,  2},
                { 6,  66,  5,  2}
        };

        SevenSnake sevenSnake = new SevenSnake(grid);
        sevenSnake.run();

        Assert.assertTrue(sevenSnake.getSolution().stream().anyMatch(s -> s.toString().equals("(1 1), (1 2), (1 3), (1 4), (2 4), (3 4), (4 4)")));
        Assert.assertTrue(sevenSnake.getSolution().stream().anyMatch(s -> s.toString().equals("(2 1), (2 2), (2 3), (3 1), (3 3), (4 1), (4 3)")));
    }

    @Test
    public void testFailure() throws Exception {

        int[][] grid = {
                { 18,  2,  5,  4},
                { 6,   5,  4,  3},
                { 6,   1,  3,  2},
                { 6,  66,  5,  2}
        };

        SevenSnake sevenSnake = new SevenSnake(grid);
        sevenSnake.run();

        Assert.assertNull(sevenSnake.getSolution());
    }
}