package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {

    private static int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        int result = nums[0];
        int min = 1, max = 1;
        for (int num : nums) {
            int curr = max * num;
            max = Math.max(num, Math.max(curr, min * num));
            min = Math.min(num, Math.min(curr, min * num));
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {2, 3, -2, 4};
        int result = maxProduct(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-2, 0, -1};
        result = maxProduct(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-1, -2, -3, -4};
        result = maxProduct(nums);
        logger.accept(nums, result);
    }

}
