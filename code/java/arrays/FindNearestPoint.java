package code.java.arrays;

import java.io.DataInput;

/**
 * https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 */
public class FindNearestPoint {

    private static int nearestValidPoint(int x, int y, int[][] points) {
        int distance = Integer.MAX_VALUE, idx = -1, i = 0;
        for (int[] coordinates : points) {
            if (coordinates[0] == x || coordinates[1] == y) { // Valid coordinates
                if (Math.abs(coordinates[0] - x) + Math.abs(coordinates[1] - y) < distance) {
                    distance = Math.abs(coordinates[0] - x) + Math.abs(coordinates[1] - y);
                    idx = i;
                }
            }
            i += 1;
        }
        return idx;
    }

    public static void main(String[] args) {
        int x = 3, y = 4;
        int[][] points = {
                {1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}
        };
        int nearestIndex = nearestValidPoint(x, y, points);
        System.out.println("Nearest Valid Point - " + nearestIndex);
        //
        points = new int[][] {
                {3, 4}
        };
        nearestIndex = nearestValidPoint(x, y, points);
        System.out.println("Nearest Valid Point - " + nearestIndex);
        //
        points = new int[][] {
                {2, 3}
        };
        nearestIndex = nearestValidPoint(x, y, points);
        System.out.println("Nearest Valid Point - " + nearestIndex);
    }

}
