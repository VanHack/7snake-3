package br.com.rtp;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class InputReader {

    public static int[][] read(String filename) throws IOException {
        return read(filename, ",");
    }

    public static int[][] read(String filename, String separator) throws IOException {
        int[][] grid;

        InputStream is = new FileInputStream(filename);
        try {
            List<String> lines = IOUtils.readLines(IOUtils.buffer(is), Charset.defaultCharset());
            if (lines.size() == 0) {
                throw new RuntimeException(String.format("File %s is empty", filename));
            }

            int gridSize = lines.get(0).split(separator).length;

            grid = new int[gridSize][gridSize];

            int i = 0, j;
            for (String line : lines) {
                line = line.trim();
                if (line.length() == 0) {
                    // ignore blank lines
                     continue;
                }

                if (i > gridSize - 1) {
                    throw new RuntimeException(String.format("Invalid file format: too many lines. Expected: %d", gridSize));
                }

                String[] numbers = line.split(separator);
                if (numbers.length != gridSize) {
                    throw new RuntimeException(
                            String.format("Invalid file format: invalid column size for line %d. Expected: %d but got %d",
                                    i + 1, gridSize, numbers.length));
                }

                j = 0;
                for (String number : numbers) {
                    grid[i][j] = Integer.valueOf(number);
                    j++;
                }
                i++;
            }
        } finally {
            is.close();
        }

        return grid;
    }
}
