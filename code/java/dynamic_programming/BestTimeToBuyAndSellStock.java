package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    private static int maxProfit(int[] prices) {
        int stock_price = prices[0], net_profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < stock_price) {
                stock_price = prices[i];
            }
            int day_profit = prices[i] - stock_price;
            if (day_profit > net_profit) {
                net_profit = day_profit;
            }
        }
        return net_profit;
    }

    /**
     * Optimal solution is Kadane's algorithm
     */
    private static int maxProfitOptimal(int[] prices) {
        int day_profit = 0, net_profit = 0;
        for (int i = 1; i < prices.length; i++) {
            day_profit = Math.max(0, day_profit + prices[i] - prices[i - 1]); // 0 - In this case, looking for positive net profit
            if (day_profit > net_profit) {
                net_profit = day_profit;
            }
        }
        return net_profit;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (prices, profit) -> {
            System.out.println("Prices - " + Arrays.toString(prices) + ", Max Profit - " + profit);
        };
        //
        int[] prices = {7, 1, 5, 3, 6, 4};
        int max_profit = maxProfit(prices);
        logger.accept(prices, max_profit);
        max_profit = maxProfitOptimal(prices);
        logger.accept(prices, max_profit);
        //
        prices = new int[] {7, 6, 4, 3, 1};
        max_profit = maxProfit(prices);
        logger.accept(prices, max_profit);
        max_profit = maxProfitOptimal(prices);
        logger.accept(prices, max_profit);
    }

}
