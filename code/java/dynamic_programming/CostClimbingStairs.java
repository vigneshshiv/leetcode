package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class CostClimbingStairs {

    private static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n];
        return Math.min(minCost(cost, n - 1, memo), minCost(cost, n - 2, memo));
    }

    private static int minCost(int[] cost, int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return cost[n];
        if (memo[n] > 0) return memo[n];
        memo[n] = cost[n] + Math.min(minCost(cost, n - 1, memo), minCost(cost, n - 2, memo));
        return memo[n];
    }

    private static int minCostClimbingStairsOptimal(int[] cost) {
        int n = cost.length;
        int a = cost[0], b = cost[1];
        if (n <= 2) return Math.min(a, b);
        for (int i = 2; i < n; i++) {
            int result = cost[i] + Math.min(a, b);
            a = b; b = result;
        }
        return Math.min(a, b);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (cost, minCost) -> {
            System.out.println("Cost - " + Arrays.toString(cost) + ", Min cost - " + minCost);
        };
        //
        int[] cost = {10, 15, 20};
        int minCost = minCostClimbingStairs(cost);
        logger.accept(cost, minCost);
        minCost = minCostClimbingStairsOptimal(cost);
        logger.accept(cost, minCost);
        System.out.println();
        //
        cost = new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        minCost = minCostClimbingStairs(cost);
        logger.accept(cost, minCost);
        minCost = minCostClimbingStairsOptimal(cost);
        logger.accept(cost, minCost);
    }

}
