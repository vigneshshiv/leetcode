package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {

    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = 0;
        int n = nums.length, diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                if (sum < target) j++;
                else k--;
                int x = Math.abs(target - sum);
                if (x < diff) {
                    diff = x;
                    closest = sum;
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, result) -> {
            System.out.println("Nums - " + Arrays.toString(input[0]) + ", Target - " + input[1][0] + ", Result - " + result);
        };
        //
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        logger.accept(new int[][] {nums, new int[] {target}}, result);
        //
        nums = new int[] {0, 0, 0};
        target = 1;
        result = threeSumClosest(nums, target);
        logger.accept(new int[][] {nums, new int[] {target}}, result);
    }

}
