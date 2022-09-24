package code.java.dynamic_programming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

    private static int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }

    private static int coinChange(int[] coins, int balance, int[] memo) {
        if (balance < 0) return -1;
        if (balance == 0) return 0;
        if (memo[balance] > 0) return memo[balance];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChange(coins, balance - coin, memo);
            if (result >= 0 && result < min) {
                min = result + 1;
            }
        }
        return memo[balance] = (min == Integer.MAX_VALUE) ? -1 : min;
    }

    /**
     * This works fastest
     *
     * n - # of coins
     * m - target amount
     *
     * Time complexity: O(n * m) time
     * Space complexity: O(m) space
     *
     * https://leetcode.com/problems/coin-change/discuss/1894180
     */
    private static int coinChangeTabulation(int[] coins, int amount) {
        if (amount < 1) return 0;
        int[] table = new int[amount + 1];
        Arrays.fill(table, amount + 1); table[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                table[i] = Math.min(table[i], table[i - coin] + 1);
            }
        }
        return table[amount] > amount ? -1 : table[amount];
    }

    /**
     * Fewest coins needed, So BFS always gives shortest path (coins in this)
     *
     * n - # of coins
     * m - target amount
     *
     * Time complexity: O(n * m) time
     * Space complexity: O(m) space
     */
    private static int coinChangeBFS(int[] coins, int amount) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        int count = 0;
        boolean[] visited = new boolean[amount + 1];
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int balance = deque.poll();
                if (balance == amount) return count;
                if (balance > amount || visited[balance]) continue;
                visited[balance] = true;
                for (int coin : coins) {
                    int sum = balance + coin;
                    if (sum <= amount) {
                        deque.offer(sum);
                    }
                }
            }
            count += 1;
        }
        return -1;
    }

    /**
     * DFS + Pruning
     */
    private static int coinChangeDFS(int[] coins, int amount) {
        Arrays.sort(coins);
        int result = coinChangeDFS(coins, amount, coins.length - 1, 0, Integer.MAX_VALUE);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int coinChangeDFS(int[] coins, int amount, int index, int count, int min) {
        if (amount == 0) return Math.min(count, min);
        if (index < 0) return min;
        for (int i = amount / coins[index]; i >= 0; i--) {
            if (count + i >= min) return min; // We use larger coins first.
            min = coinChangeDFS(coins, amount - coins[index] * i, index - 1, count + i, min);
        }
        return min;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (amount, noOfWays) -> {
            System.out.println("Amount - " + amount + ", Minimum coins required - " + noOfWays);
        };
        //
        int[] coins = new int[] {1, 2, 5};
        int amount = 11;
        // Memoized
        logger.accept(amount, coinChange(coins, amount));
        // Tabulation
        logger.accept(amount, coinChangeTabulation(coins, amount));
        // BFS
        logger.accept(amount, coinChangeBFS(coins, amount));
        // DFS
        logger.accept(amount, coinChangeDFS(coins, amount));
        amount = 7;
        logger.accept(amount, coinChange(coins, amount));
        logger.accept(amount, coinChangeTabulation(coins, amount));
        logger.accept(amount, coinChangeBFS(coins, amount));
        logger.accept(amount, coinChangeDFS(coins, amount));
        //
        coins = new int[] {2};
        amount = 3;
        // Memoized
        logger.accept(amount, coinChange(coins, amount));
        // Tabulation
        logger.accept(amount, coinChangeTabulation(coins, amount));
        // BFS
        logger.accept(amount, coinChangeBFS(coins, amount));
        // DFS
        logger.accept(amount, coinChangeDFS(coins, amount));
        //
        coins = new int[] {1};
        amount = 0;
        // Memoized
        logger.accept(amount, coinChange(coins, amount));
        // Tabulation
        logger.accept(amount, coinChangeTabulation(coins, amount));
        // BFS
        logger.accept(amount, coinChangeBFS(coins, amount));
        // DFS
        logger.accept(amount, coinChangeDFS(coins, amount));
        //
        coins = new int[] {1};
        amount = 2;
        // Memoized
        logger.accept(amount, coinChange(coins, amount));
        // Tabulation
        logger.accept(amount, coinChangeTabulation(coins, amount));
        // BFS
        logger.accept(amount, coinChangeBFS(coins, amount));
        // DFS
        logger.accept(amount, coinChangeDFS(coins, amount));
    }

}
