package code.java.dynamic_programming;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

    private static int coinChange(int[] coins, int amount) {

        return 0;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (amount, noOfWays) -> {
            System.out.println("Amount - " + amount + ", No of ways - " + noOfWays);
        };
        //
        int[] denoms = new int[] {1, 2, 5};
        int amount = 11;
        // logger.accept(amount, makeChange(amount, denoms, denoms.length - 1));
        // Memoized
        int[][] memo = new int[amount + 1][denoms.length];
        // logger.accept(amount, makeChangeMemo(amount, denoms, denoms.length - 1, memo));
        // Tabulation
        // logger.accept(amount, makeChangeTabulation(amount, denoms));
        //
        denoms = new int[] {2};
        amount = 3;
        // logger.accept(amount, makeChange(amount, denoms, denoms.length - 1));
        // Memoized
        memo = new int[amount + 1][denoms.length];
        // logger.accept(amount, makeChangeMemo(amount, denoms, denoms.length - 1, memo));
        // Tabulation
        // logger.accept(amount, makeChangeTabulation(amount, denoms));
        //
        denoms = new int[] {1};
        amount = 0;
        // logger.accept(amount, makeChange(amount, denoms, denoms.length - 1));
        // Memoized
        memo = new int[amount + 1][denoms.length];
        // logger.accept(amount, makeChangeMemo(amount, denoms, denoms.length - 1, memo));
        // Tabulation
        // logger.accept(amount, makeChangeTabulation(amount, denoms));
    }

}
