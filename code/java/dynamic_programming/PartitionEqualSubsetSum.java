package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {

    private static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // Odd sum, cannot form the partition
        if ((sum & 1) == 1) return false;
        // Half partition sum
        sum /= 2;
        //
        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        // Base case [0] - true
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i > 0 && i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
                // If once dp[sum] is true then we can return
                if (dp[sum]) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + result);
        };
        //
        int[] nums = {1, 2, 3};
        boolean result = canPartition(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {1, 2, 5};
        result = canPartition(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {1, 5, 11, 5};
        result = canPartition(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {1, 2, 3, 5};
        result = canPartition(nums);
        logger.accept(nums, result);
    }

}
