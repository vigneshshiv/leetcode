package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/
 */
public class StraightLineCheck {

    private static boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        int x0 = coordinates[0][0], y0 = coordinates[0][1], x1 = coordinates[1][0], y1 = coordinates[1][1];
        int dx = x1 - x0, dy = y1 - y0;
        for (int[] points : coordinates) {
            int x = points[0], y = points[1];
            if (dx * (y - y1) != dy * (x - x1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Boolean> logger = (coordinates, straightLine) -> {
            System.out.println("Coordinates - " + Arrays.deepToString(coordinates) + ", Forms Straight Line - " + straightLine);
        };
        //
        int[][] coordinates = {
                {1, 1}, {2, 2}, {3, 3}
        };
        boolean formsStraightLine = checkStraightLine(coordinates);
        logger.accept(coordinates, formsStraightLine);
        //
        coordinates = new int[][] {
                {2, 1}, {4, 2}, {6, 3}
        };
        formsStraightLine = checkStraightLine(coordinates);
        logger.accept(coordinates, formsStraightLine);
        //
        coordinates = new int[][] {
                {1, 2}, {2, 3}, {3, 4}, {4, 5}
        };
        formsStraightLine = checkStraightLine(coordinates);
        logger.accept(coordinates, formsStraightLine);
        //
        coordinates = new int[][] {
                {2, 4}, {2, 5}, {2, 8}
        };
        formsStraightLine = checkStraightLine(coordinates);
        logger.accept(coordinates, formsStraightLine);
        //
        coordinates = new int[][] {
                {1, 2}, {2, 3}, {3, 5}
        };
        formsStraightLine = checkStraightLine(coordinates);
        logger.accept(coordinates, formsStraightLine);
    }

}
