package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {

    private static int removeDuplicates(int[] nums) {
        int idx = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[idx]) {
                nums[++idx] = nums[j];
            }
        }
        return idx + 1;
    }

    private static int removeDuplicatesOptimal(int[] nums) {
        int idx = 0;
        for (int n : nums) {
            if (idx == 0 || n > nums[idx - 1]) {
                nums[idx++] = n;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[][]> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + Arrays.toString(result[0])
                    + ", Shifted - " + Arrays.toString(result[1]));
        };
        //
        int[] nums = {1, 1, 2};
        int[] input = nums.clone();
        int k = removeDuplicates(nums);
        logger.accept(input, new int[][] {nums, new int[] {k}});
        //
        nums = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        input = nums.clone();
        k = removeDuplicatesOptimal(nums);
        logger.accept(input, new int[][] {nums, new int[] {k}});
    }

}
