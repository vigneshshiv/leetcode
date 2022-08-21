package code.java.searching;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Matrix is sorted in a row wise and column wise manner
 *
 * [
 *  10, 20, 30, 40
 *  15, 25, 35, 45
 *  28, 29, 37, 49
 *  33, 34, 38, 50
 * ]
 *
 */
public class MatrixSearch {

    /**
     * Time complexity: O(log n + log m)
     * Space complexity: O(1)
     */
    private static int[] matrixSearch(int[][] matrix, int target) {
        if (Objects.isNull(matrix) || matrix.length == 0 || Objects.isNull(matrix[0]) || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (target == matrix[row][col]) {
                return new int[] {row, col};
            }
            if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {28, 29, 37, 49},
                {33, 34, 38, 50}
        };
        int target = 30;
        int[] result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 49;
        result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 28;
        result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 77;
        result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 28;
        result = matrixSearch(new int[][] {}, target);
        System.out.println("Target - " + target + ", Result - " + Arrays.toString(result));
    }

}
