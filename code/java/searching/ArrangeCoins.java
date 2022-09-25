package code.java.searching;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/arranging-coins/
 */
public class ArrangeCoins {

    /**
     * n = 6
     * $
     * $$
     * $$$
     * $
     * Answer - 3 completely filled rows
     *
     * Time complexity: O(sqrt(n))
     * Space complexity: O(1)
     */
    private static int arrangeCoinsEasy(int n) {
        int i = 1; // initialize first row
        while ((n -= i) >= 0) {
            i += 1;
        }
        return i - 1; // return last row is completed row
    }

    /**
     * Same as above
     *
     * Time complexity: O(16) times, i.e, O(1)
     * space complexity: O(1)
     */
    private static int arrangeCoinsBits(int n) {
        int mask = 1 << 15;
        long result = 0;
        while (mask > 0) {
            long k = result | mask;
            long coinsFilled = (k * (k + 1)) / 2;
            if (coinsFilled == n) {
                return (int) k;
            }
            if (coinsFilled < n) {
                result = k;
            }
            mask >>= 1;
        }
        return (int) result;
    }

    /**
     * Math approach
     * Formula:
     *  - (K * (K + 1)) / 2 <= N
     *  - K^2 + K = 2N
     *  - (K + 1/2)^2 <= 2N + 1/4
     *  - K <= sqrt(2N + 1/4) - 1/2
     *
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    private static int arrangeCoinsMath(int n) {
        return (int) (Math.sqrt(2 * (long) n + 0.25) - 0.5);
    }

    /**
     * Binary Search
     *
     * Time complexity: O(log(n/2)), time complexity maximum can be O(30), which is equivalent to O(1)
     * Space complexity: O(1)
     */
    private static int arrangeCoinsBinarySearch(int n) {
        if (n <= 1) return n;
        if (n <= 3) return n == 3 ? 2 : 1;
        long start = 2, end = n / 2;
        while (start <= end) {
            long k = start + (end - start) / 2;
            long coinsFilled = (k * (k + 1)) / 2;
            if (coinsFilled == n) {
                return (int) k;
            }
            if (coinsFilled < n) {
                start = k + 1;
            } else {
                end = k - 1;
            }
        }
        // Since we are looking for no of rows, if coinsFilled < n, start exceeded the range.
        return (int) end;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Integer> logger = (input, result) -> {
            System.out.println(input[0] + ": N - " + input[1] + ", No of rows filled - " + result);
        };
        //
        int n = 5;
        logger.accept(new String[] {"Easy Approach", String.valueOf(n)}, arrangeCoinsEasy(n));
        logger.accept(new String[] {"Bits Approach", String.valueOf(n)}, arrangeCoinsBits(n));
        logger.accept(new String[] {"Math Approach", String.valueOf(n)}, arrangeCoinsMath(n));
        logger.accept(new String[] {"Binary Search Approach", String.valueOf(n)}, arrangeCoinsBinarySearch(n));
        //
        n = 8;
        logger.accept(new String[] {"Easy Approach", String.valueOf(n)}, arrangeCoinsEasy(n));
        logger.accept(new String[] {"Bits Approach", String.valueOf(n)}, arrangeCoinsBits(n));
        logger.accept(new String[] {"Math Approach", String.valueOf(n)}, arrangeCoinsMath(n));
        logger.accept(new String[] {"Binary Search Approach", String.valueOf(n)}, arrangeCoinsBinarySearch(n));
    }

}
