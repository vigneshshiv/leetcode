package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf {

    private static int[] productExceptSelf(int[] nums) {
        int n = nums.length, prev = 1;
        int[] result = new int[n];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prev;
            prev *= nums[i];
        }
        prev = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= prev;
            prev *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {-1, 1, 0, -3, 3};
        result = productExceptSelf(nums);
        logger.accept(nums, result);
    }

}
