package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/triangle/
 */
public class Triangle {

    /**
     * https://leetcode.com/problems/triangle/discuss/38724
     */
    private static int minimumTotal(List<List<Integer>> triangle) {
        int[] table = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                table[j] = Math.min(table[j], table[j + 1]) + triangle.get(i).get(j);
            }
        }
        return table[0];
    }

    private static int minimumTotalRecursive(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n][n];
        return minimumTotal(triangle, memo, 0, 0);
    }

    private static int minimumTotal(List<List<Integer>> triangle, int[][] memo, int level, int idx) {
        if (memo[level][idx] > 0) return memo[level][idx];
        int total = triangle.get(level).get(idx);
        if (level < triangle.size() - 1) {
            total += Math.min(minimumTotal(triangle, memo,level + 1, idx), minimumTotal(triangle, memo, level + 1, idx + 1));
        }
        return memo[level][idx] = total;
    }

    public static void main(String[] args) {
        BiConsumer<List<List<Integer>>, Integer> logger = (input, total) -> System.out.println("Input - " + input + ", Total - " + total);
        //
        List<List<Integer>> triangle = Arrays.asList(
            Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3)
        );
        int total = minimumTotal(triangle);
        logger.accept(triangle, total);
        total = minimumTotalRecursive(triangle);
        logger.accept(triangle, total);
        //
        triangle = Arrays.asList(
                Arrays.asList(-10)
        );
        total = minimumTotal(triangle);
        logger.accept(triangle, total);
        total = minimumTotalRecursive(triangle);
        logger.accept(triangle, total);
        //
        triangle = Arrays.asList(
                Arrays.asList(-1), Arrays.asList(2, 3), Arrays.asList(1, -1, -3)
        );
        total = minimumTotal(triangle);
        logger.accept(triangle, total);
        total = minimumTotalRecursive(triangle);
        logger.accept(triangle, total);
    }

}
