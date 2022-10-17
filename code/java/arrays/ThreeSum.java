package code.java.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

    private static List<List<Integer>> threeSum(int[] nums) {
        // Sort the array
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum > 0) {
                    R -= 1;
                } else if (sum < 0) {
                    L += 1;
                } else {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    L += 1;
                    while (nums[L] == nums[L - 1] && L < R) {
                        L += 1;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], List<List<Integer>>> logger = (nums, result) -> {
            System.out.println("Input - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 1, 1};
        result = threeSum(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 0, 0};
        result = threeSum(nums);
        logger.accept(nums, result);
    }

}
