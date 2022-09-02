package code.java.arrays;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
 */
public class CountOddNumbers {

    private static int countOdds(int low, int high) {
        int diff = high - low;
        boolean bothOdd = (low & 1) == 1 && (high & 1) == 1;
        return (diff & 1) == 0 ? bothOdd ? (diff / 2) + 1 : (diff / 2) : (int) Math.ceil(diff / 2) + 1;
    }

    /**
     * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/discuss/754726
     */
    private static int countOddsOptimal(int low, int high) {
        return (high + 1) / 2 - (low / 2);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, count) -> {
            System.out.println("Low - " + input[0] + ", High - " + input[1] + ", Odd Numbers count - " + count);
        };
        //
        int low = 1, high = 2;
        int count = countOdds(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 1; high = 4;
        count = countOdds(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 2; high = 5;
        count = countOdds(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 2; high = 6;
        count = countOddsOptimal(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 3; high = 7;
        count = countOddsOptimal(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 8; high = 10;
        count = countOddsOptimal(low, high);
        logger.accept(new int[] {low, high}, count);
        //
        low = 1; high = 11;
        count = countOddsOptimal(low, high);
        logger.accept(new int[] {low, high}, count);
    }

}
