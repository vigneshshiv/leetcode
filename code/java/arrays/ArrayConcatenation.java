package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/concatenation-of-array/
 */
public class ArrayConcatenation {

    private static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[i + n] = nums[i];
        }
        return result;
    }

    private static int[] getConcatenationSystemCopy(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        System.arraycopy(nums, 0, result, 0, n);
        System.arraycopy(nums, 0, result, n, n);
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {1, 2, 1};
        int[] result = getConcatenation(nums);
        logger.accept(nums, result);
        result = getConcatenationSystemCopy(nums);
        logger.accept(nums, result);
        System.out.println();
        //
        nums = new int[] {1, 3, 2, 1};
        result = getConcatenation(nums);
        logger.accept(nums, result);
        result = getConcatenationSystemCopy(nums);
        logger.accept(nums, result);
    }

}
