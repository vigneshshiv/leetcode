package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/richest-customer-wealth/
 */
public class RichestCustomer {

    /**
     * Time complexity: O(nm), where n * m is the dimension of accounts
     * Space complexity: O(1)
     */
    private static int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        for (int[] person : accounts) {
            int wealth = Arrays.stream(person).sum();
            maxWealth = Math.max(wealth, maxWealth);
        }
        return maxWealth;
    }

    private static int maximumWealthOneLiner(int[][] accounts) {
        return Arrays.stream(accounts)
                .mapToInt(person -> Arrays.stream(person).sum())
                .max()
                .getAsInt();
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, result) ->
                System.out.println("Input - " + Arrays.deepToString(input) + ", Result - " + result);
        //
        int[][] accounts = {
                {1, 2, 3}, {3, 2, 1}
        };
        int wealth = maximumWealth(accounts);
        logger.accept(accounts, wealth);
        //
        accounts = new int[][] {
                {1, 5}, {7, 3}, {3, 5}
        };
        wealth = maximumWealth(accounts);
        logger.accept(accounts, wealth);
        //
        accounts = new int[][] {
                {2, 8, 7}, {7, 1, 3}, {1, 9, 5}
        };
        wealth = maximumWealth(accounts);
        logger.accept(accounts, wealth);
    }

}
