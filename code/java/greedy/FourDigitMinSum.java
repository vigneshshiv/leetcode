package code.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/
 */
public class FourDigitMinSum {

    private static int minimumSum(int num) {
        int[] digits = getDigits(num);
        Arrays.sort(digits);
        int num1 = digits[0] * 10 + digits[2];
        int num2 = digits[1] * 10 + digits[3];
        return num1 + num2;
    }

    private static int[] getDigits(int num) {
        int[] digits = new int[4];
        int idx = 0;
        while (num > 0) {
            digits[idx++] = num % 10;
            num /= 10;
        }
        return digits;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (input, sum) -> System.out.println("Num - " + input + ", Sum - " + sum);
        //
        int num = 2932;
        int sum = minimumSum(num);
        logger.accept(num, sum);
        //
        num = 4009;
        sum = 13;
        logger.accept(num, sum);
    }

}
