package code.java.searching;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class Matrix2DSearchII {

    private static boolean searchMatrix(int[][] matrix, int target) {
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
                return true;
            }
            if (target < matrix[mid][colMid]) {
                rowEnd = mid;
            } else {
                rowStart = mid;
            }
        }
        // 2 rows case
        if (target == matrix[rowStart][colMid]) {
            return true;
        }
        if (target == matrix[rowStart + 1][colMid]) {
            return true;
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

    private static boolean binarySearch(int[][] matrix, int target, int row, int colStart, int colEnd) {
        int mid = 0;
        while (colStart <= colEnd) {
            mid = colStart + (colEnd - colStart) / 2;
            if (target == matrix[row][mid]) {
                return true;
            }
            if (target < matrix[row][mid]) {
                colEnd = mid - 1;
            } else {
                colStart = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Boolean> logger = (target, result) -> System.out.println("Target - " + target + ", Result - " + result);
        //
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        System.out.println(Arrays.deepToString(matrix));
        boolean result = searchMatrix(matrix, target);
        logger.accept(target, result);
        System.out.println();
        //
        target = 20;
        System.out.println(Arrays.deepToString(matrix));
        result = searchMatrix(matrix, target);
        logger.accept(target, result);
        System.out.println();
        //
        matrix = new int[][] {
                {2, 5}, {2, 8}, {7, 9}, {7, 11}, {9, 11}
        };
        target = 7;
        System.out.println(Arrays.deepToString(matrix));
        result = searchMatrix(matrix, target);
        logger.accept(target, result);
    }

}
