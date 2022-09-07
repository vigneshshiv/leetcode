package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-pivot-index/
 *
 * https://leetcode.com/problems/find-the-middle-index-in-array/
 */
public class PivotIndex {

    private static int pivotIndex(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        for (int idx = 0; idx < nums.length; idx++) {
            int leftSum = idx == 0 ? 0 : nums[idx - 1];
            int rightSum = idx == nums.length - 1 ? 0 : nums[nums.length - 1] - nums[idx];
            if (leftSum == rightSum) return idx;
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/find-pivot-index/discuss/109274
     */
    private static int pivotIndexOptimal(int[] nums) {
        int total = 0, leftSum = 0;
        for (int num : nums) {
            total += num;
        }
        for (int i = 0; i < nums.length; leftSum += nums[i++]) {
            if (2 * leftSum == total - nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, idx) -> System.out.println("Input - " + Arrays.toString(nums) + ", Idx - " + idx);
        //
        int[] nums = {1, 7, 3, 6, 5, 6};
        int idx = pivotIndex(nums.clone());
        logger.accept(nums, idx);
        //
        nums = new int[] {1, 2, 3};
        idx = pivotIndex(nums.clone());
        logger.accept(nums, idx);
        //
        nums = new int[] {2, 1, -1};
        idx = pivotIndex(nums.clone());
        logger.accept(nums, idx);
        //
        nums = new int[] {2, 3, -1, 8, 4};
        idx = pivotIndexOptimal(nums.clone());
        logger.accept(nums, idx);
        //
        nums = new int[] {1, -1, 4};
        idx = pivotIndexOptimal(nums.clone());
        logger.accept(nums, idx);
        //
        nums = new int[] {2, 5};
        idx = pivotIndexOptimal(nums.clone());
        logger.accept(nums, idx);
    }

}
