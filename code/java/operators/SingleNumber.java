package code.java.operators;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

    private static int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        return x;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, result) -> System.out.println("Input - " + Arrays.toString(nums) + ", Result - " + result);
        //
        int[] nums = {2, 2, 1};
        int result = singleNumber(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {4, 1, 2, 1, 2};
        result = singleNumber(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {1};
        result = singleNumber(nums);
        logger.accept(nums, result);
    }

}
