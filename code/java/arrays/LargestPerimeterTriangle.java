package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/largest-perimeter-triangle
 */
public class LargestPerimeterTriangle {

    /**
     * 1. Sort Nums
     * 2. Try to get triangle with 3 biggest numbers
     * 3. nums[i] < nums[i - 1] + nums[i - 2] forms a triangle, if not repeat the process
     */
    private static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, perimeter) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Perimeter - " + perimeter);
        };
        //
        int[] nums = {2, 1, 2};
        int perimeter = largestPerimeter(nums);
        logger.accept(nums, perimeter);
        //
        nums = new int[] {1, 2, 1};
        perimeter = largestPerimeter(nums);
        logger.accept(nums, perimeter);
        //
        nums = new int[] {1, 2, 3, 5, 4, 1, 8, 5, 0};
        perimeter = largestPerimeter(nums);
        logger.accept(nums, perimeter);
        //
        nums = new int[] {1, 4, 3, 5, 7, 1, 8, 10, 1};
        perimeter = largestPerimeter(nums);
        logger.accept(nums, perimeter);
        //
        nums = new int[] {3, 2, 3, 4};
        perimeter = largestPerimeter(nums);
        logger.accept(nums, perimeter);
    }

}
