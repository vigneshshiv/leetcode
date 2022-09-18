package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    /**
     * Recurrence Relation: rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )
     */
    private static int robRecursive(int[] nums) {
        int[] memo = new int[nums.length];
        return rob(nums, memo, nums.length - 1);
    }

    private static int rob(int[] nums, int[] memo, int idx) {
        if (idx < 0) return 0;
        if (memo[idx] > 0) return memo[idx];
        int result = Math.max(rob(nums, memo, idx - 2) + nums[idx], rob(nums, memo, idx - 1));
        return memo[idx] = result;
    }

    private static int robIterative(int[] nums) {
        int[] table = new int[nums.length + 1];
        table[0] = 0; table[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            table[i + 1] = Math.max(table[i], table[i - 1] + nums[i]);
        }
        return table[nums.length];
    }

    private static int robOptimal(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int prev = a;
            a = Math.max(a, b + num);
            b = prev;
        }
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {1, 2, 3, 1};
        int result = robRecursive(nums);
        logger.accept(nums, result);
        result = robIterative(nums);
        logger.accept(nums, result);
        result = robOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {2, 7, 9, 3, 1};
        result = robRecursive(nums);
        logger.accept(nums, result);
        result = robIterative(nums);
        logger.accept(nums, result);
        result = robOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {2, 1, 1, 2};
        result = robRecursive(nums);
        logger.accept(nums, result);
        result = robIterative(nums);
        logger.accept(nums, result);
        result = robOptimal(nums);
        logger.accept(nums, result);
    }

}
