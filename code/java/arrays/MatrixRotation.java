package code.java.arrays;

import code.java.utils.MethodsUtility;

public class MatrixRotation {

    /**
     * Time complexity: O(n^2) time
     * Space complexity: O(1) space
     */
    static void rotate(int[][] matrix, int rows, int columns) {
        for (int layer = 0; layer < rows/2; layer++) {
            int first = layer;
            int last = rows - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                // Top
                int top = matrix[first][i];
                // Left to Top
                matrix[first][i] = matrix[last - offset][first];
                // Bottom to Left
                matrix[last - offset][first] = matrix[last][last - offset];
                // Right to Bottom
                matrix[last][last - offset] = matrix[i][last];
                // Top to Right
                matrix[i][last] = top;
            }
        }
        System.out.println("Matrix rotate - ");
        MethodsUtility.printArray(matrix, rows, columns);
    }

    public static void main(String[] args) {
        int rows = 4, columns = 4;
        int[][] matrix = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = count += 1;
            }
        }
        System.out.println("Matrix input - ");
        MethodsUtility.printArray(matrix, rows, columns);
        //
        rotate(matrix, rows, columns);
    }

}
