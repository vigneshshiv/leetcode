package code.java.arrays;

import code.java.utils.MethodsUtility;

import java.util.*;

/**
 * https://leetcode.com/problems/01-matrix/
 */
public class Matrix_01 {

    private static final int NO_DIRS = 4;
    private static final int[] DIRS = {0, 1, 0, -1, 0};

    private static int[][] updateMatrixInitialTry(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[][] result = new int[rows][cols];
        Arrays.setAll(result, r -> {
            Arrays.fill(result[r], 0);
            return result[r];
        });
        Set<String> visited = new HashSet<>();
        Map<String, Integer> table = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1) {
                    result[r][c] = findNearest1sPosition(mat, r, c, visited, table, r, c);
                    table.put(r + "#" + c, result[r][c]);
                }
            }
        }
        return result;
    }

    private static int findNearest1sPosition(int[][] mat, int r, int c, Set<String> visited, Map<String, Integer> table, int row, int col) {
        boolean rowInbounds = (0 <= r && r < mat.length);
        boolean colInbounds = (0 <= c && c < mat[0].length);
        //
        if (!rowInbounds || !colInbounds) return Integer.MAX_VALUE;
        if (mat[r][c] == 0) {
            return Math.abs(row - r) + Math.abs(col - c);
        }
        String key = r + "#" + c;
        if (table.containsKey(key)) {
            int nearest1s = table.get(key);
            if (r != row || c != col) {
                nearest1s += Math.abs(row - r) + Math.abs(col - c);
            }
            return nearest1s;
        }
        if (!visited.add(key)) {
            return Integer.MAX_VALUE;
        }
        int path = Integer.MAX_VALUE;
        for (int idx = 0; idx < NO_DIRS; idx++) {
            path = Math.min(path, findNearest1sPosition(mat, r + DIRS[idx], c + DIRS[idx + 1], visited, table, row, col));
        }
        visited.remove(key);
        return path;
    }

    private static int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) queue.offer(new int[] {r, c});
                else mat[r][c] = -1; // Marked as not processed yet!
            }
        }
        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int r = idx[0], c = idx[1];
            for (int i = 0; i < NO_DIRS; i++) {
                int nr = r + DIRS[i], nc = c + DIRS[i + 1];
                if (nr < 0 || nr == rows || nc < 0 || nc == cols || mat[nr][nc] != -1) {
                    continue;
                };
                mat[nr][nc] = mat[r][c] + 1;
                queue.offer(new int[] {nr, nc});
            }
        }
        return mat;
    }

    /**
     * https://leetcode.com/problems/01-matrix/discuss/1369741
     */
    private static int[][] updateMatrixDPTabulation(int[][] mat) {
        int rows = mat.length, cols = mat[0].length, INF = rows + cols;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) continue;
                int top = INF, left = INF;
                if (r - 1 >= 0) top = mat[r - 1][c];
                if (c - 1 >= 0) left = mat[r][c - 1];
                mat[r][c] = Math.min(top, left) + 1;
            }
        }
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                if (mat[r][c] == 0) continue;
                int bottom = INF, right = INF;
                if (r + 1 < rows) bottom = mat[r + 1][c];
                if (c + 1 < cols) right = mat[r][c + 1];
                mat[r][c] = Math.min(mat[r][c], Math.min(bottom, right) + 1);
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int rows = 3, cols = 3;
        int[][] matrix = {
                {0, 0, 0}, {0, 1, 0}, {1, 1, 1}
        };
        int[][] result = updateMatrix(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        MethodsUtility.printArray(result, rows, cols);
        //
        matrix = new int[][] {
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        };
        result = updateMatrixInitialTry(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        MethodsUtility.printArray(result, rows, cols);
        //
        rows = 10; cols = 10;
        matrix = new int[][] {
                {0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0}
        };
        result = updateMatrixDPTabulation(matrix);
        MethodsUtility.printArray(matrix, rows, cols);
        MethodsUtility.printArray(result, rows, cols);
    }

}
