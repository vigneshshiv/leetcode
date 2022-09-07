package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * https://leetcode.com/problems/maximum-product-subarray
 */
public class MaximumSubArray {

    /**
     * Causes TLE, Not an effective solution
     *
     * n - # of elements in array
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    private static int maxSubArraySum(int[] nums) {
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
    private static int maxSubArraySumOptimal(int[] nums) {
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
     * Length of the max subarray
     */
    private static int maxSubArraySumLength(int[] nums) {
        int curr = nums[0], global = nums[0], start = 0, end = 0;
        for (int i = 1; i < nums.length; i++) {
            curr += nums[i];
            if (curr > global) {
                global = curr;
                end = i;
            }
            if (curr < 0) {
                curr = 0;
                start = i + 1;
            }
        }
        return end - start + 1;
    }

    /**
     * Start and End index of max subarray
     */
    private static int[] maxSubArraySumIndices(int[] nums) {
        int curr = nums[0], global = nums[0], start = 0, end = 0;
        for (int i = 1; i < nums.length; i++) {
            curr += nums[i];
            if (curr > global) {
                global = curr;
                end = i;
            }
            if (curr < 0) {
                curr = 0;
                start = i + 1;
            }
        }
        return new int[] {start, end};
    }

    /**
     * Elements of the max subarray
     */
    private static int[] maxSubArraySumElements(int[] nums) {
        int curr = nums[0], global = nums[0], start = 0, end = 0;
        for (int i = 1; i < nums.length; i++) {
            curr += nums[i];
            if (curr > global) {
                global = curr;
                end = i;
            }
            if (curr < 0) {
                curr = 0;
                start = i + 1;
            }
        }
        return Arrays.copyOfRange(nums, start, end + 1);
    }

    /**
     * TODO:
     * Maximum Product of Sub Array
     */
    private static int maxSubArrayProductOptimal(int[] nums) {
        int curr = nums[0], global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(curr, curr * nums[i]);
            if (curr > global) {
                global = curr;
            }
        }
        return global;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, maxSum) -> System.out.println("Input - " + Arrays.toString(input) + ", MaxSum - " + maxSum);
        BiConsumer<int[], Integer> length_logger = (input, length) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Length - " + length);
        };
        BiConsumer<int[], int[]> indices_logger = (input, indices) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Indices - " + Arrays.toString(indices));
        };
        BiConsumer<int[], int[]> elements_logger = (input, elements) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Elements - " + Arrays.toString(elements));
        };
        BiConsumer<int[], Integer> product_logger = (input, maxProduct) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", MaxProduct - " + maxProduct);
        };
        //
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArraySumOptimal(nums);
        logger.accept(nums, maxSum);
        //
        nums = new int[] {-1};
        maxSum = maxSubArraySum(nums);
        logger.accept(nums, maxSum);
        //
        nums = new int[] {5, 4, -1, 7, 8};
        maxSum = maxSubArraySum(nums);
        logger.accept(nums, maxSum);
        //
        nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSum = maxSubArraySumLength(nums);
        length_logger.accept(nums, maxSum);
        //
        nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] indices = maxSubArraySumIndices(nums);
        indices_logger.accept(nums, indices);
        //
        nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] elements = maxSubArraySumElements(nums);
        elements_logger.accept(nums, elements);
        //
        nums = new int[] {-2, 3, -4};
        int maxProduct = maxSubArrayProductOptimal(nums);
        product_logger.accept(nums, maxProduct);
    }

}
