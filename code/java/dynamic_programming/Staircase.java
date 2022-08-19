package code.java.dynamic_programming;

import java.util.function.BiConsumer;

public class Staircase {

    /**
     * Time complexity: O(3^n), where n is the no of branching on each recursive call
     * Space complexity: O(n)
     */
    private static int countWays(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }

    /**
     * Time complexity: O(n), where n is the no of branching on each recursive call
     * Space complexity: O(n^2)
     */
    private static int countWaysMemo(int n, int[] memo) {
        if (n > 0 && memo[n] > 0) return memo[n];
        if (n == 0) return 1;
        if (n < 0) return 0;
        var noOfWays = countWaysMemo(n - 1, memo) + countWaysMemo(n - 2, memo) + countWaysMemo(n - 3, memo);
        memo[n] = noOfWays;
        return noOfWays;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int countWaysByTabulation(int n) {
        if (n < 4) {
            return switch(n) {
                case 1 -> 1;
                case 2 -> 2;
                case 3 -> 4;
                default -> 0;
            };
        }
        int[] table = new int[n + 1];
        table[0] = 1;
        table[1] = 2;
        table[2] = 4;
        for (int i = 3; i < table.length; i++) {
            table[i] = table[i - 1] + table[i - 2] + table[i - 3];
        }
        return table[n - 1];
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (n, noOfWays) -> System.out.println("Input - " + n + ", No Of Ways - " + noOfWays);
        /*
        logger.accept(1, countWays(1));
        logger.accept(2, countWays(2));
        logger.accept(3, countWays(3));
        logger.accept(4, countWays(4));
        logger.accept(5, countWays(5));
        logger.accept(8, countWays(8));
        logger.accept(30, countWays(30));
        */
        // Memoization
        /*
        logger.accept(1, countWaysMemo(1, new int[2]));
        logger.accept(2, countWaysMemo(2, new int[3]));
        logger.accept(3, countWaysMemo(3, new int[4]));
        logger.accept(4, countWaysMemo(4, new int[5]));
        logger.accept(5, countWaysMemo(5, new int[6]));
        logger.accept(8, countWaysMemo(8, new int[9]));
        logger.accept(30, countWaysMemo(30, new int[31]));
        */
        // Tabulation
        logger.accept(1, countWaysByTabulation(1));
        logger.accept(2, countWaysByTabulation(2));
        logger.accept(3, countWaysByTabulation(3));
        logger.accept(4, countWaysByTabulation(4));
        logger.accept(5, countWaysByTabulation(5));
        logger.accept(8, countWaysByTabulation(8));
        logger.accept(30, countWaysByTabulation(30));
    }

}
