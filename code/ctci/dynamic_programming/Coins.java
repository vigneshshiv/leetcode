package code.ctci.dynamic_programming;

import java.util.function.BiConsumer;

public class Coins {

    /**
     * Time complexity: O(n^m), where n is the length of denoms, m is target amount.
     * Space complexity: O(m)
     */
    private static int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length - 1) {
            return 1;
        }
        var ways = 0;
        var denomAmount = denoms[index];
        for (int i = 0; i * denomAmount <= amount; i++) {
            var remainingAmount = amount - i * denomAmount;
            ways += makeChange(remainingAmount, denoms, index + 1);
        }
        return ways;
    }

    /**
     * Time complexity: O(n * m) time, where n is the length of denoms, m is target amount.
     * Space complexity: O(m^2)
     */
    private static int makeChangeMemo(int amount, int[] denoms, int index, int[][] memo) {
        if (memo[amount][index] > 0) {
            return memo[amount][index];
        }
        if (index >= denoms.length - 1) {
            return 1;
        }
        var ways = 0;
        var denomAmount = denoms[index];
        for (int i = 0; i * denomAmount <= amount; i++) {
            var remainingAmount = amount - i * denomAmount;
            ways += makeChangeMemo(remainingAmount, denoms, index + 1, memo);
        }
        memo[amount][index] = ways;
        return ways;
    }

    /**
     * Time complexity: O(n * m) time, where n is the length of denoms, m is target amount.
     * Space complexity: O(m) space
     */
    private static int makeChangeTabulation(int amount, int[] denoms) {
        int[] table = new int[amount + 1];
        table[0] = 1;
        for (int coin : denoms) {
            for (int i = coin; i <= amount; i++) {
                table[i] += table[i - coin];
            }
        }
        return table[amount];
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (amount, noOfWays) -> System.out.println("Amount - " + amount + ", No of ways - " + noOfWays);
        int[] denoms = {25, 10, 5, 1};
        int amount = 12;
        logger.accept(amount, makeChange(amount, denoms, 0));
        // Memoized
        int[][] memo = new int[amount + 1][denoms.length];
        logger.accept(amount, makeChangeMemo(amount, denoms, 0, memo));
        // Tabulation
        logger.accept(amount, makeChangeTabulation(amount, denoms));
    }

}
