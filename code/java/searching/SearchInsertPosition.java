package code.java.searching;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/search-insert-position/
 */
public class SearchInsertPosition {

    private static int searchInsert(int[] nums, int target) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, index) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", Target - " + Arrays.toString(input[1]) + ", Index - " + index);
        };
        //
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int index = searchInsert(nums, target);
        logger.accept(new int[][] {nums, new int[] {target}}, index);
        //
        nums = new int[] {1, 3, 5, 6}; target = 2;
        index = searchInsert(nums, target);
        logger.accept(new int[][] {nums, new int[] {target}}, index);
        //
        nums = new int[] {1, 3, 5, 6}; target = 7;
        index = searchInsert(nums, target);
        logger.accept(new int[][] {nums, new int[] {target}}, index);
    }

}
