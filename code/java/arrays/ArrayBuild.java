package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/build-array-from-permutation/
 */
public class ArrayBuild {

    private static int[] buildArray(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[nums[i]];
        }
        return result;
    }

    /**
     * Follow-up: Can you solve it without using an extra space (i.e., O(1) memory)?
     */
    private static int[] buildArrayInPlace(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] + n * (nums[nums[i]] % n);
        }
        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
        return nums;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {0, 2, 1, 5, 3, 4};
        int[] result = buildArrayInPlace(nums.clone());
        logger.accept(nums, result);
        System.out.println();
        //
        nums = new int[] {5, 0, 1, 2, 3, 4};
        result = buildArray(nums);
        logger.accept(nums, result);
    }

}
