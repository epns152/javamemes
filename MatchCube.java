package own_jokes;

import java.util.Scanner;

public class MatchCube {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int countOfCubes, countOfMatches1, countOfMatches2;

        countOfCubes = s.nextInt();
        int edge = countEdge(countOfCubes);
        countOfMatches1 = countMatches(countOfCubes, edge, false, false);
        countOfMatches2 = countMatches(countOfCubes, edge + 1, false, false);
        System.out.print(Math.min(countOfMatches1, countOfMatches2));
    }

    public static int countEdge (int countOfCubes) {
        int edge;
        edge = (int) Math.floor(Math.cbrt(countOfCubes));
        return edge;
    }

    public static int countMatches (int countOfCubes, int edge, boolean hasFloor, boolean hasWall) {
        int countOfFloors;
        int countOfMatches = 0;
        int countOfLeftCubes = countOfCubes;
        if (countOfCubes % edge != 0) {
            countOfFloors = countOfCubes / edge + 1;
        } else {
            countOfFloors = countOfCubes / edge;
        }
        if (countOfFloors % edge == 0) {
            countOfFloors /= edge;
        } else {
            countOfFloors = countOfFloors / edge + 1;
        }
        for (int i = 0; i < countOfFloors; i++) {
            if (countOfFloors - i == 1) {
                edge = (int) Math.ceil(Math.sqrt(countOfLeftCubes));
                for (int line = 1; line <= edge; line++) {
                    if (countOfLeftCubes > 0) {
                        countOfMatches += oneLine(edge, countOfLeftCubes, hasFloor, hasWall);
                        countOfLeftCubes -= edge;
                        hasWall = true;
                    }
                }
                hasFloor = true;
                hasWall = false;
            } else {
                for (int line = 1; line <= edge; line++) {
                    if (countOfLeftCubes > 0) {
                        countOfMatches += oneLine(edge, countOfLeftCubes, hasFloor, hasWall);
                        countOfLeftCubes -= edge;
                        hasWall = true;
                    }
                }
                hasFloor = true;
                hasWall = false;
            }
        }
        return countOfMatches;
    }

    public static int oneLine (int edge, int countOfLeftCubes, boolean hasFloor, boolean hasWall) {
        int result, matchesForCube;
        if (hasFloor && hasWall) {
            result = 5;
            matchesForCube = 3;
        } else if (hasFloor || hasWall) {
            result = 8;
            matchesForCube = 5;
        } else {
            result = 12;
            matchesForCube = 8;
        }
        countOfLeftCubes--;
        for (int i = 1; i < edge; i++) {
            if (--countOfLeftCubes >= 0) {
                result += matchesForCube;
            }
        }
        return result;
    }
}
