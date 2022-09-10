package code.java.arrays;

import code.java.utils.MethodsUtility;

/**
 * https://leetcode.com/problems/reshape-the-matrix/
 */
public class MatrixReshape {

    /**
     * Problem statement was not clear, direct row and column case its working.
     * Even the column given in any order of total or row's column is working by changing the <code>if (c > r) c /= r;</code>
     */
    private static int[][] matrixReshapeInitialTry(int[][] mat, int r, int c) {
        int n = mat.length, m = mat[0].length;
        if (n == r && m == c) {
            return mat;
        }
        int[][] result = new int[r][c];
        int rr = 0, cc = 0;
        for (int[] row : mat) {
            for (int col : row) {
                result[rr][cc++] = col;
                if (cc == c) {
                    rr += 1;
                    cc = 0;
                }
            }
        }
        return result;
    }

    private static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length, m = mat[0].length;
        if (n * m != r * c) return mat;
        int[][] result = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            result[i / c][i % c] = mat[i / m][i % m];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 2, 3, 4}
        };
        int row = 2, col = 4;
        int[][] res = matrixReshape(matrix, row, col);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(res, row, col / row);

        System.exit(1);

        int[][] mat = {
                {1, 2}, {3, 4}
        };
        int r = 1, c = 4;
        int[][] result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
        //
        mat = new int[][] {
                {1, 2}, {3, 4}
        };
        // r = 2; c = 2;
        r = 2; c = 4;
        result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
        //
        mat = new int[][] {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}
        };
        // r = 6; c = 2;
        r = 6; c = 12;
        result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
        //
        mat = new int[][] {
                {1, 2, 3, 4, 5, 6}
        };
        // r = 2; c = 3;
        r = 2; c = 6;
        result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
        //
        mat = new int[][] {
                {1, 2}, {3, 4}
        };
        // r = 4; c = 1;
        r = 4; c = 4;
        result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
        //
        mat = new int[][] {
                {1, 2}, {3, 4}
        };
        // r = 4; c = 1;
        r = 4; c = 1;
        result = matrixReshape(mat, r, c);
        // MethodsUtility.printArray(result, r, c);
        MethodsUtility.printArray(result, r, c / r);
    }

}
