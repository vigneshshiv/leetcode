package code.java.arrays;

import code.java.utils.MethodsUtility;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/where-will-the-ball-fall/
 */
public class BallFall {

    private static int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] result = new int[n];
        for (int i = 0; i < n; ++i) {
            int i1 = i, i2;
            for (int j = 0; j < m; ++j) {
                i2 = i1 + grid[j][i1];
                if (i2 < 0 || i2 >= n || grid[j][i2] != grid[j][i1]) {
                    i1 = -1;
                    break;
                }
                i1 = i2;
            }
            result[i] = i1;
        }
        return result;
    }

    public static void main(String[] args) {
        int rows = 5, cols = 5;
        int[][] grid = {
                {1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}
        };
        MethodsUtility.printArray(grid, rows, cols);
        int[] result = findBall(grid);
        System.out.println(Arrays.toString(result));
        //
        rows = 1; cols = 1;
        grid = new int[][] { {-1} };
        MethodsUtility.printArray(grid, rows, cols);
        result = findBall(grid);
        System.out.println(Arrays.toString(result));
        //
        rows = 4; cols = 6;
        grid = new int[][] {
                {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}
        };
        MethodsUtility.printArray(grid, rows, cols);
        result = findBall(grid);
        System.out.println(Arrays.toString(result));
    }

}
