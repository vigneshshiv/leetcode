package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/running-sum-of-1d-array/
 */
public class RunningSum {

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result[i] = sum;
        }
        return result;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int[] runningSumOptimal(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {1, 2, 3, 4};
        int[] input = nums.clone();
        int[] result = runningSum(nums);
        logger.accept(nums, result);
        runningSumOptimal(nums);
        logger.accept(input, nums);
        //
        nums = new int[] {1, 1, 1, 1, 1};
        input = nums.clone();
        result = runningSum(nums);
        logger.accept(nums, result);
        runningSumOptimal(nums);
        logger.accept(input, nums);
        //
        nums = new int[] {3, 1, 2, 10, 1};
        input = nums.clone();
        result = runningSum(nums);
        logger.accept(nums, result);
        runningSumOptimal(nums);
        logger.accept(input, nums);
    }

}
