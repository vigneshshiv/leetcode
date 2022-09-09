package code.java.arrays;

import code.java.sorting.Sort;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeros {

    /**
     * Easy approach
     */
    private static void moveZeroesEasy(int[] nums) {
        if (nums.length == 1) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                Sort.swap(nums, i, j);
                j++;
            }
        }
    }

    /**
     * In Place move zero's
     */
    private static void moveZeroes(int[] nums) {
        if (nums.length == 1) return;
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) {
            nums[idx++] = 0;
        }
    }

    /**
     * Snow ball method - https://leetcode.com/problems/move-zeroes/discuss/172432
     */
    private static void moveZerosOptimal(int[] nums) {
        if (nums.length == 1) return;
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                idx += 1;
            } else if (idx > 0) {
                nums[i - idx] = nums[i];
                nums[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {0, 1, 0, 3, 12};
        int[] input = nums.clone();
        moveZeroesEasy(nums);
        logger.accept(input, nums);
        //
        nums = new int[] {0};
        input = nums.clone();
        moveZeroes(nums);
        logger.accept(input, nums);
        //
        nums = new int[] {2, 1};
        input = nums.clone();
        moveZeroes(nums);
        logger.accept(input, nums);
        //
        nums = new int[] {1, 0, 1};
        input = nums.clone();
        moveZeroes(nums);
        logger.accept(input, nums);
    }

}
