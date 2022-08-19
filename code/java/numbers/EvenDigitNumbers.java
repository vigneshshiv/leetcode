package code.java.numbers;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 */
public class EvenDigitNumbers {

    /**
     * Time complexity: O(n log(n))
     * Space complexity: O(1)
     */
    private static int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (digits(num) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    private static int digits(int num) {
        if (num == 0) return 1;
        if (num < 0) num *= -1;
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int findNumbersOptimal(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num < 0) {
                num *= -1;
            }
            int digit = (int) Math.log10(num) + 1;
            if ((digit & 1) == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, result) -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + result);
        //
        int[] nums = {12, 345, 2, 6, 7896};
        int result = findNumbers(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {12, 345, 2, 6, 7896};
        result = findNumbersOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {555, 901, 482, 1771};
        result = findNumbers(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {555, 901, 482, 1771};
        result = findNumbersOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 1, 10};
        result = findNumbers(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 1, 10};
        result = findNumbersOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-7, -29, 9, 0, 55};
        result = findNumbers(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-7, -29, 9, 0, 55};
        result = findNumbersOptimal(nums);
        logger.accept(nums, result);
    }

}
