package code.java.searching;

import code.java.utils.MethodsUtility;

/**
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 */
public class MatrixCountNegativeNumbers {

    private static int countNegativesEasy(int[][] grid) {
        int n = grid.length, m = grid[0].length, r = n - 1, c = 0, count = 0;
        while (r >= 0 && c < m) {
            if (grid[r][c] < 0) {
                r -= 1;
                count += m - c;
            } else {
                c += 1;
            }
        }
        return count;
    }

    private static int countNegatives(int[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            count += binarySearch(grid, r);
        }
        return count;
    }

    private static int binarySearch(int[][] grid, int row) {
        int low = 0, mid = 0, high = grid[row].length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (grid[row][mid] < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low >= grid[row].length ? 0 : grid[row].length - low;
    }

    public static void main(String[] args) {
        int rows = 4, columns = 4;
        int[][] matrix = {
                {4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}
        }; // 8
        System.out.println("Matrix input - ");
        MethodsUtility.printArray(matrix, rows, columns);
        int result = countNegativesEasy(matrix);
        System.out.println("Negative Numbers count - " + result);
        //
        rows = 2; columns = 2;
        matrix = new int[][] {
                {3, 2}, {1, 0}
        }; // 0
        System.out.println("Matrix input - ");
        MethodsUtility.printArray(matrix, rows, columns);
        result = countNegatives(matrix);
        System.out.println("Negative Numbers count - " + result);
        //
        rows = 3; columns = 4;
        matrix = new int[][] {
                {3, 2, 1, -1}, {5, 3, 2, 0}, {9, -2, -4, -5}
        }; // 4
        System.out.println("Matrix input - ");
        MethodsUtility.printArray(matrix, rows, columns);
        result = countNegatives(matrix);
        System.out.println("Negative Numbers count - " + result);
    }

}
