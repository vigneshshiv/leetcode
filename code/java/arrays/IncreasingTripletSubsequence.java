package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int x : nums) {
            // update the first smallest number
            if (x <= first) first = x;
            // update the second smallest number
            else if (x <= second) second = x;
            // if x > first && x > second, then found the answer
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {1, 2, 3, 4, 5};
        boolean result = increasingTriplet(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {5, 4, 3, 2, 1};
        result = increasingTriplet(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {2, 1, 5, 0, 4, 6};
        result = increasingTriplet(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {20, 100, 10, 12, 5, 13};
        result = increasingTriplet(nums);
        logger.accept(nums, result);
    }

}
