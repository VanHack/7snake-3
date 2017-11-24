# Seven Snake Problem

## RUNNING

java -cp lib/commons-io-2.6jar:. br.com.rtp.SevenSnake input.csv

## INPUT

`input.csv` is a CSV file with one grid N x N. Each cell have a number between 1 and 256.

E.g.: 4 x 4 grid

```
1,2,3,4
5,6,7,8
9,10,11,12
13,14,15,16
```

## OUTPUT

The program will write to standard output.

- In case there is no solution:

```
FAIL
```

- Else, it will print:
  - Two lines, each line represents a 7-Snake.
  - Each snake is represented by a list of 7 (x, y) points separated by commas.

