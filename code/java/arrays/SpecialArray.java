package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
 */
public class SpecialArray {

    private static int specialArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int n = nums.length - i;
            boolean x = n <= nums[i];
            boolean y = (i - 1 < 0) || (n > nums[i - 1]);
            if (x && y) return n;
        }
        return -1;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, result) -> System.out.println("Nums - " + Arrays.toString(input) + ", Result - " + result);
        //
        int[] nums = {3, 5};
        int result = specialArray(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 0};
        result = specialArray(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 4, 3, 0, 4};
        result = specialArray(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {3, 9, 7, 8, 3, 8, 6, 6};
        result = specialArray(nums);
        logger.accept(nums, result);
    }

}
