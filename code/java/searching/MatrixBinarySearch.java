package code.java.searching;

import code.java.utils.MethodsUtility;

import java.util.Arrays;
import java.util.Objects;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * It also works with <code>MatrixSearch</code> reference.
 *
 */
public class MatrixBinarySearch {

    /**
     * Time complexity: O(log n + log m)
     * Space complexity: O(1)
     */
    private static int[] matrixSearch(int[][] matrix, int target) {
        if (Objects.isNull(matrix) || matrix.length == 0 || Objects.isNull(matrix[0]) || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // Base case if only one row
        if (rows == 1) {
            return binarySearch(matrix, target, 0, 0, cols - 1);
        }
        int rowStart = 0, rowEnd = rows - 1;
        int colMid = cols / 2;
        int mid = 0;
        // More than 2 row check
        while (rowStart < (rowEnd - 1)) {
            mid = rowStart + (rowEnd - rowStart) / 2;
            if (target == matrix[mid][colMid]) {
                return new int[] {mid, colMid};
            }
            if (target < matrix[mid][colMid]) {
                rowEnd = mid;
            } else {
                rowStart = mid;
            }
        }
        // 2 rows case
        if (target == matrix[rowStart][colMid]) {
            return new int[] {rowStart, colMid};
        }
        if (target == matrix[rowStart + 1][colMid]) {
            return new int[] {rowStart + 1, colMid};
        }
        // Columns check
        if (colMid - 1 >= 0 && target <= matrix[rowStart][colMid - 1]) {
            return binarySearch(matrix, target, rowStart, 0, colMid - 1);
        }
        if (colMid + 1 < cols && target >= matrix[rowStart][colMid + 1] && target <= matrix[rowStart][cols - 1]) {
            return binarySearch(matrix, target, rowStart, colMid + 1, cols - 1);
        }
        if (colMid - 1 >= 0 && target <= matrix[rowStart + 1][colMid - 1]) {
            return binarySearch(matrix, target, rowStart + 1, 0, colMid - 1);
        } else {
            return binarySearch(matrix, target, rowStart + 1, colMid + 1, cols - 1);
        }
    }

    private static int[] binarySearch(int[][] matrix, int target, int row, int colStart, int colEnd) {
        int mid = 0;
        while (colStart <= colEnd) {
            mid = colStart + (colEnd - colStart) / 2;
            if (target == matrix[row][mid]) {
                return new int[] {row, mid};
            }
            if (target < matrix[row][mid]) {
                colEnd = mid - 1;
            } else {
                colStart = mid + 1;
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int rows = 4, columns = 4;
        int[][] matrix = new int[rows][columns];
        //
        Arrays.setAll(matrix, r -> {
            Arrays.setAll(matrix[r], c -> (r * matrix[r].length) + c + 1);
            return matrix[r];
        });
        //
        System.out.println("Matrix input - ");
        MethodsUtility.printArray(matrix, rows, columns);
        int target = 9;
        int[] result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Index - " + Arrays.toString(result));
        //
        target = 14;
        result = matrixSearch(matrix, target);
        System.out.println("Target - " + target + ", Index - " + Arrays.toString(result));
        //
        target = 14;
        result = matrixSearch(new int[][]{}, target);
        System.out.println("Empty Matrix { {} }, Target - " + target + ", Index - " + Arrays.toString(result));
        //
        int[][] input = { {1}, {3} };
        target = 0;
        result = matrixSearch(input, target);
        System.out.println("Runtime error matrix, Target - " + target + ", Index - " + Arrays.toString(result));
    }

}
