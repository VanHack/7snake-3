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

public class InputReaderTest {

    @Test
    public void testRead() throws Exception {
        File tmpFile = File.createTempFile("7snake", ".csv");
        tmpFile.deleteOnExit();
        try (OutputStream os = IOUtils.buffer(new FileOutputStream(tmpFile))) {
            IOUtils.writeLines(Arrays.asList(
                    "1,2,3",
                    "4,5,6",
                    "7,8,9"
            ), "\n", os, Charset.defaultCharset());
        }

        int[][] result = InputReader.read(tmpFile.getAbsolutePath());

        Assert.assertTrue(result.length == 3);
        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(result[i].length == 3);
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals((i*3) + j + 1, result[i][j]);
            }
        }


    }

}