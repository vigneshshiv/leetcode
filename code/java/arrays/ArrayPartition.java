package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/array-partition/
 */
public class ArrayPartition {

    private static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, sum) -> System.out.println("Input - " + Arrays.toString(input) + ", Sum - " + sum);
        //
        int[] nums = {1, 4, 3, 2};
        int sum = arrayPairSum(nums.clone());
        logger.accept(nums, sum);
        //
        nums = new int[] {6, 2, 6, 5, 1, 2};
        sum = arrayPairSum(nums.clone());
        logger.accept(nums, sum);
    }

}
