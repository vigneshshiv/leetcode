package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/matrix-diagonal-sum/
 */
public class MatrixDiagonalSum {

    private static int diagonalSum(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        int sum = 0;
        int center = (matrix.length & 1) == 1 ? matrix.length / 2 : -1;
        int j = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
            if (center != i) {
                sum += matrix[i][j--];
            } else {
                j--;
            }
        }
        return sum;
    }

    private static int diagonalSumOptimal(int[][] matrix) {
        int sum = 0, n = matrix.length;
        if (n == 1) return matrix[0][0];
        for (int r = 0, c = n - 1; r < n; r++, c--) {
            sum += matrix[r][r] + matrix[c][r];
        }
        return (n & 1) == 0 ? sum : sum - matrix[n / 2][n / 2];
    }

    /**
     * https://leetcode.com/problems/matrix-diagonal-sum/discuss/2510386/Java-Simple-Solution-in-one-pass...
     */
    private static int diagonalSumOneLine(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        int center = (matrix.length & 1) == 1 ? matrix.length / 2 : -1;
        return IntStream.range(0, matrix.length).map(i -> {
            return (center != i)
                    ? matrix[i][i] + matrix[matrix.length - 1 - i][i]
                    : matrix[i][i];
        }).sum();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        Arrays.setAll(matrix, r -> {
            Arrays.setAll(matrix[r], c -> (r * matrix[r].length) + c + 1);
            return matrix[r];
        });
        int sum = diagonalSum(matrix);
        int oneLineSum = diagonalSumOptimal(matrix);
        System.out.println("Sum - " + sum + ", One line sum - " + oneLineSum);
        //
        Arrays.setAll(matrix, r -> {
            Arrays.setAll(matrix[r], c -> 1);
            return matrix[r];
        });
        sum = diagonalSum(matrix);
        oneLineSum = diagonalSumOneLine(matrix);
        System.out.println("Sum - " + sum + ", One line sum - " + oneLineSum);
        //
        int[][] matrix1 = new int[4][4];
        Arrays.setAll(matrix1, r -> {
            Arrays.setAll(matrix1[r], c -> 1);
            return matrix1[r];
        });
        sum = diagonalSum(matrix1);
        oneLineSum = diagonalSumOneLine(matrix);
        System.out.println("Sum - " + sum + ", One line sum - " + oneLineSum);
        //
        sum = diagonalSum(new int[][] { {2} });
        System.out.println("Sum - " + sum);
    }

}
