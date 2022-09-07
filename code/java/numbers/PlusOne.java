package code.java.numbers;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/plus-one/
 */
public class PlusOne {

    private static int[] plusOne(int[] digits) {
        int last = digits[digits.length - 1] % 10;
        if (last < 9) {
            digits[digits.length - 1] = last + 1;
            return digits;
        }
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            digits[i] = (digits[i] + carry) % 10;
            carry = (digit + carry) / 10;
        }
        if (carry == 0) return digits;
        //
        int[] result = new int[digits.length + 1];
        result[0] = carry;
        return result;
    }

    /**
     * https://leetcode.com/problems/plus-one/discuss/24082
     */
    private static int[] plusOneEasy(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (digits, result) -> {
            System.out.println("Digits - " + Arrays.toString(digits) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] digits = {1, 2, 3};
        int[] result = plusOne(digits.clone());
        logger.accept(digits, result);
        //
        digits = new int[] {4, 3, 2, 1};
        result = plusOne(digits.clone());
        logger.accept(digits, result);
        //
        digits = new int[] {9};
        result = plusOne(digits.clone());
        logger.accept(digits, result);
        //
        digits = new int[] {1, 9, 9};
        result = plusOne(digits.clone());
        logger.accept(digits, result);
        //
        digits = new int[] {9, 9, 9, 9};
        result = plusOneEasy(digits.clone());
        logger.accept(digits, result);
        //
        digits = new int[] {9, 8, 9};
        result = plusOneEasy(digits.clone());
        logger.accept(digits, result);
    }

}
