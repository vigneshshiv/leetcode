package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubArray {

    /**
     * Causes TLE, Not an effective solution
     *
     * n - # of elements in array
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    private static int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        for (int idx = 0; idx < nums.length; idx++) {
            sum = Math.max(sum, subArraySum(idx, 0, nums));
        }
        return sum;
    }

    private static int subArraySum(int idx, int sum, int[] nums) {
        if (idx == nums.length) return sum;
        sum += nums[idx];
        int total = subArraySum(idx + 1, sum, nums);
        return Math.max(total, sum);
    }

    /**
     * Optimal solution is Kadane's algorithm
     * Reference - https://www.youtube.com/watch?v=86CQq3pKSUw
     *
     * n - # of elements in array
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    private static int maxSubArrayOptimal(int[] nums) {
        int curr = nums[0], global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(curr + nums[i], nums[i]);
            if (curr > global) {
                global = curr;
            }
        }
        return global;
    }

    /**
     * TODO
     * 1. Length of the max subarray
     * 2. Elements of the max subarray
     * 3. Start and End index of max subarray
     */



    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, maxSum) -> System.out.println("Input - " + Arrays.toString(input) + ", MaxSum - " + maxSum);
        //
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArrayOptimal(nums);
        logger.accept(nums, maxSum);
        //
        nums = new int[] {-1};
        maxSum = maxSubArray(nums);
        logger.accept(nums, maxSum);
        //
        nums = new int[] {5, 4, -1, 7, 8};
        maxSum = maxSubArray(nums);
        logger.accept(nums, maxSum);
    }

}
