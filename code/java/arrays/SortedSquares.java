package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class SortedSquares {

    private static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int start = 0, end = nums.length - 1, idx = end;
        while (start <= end) {
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                result[idx] = nums[start++];
            } else {
                result[idx] = nums[end--];
            }
            result[idx] *= result[idx--];
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/squares-of-a-sorted-array/discuss/221924
     * In place try, Not working for few edge cases, {-2, 0, 3}
     *
     * Working for - {-4,-1,0,3,10}, {-7,-3,2,3,11}, {-5,-3,-2,-1}, {-10000,-9999,-7,-5,0,0,10000}
     *
     */
    private static int[] sortedSquaresInPlace(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                swap(nums, start, end);
                end -= 1;
            } else {
                // Diff check
                if (nums[start] == 0 || nums[start] == nums[end] || Math.abs(nums[end]) - Math.abs(nums[start]) == 1) {
                    nums[start] *= nums[start];
                    start += 1;
                } else {
                    nums[end] *= nums[end];
                    end -= 1;
                }
            }
        }
        nums[start] *= nums[start];
        return nums;
    }

    private static void swap(int[] nums, int s, int e) {
        int temp = nums[s] * nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {-4,-1,0,3,10};
        int[] result = sortedSquares(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-7,-3,2,3,11};
        result = sortedSquares(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-5,-3,-2,-1};
        result = sortedSquares(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-10000,-9999,-7,-5,0,0,10000};
        result = sortedSquares(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-2, 0, 3};
        result = sortedSquares(nums);
        logger.accept(nums, result);
    }

}
