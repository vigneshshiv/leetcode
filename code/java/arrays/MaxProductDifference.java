package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class MaxProductDifference {

    private static int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, result) -> {
            System.out.println("Input - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {5, 6, 2, 7, 4};
        int result = maxProductDifference(nums.clone());
        logger.accept(nums, result);
        //
        nums = new int[] {4, 2, 5, 9, 7, 4, 8};
        result = maxProductDifference(nums.clone());
        logger.accept(nums, result);
    }

}
