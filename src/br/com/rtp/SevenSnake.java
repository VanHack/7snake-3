package br.com.rtp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SevenSnake implements Runnable {

    private int gridSize;
    private int[][] grid;

    private Map<Long, List<Snake>> snakesFound = new HashMap<>();

    private List<Snake> solution = null;

    SevenSnake(int[][] grid) {
        this.grid = grid;
        this.gridSize = grid.length;
    }

    private List<Coord> getAllCoords() {
        List<Coord> allCoords = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                allCoords.add(new Coord(i, j, gridSize));
            }
        }
        return allCoords;
    }

    private DirectionSet getPossibleDirections(DirectionSet possibleDirections, Direction lastDirection, Direction newDirection) {
        if (lastDirection == newDirection) {
            return possibleDirections;
        }
        return possibleDirections.without(Direction.opposite(newDirection));
    }

    private List<CoordSet> getSnake(int counter, CoordSet snake, Coord startPoint, DirectionSet possibleDirections, Direction lastDirection) {
        if (startPoint == null) {
            return Collections.emptyList();
        }

        if (counter == 0) {
            return new ArrayList<>(Arrays.asList(snake));
        }

        counter--;
        List<CoordSet> result = new ArrayList<>();
        for (Direction dir : possibleDirections) {
            Coord newCoord = startPoint.get(dir);
            DirectionSet newPossibleDirections = getPossibleDirections(possibleDirections, lastDirection, dir);
            result.addAll(getSnake(counter, snake.add(newCoord), newCoord, newPossibleDirections, dir));
        }

        return result;
    }

    private List<CoordSet> get3Snake(Coord startPoint, Direction direction) {
        return getSnake(2, new CoordSet(startPoint), startPoint, DirectionSet.ALL.without(Direction.opposite(direction)), direction);
    }

    private List<CoordSet> combine(Coord center, List<CoordSet> list1, List<CoordSet> list2, Coord exception) {
        if (list1.size() == 0 || list2.size() == 0) {
            return Collections.emptyList();
        }

        List<CoordSet> result = new ArrayList<CoordSet>();

        if (exception != null) {
            list1 = list1.stream().filter(set -> !set.contains(exception)).collect(Collectors.toList());
            list2 = list2.stream().filter(set -> !set.contains(exception)).collect(Collectors.toList());
        }


        for (CoordSet set1: list1) {
            for (CoordSet set2: list2) {
                HashSet<Coord> set = new HashSet<>();
                set.addAll(set1.getSet());
                set.add(center);
                set.addAll(set2.getSet());
                result.add(new CoordSet(set));
            }
        }

        return result;
    }

    private Snake findPair(Snake snake) {
        List<Snake> snakes = snakesFound.get(snake.getValue());
        if (snakes == null) {
            return null;
        }
        return snakes.stream()
                .filter(s -> snake.matches(s))
                .findFirst()
                .orElse(null);
    }

    private void saveSnake(Snake snake) {
        List<Snake> snakes = snakesFound.get(snake.getValue());
        if (snakes == null) {
            snakesFound.put(snake.getValue(), new ArrayList(Arrays.asList(snake)) );
        } else {
            snakes.add(snake);
        }
    }

    private void saveSnakes(List<Snake> snakes) {
        snakes.stream().forEach(this::saveSnake);
    }

    private boolean process(Coord snakeCenter) {
        // given a center, to create a snake, I have to expand
        // both sides in any direction, which give me 6 possible combinations
        //   |           |    |
        //   C    -C-   -C    C-    C-   -C
        //   |                      |     |

        List<CoordSet> up = get3Snake(snakeCenter.up(), Direction.UP);
        List<CoordSet> right = get3Snake(snakeCenter.right(), Direction.RIGHT);
        List<CoordSet> down = get3Snake(snakeCenter.down(), Direction.DOWN);
        List<CoordSet> left = get3Snake(snakeCenter.left(), Direction.LEFT);

        List<CoordSet> snakesCoords = new ArrayList<>();
        snakesCoords.addAll(combine(snakeCenter, up, down, null));
        snakesCoords.addAll(combine(snakeCenter, right, left, null));
        snakesCoords.addAll(combine(snakeCenter, left, up, snakeCenter.upLeft()));
        snakesCoords.addAll(combine(snakeCenter, up, right, snakeCenter.upRight()));
        snakesCoords.addAll(combine(snakeCenter, right, down, snakeCenter.downRight()));
        snakesCoords.addAll(combine(snakeCenter, down, left, snakeCenter.downLeft()));

        // did we find any solution?
        List<Snake> snakes = snakesCoords.stream().map(coords -> new Snake(grid, coords)).collect(Collectors.toList());
        for (Snake snake : snakes) {
            Snake pair = findPair(snake);
            if (pair != null) {
                solution = Arrays.asList(snake, pair);
                return true;
            }
        }

        // save snakes found
        saveSnakes(snakes);
        return false;
    }

    @Override
    public void run() {
        // We will expand snake search by the center of the snake.
        // We will shuffle all possible center points so that we may
        // find faster a possible solution, since centers that are near
        // to each other is more likely to intersect (which may not lead
        // to a possible solution)
        List<Coord> allCoords = getAllCoords();
        Collections.shuffle(allCoords);
        for (Coord snakeCenter: allCoords) {
            if (process(snakeCenter)) {
                return; // solution has been found, stop!
            }
        }
    }

    public List<Snake> getSolution() {
        return solution;
    }

    public void printResults() {
        if (solution == null) {
            System.out.println("FAIL");
        } else {
            for (Snake snake : solution) {
                System.out.println(snake.toString());
            }
        }
    }

    public static void main(String[] args)  {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java br.com.rtp.SevenSnake <input.csv>");
                System.exit(1);
            }

            SevenSnake sevenSnake = new SevenSnake(InputReader.read(args[0]));
            sevenSnake.run();
            sevenSnake.printResults();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
