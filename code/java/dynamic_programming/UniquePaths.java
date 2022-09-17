package code.java.dynamic_programming;

import code.java.utils.MethodsUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

    private static int uniquePaths(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        return uniquePaths(m, n, memo);
    }

    /**
     * Time complexity: O(m * n) time, where m & n are the possible ways
     * Space complexity: O(m + n) space, Multi-linear function
     */
    private static int uniquePaths(int m, int n, int[][] memo) {
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        if (memo[m][n] > 0) return memo[m][n];
        int result = uniquePaths(m - 1, n, memo) + uniquePaths(m, n - 1, memo);
        memo[m][n] = result;
        return result;
    }

    /**
     * Time complexity: O(m * n), where m is the no. of rows, n is no. of columns
     * Space complexity: O(mn)
     */
    private static int uniquePathsTabulation(int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        table[1][1] = 1;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                var current = table[i][j];
                if (j + 1 <= n) table[i][j + 1] += current;
                if (i + 1 <= m) table[i + 1][j] += current;
            }
        }
        // MethodsUtility.printArray(table, m + 1, n + 1);
        return table[m][n];
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (grid, result) -> {
            System.out.println("M - " + grid[0] + ", N - " + grid[1] + ", Result - " + result);
        };
        //
        int m = 3, n = 7;
        int uniquePaths = uniquePaths(m, n);
        logger.accept(new int[] {m, n}, uniquePaths);
        // uniquePaths = uniquePathsTabulation(m, n);
        // logger.accept(new int[] {m, n}, uniquePaths);
        //
        m = 3; n = 2;
        uniquePaths = uniquePaths(m, n);
        logger.accept(new int[] {m, n}, uniquePaths);
        uniquePaths = uniquePathsTabulation(m, n);
        logger.accept(new int[] {m, n}, uniquePaths);
    }

}
