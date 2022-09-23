package code.java.arrays;

import code.java.utils.MethodsUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {

    private static List<Integer> spiralOrderInitialTry(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int c = i;
            while (c < n - i && i < m - i) {
                result.add(matrix[i][c++]);
            }
            // Reset column pos
            c -= 1;
            int r = i + 1;
            while (r < m - i && c < n) {
                result.add(matrix[r++][c]);
            }
            // Reset row pos & colum last pos
            r -= 1; c -= 1;
            while (c >= i && r != i) {
                result.add(matrix[r][c--]);
            }
            // Reset column pos
            r -= 1; c += 1;
            while (r >= (i + 1) && n != (i + 1)) {
                result.add(matrix[r--][c]);
            }
        }
        return result;
    }

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int rowStart = 0, colStart = 0, rowEnd = m - 1, colEnd = n - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse Right
            for (int j = colStart; j <= colEnd; j++) {
                result.add(matrix[rowStart][j]);
            }
            rowStart += 1;
            // Traverse Down
            for (int j = rowStart; j <= rowEnd; j++) {
                result.add(matrix[j][colEnd]);
            }
            colEnd -= 1;
            if (rowStart <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colStart; j--) {
                    result.add(matrix[rowEnd][j]);
                }
            }
            rowEnd -= 1;
            if (colStart <= colEnd) {
                // Traverse Up
                for (int j = rowEnd; j >= rowStart; j--) {
                    result.add(matrix[j][colStart]);
                }
            }
            colStart += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int rows = 3, cols = 3;
        int[][] matrix = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        List<Integer> result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
        //
        rows = 3; cols = 4;
        matrix = new int[][] {
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };
        result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
        //
        rows = 4; cols = 4;
        matrix = new int[][] {
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}
        };
        result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
        //
        rows = 3; cols = 1;
        matrix = new int[][] {
                {7}, {9}, {6}
        };
        result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
        //
        rows = 2; cols = 3;
        matrix = new int[][] {
                {2, 5, 8}, {4, 0, -1 }
        };
        result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
        //
        rows = 5; cols = 1;
        matrix = new int[][] {
                {1}, {2}, {3}, {4}, {5}
        };
        result = spiralOrder(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        System.out.println("Result - " + result);
    }

}
