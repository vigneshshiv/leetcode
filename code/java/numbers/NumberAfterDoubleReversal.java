package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/a-number-after-a-double-reversal/
 */
public class NumberAfterDoubleReversal {

    private static boolean isSameAfterReversals(int num) {
        if (num == 0) return true;
        if (num % 10 == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Boolean> logger = (num, result) -> {
            System.out.println("Num - " + num + ", Double Reversal Same Number - " + result);
        };
        //
        int num = 526;
        boolean result = isSameAfterReversals(num);
        logger.accept(num, result);
        //
        num = 1800; result = isSameAfterReversals(num);
        logger.accept(num, result);
        //
        num = 0; result = isSameAfterReversals(num);
        logger.accept(num, result);
        //
        num = 1223; result = isSameAfterReversals(num);
        logger.accept(num, result);
        //
        num = 13497; result = isSameAfterReversals(num);
        logger.accept(num, result);
        //
        num = 122367; result = isSameAfterReversals(num);
        logger.accept(num, result);
    }

}
