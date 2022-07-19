package code.ctci.arrays;

public class ZeroMatrix {

    static void printArray(int[][] matrix, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix.length; j++) {
            matrix[row][j] = 0;
        }
    }

    static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[i][col] = 0;
        }
    }

    /**
     * Time complexity: O(mn) time
     * Space complexity: O(mn) space
     */
    static void setZeros(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];
        // Store the row and column index with value 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }
        // Nullify Rows
        for (int i = 0; i < rows.length; i++) {
            if (rows[i]) nullifyRow(matrix, i);
        }
        // Nullify Columns
        for (int j = 0; j < columns.length; j++) {
            if (columns[j]) nullifyColumn(matrix, j);
        }
        printArray(matrix, matrix.length, matrix[0].length);
    }

    /**
     * Time complexity: O(mn) time
     * Space complexity: O(1) space
     */
    static void setZerosSpaceOptimal(int[][] matrix) {
        boolean rowHasZero = false, colHasZero = false;
        // Check if first row has zero
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }
        // Check if first col has zero
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }
        // Check rest of the rows & columns
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // Nullify rows based on values in first column
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) nullifyRow(matrix, i);
        }
        // Nullify columns based on values in first row
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[j][0] == 0) nullifyColumn(matrix, j);
        }
        // Nullify first row
        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }
        // Nullify first column
        if (colHasZero) {
            nullifyColumn(matrix, 0);
        }
        printArray(matrix, matrix.length, matrix[0].length);
    }

    public static void main(String[] args) {
        int rows = 4, columns = 4;
        int[][] matrix = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = count += 1;
                }
            }
        }
        System.out.println("Matrix input - ");
        printArray(matrix, rows, columns);
        // Set Zeros
        // setZeros(matrix);
        // Set Zeros with optimal space - O(1)
        setZerosSpaceOptimal(matrix);
    }

}
