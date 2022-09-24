package code.java.searching;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 */
public class SumOfSquareNumbers {

    private static boolean judgeSquareSum(int c) {
        long x = 0, y = (long) Math.sqrt(c);
        while (x <= y) {
            long num = x * x + y * y;
            if (num == c) return true;
            if (num > c) y -= 1;
            else x += 1;
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Boolean> logger = (c, result) -> System.out.println("C - " + c + ", Result - " + result);
        //
        int c = 5;
        logger.accept(c, judgeSquareSum(c));
        //
        c = 4;
        logger.accept(c, judgeSquareSum(c));
        //
        c = 3;
        logger.accept(c, judgeSquareSum(c));
    }

}
