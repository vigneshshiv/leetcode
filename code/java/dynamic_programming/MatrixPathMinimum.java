package code.java.dynamic_programming;

public class MatrixPathMinimum {

    private static int findPathMinimum(int[][] matrix) {
        return findPathMinimum(matrix, 0, 0, 0);
    }

    private static int findPathMinimum(int[][] matrix, int n, int m, int sum) {
        if (n == matrix.length - 1 && m == matrix[0].length - 1) {
            return sum + matrix[n][m];
        }
        if (n == matrix.length || m == matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        int pathSum = Math.min(findPathMinimum(matrix, n + 1, m, sum + matrix[n][m]),
                findPathMinimum(matrix, n, m + 1, sum + matrix[n][m]));
        return pathSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {4, 9, 7},
                {3, 8, 5},
                {1, 2, 6}
        };
        int result = findPathMinimum(matrix);
        System.out.println("Minimum Path Sum - " + result);
    }

}
