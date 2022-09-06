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

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {0, 2, 1, 5, 3, 4};
        int[] result = buildArray(nums);
        logger.accept(nums, result);
        System.out.println();
        //
        nums = new int[] {5, 0, 1, 2, 3, 4};
        result = buildArray(nums);
        logger.accept(nums, result);
    }

}
