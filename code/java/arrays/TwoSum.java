package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int[] findTwoSumIndices(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (table.containsKey(nums[i])) {
                return new int[] { table.get(nums[i]), i };
            }
            num = target - nums[i];
            table.put(num, i);
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findTwoSumIndices(nums, target);
        System.out.println("Input - " + Arrays.toString(nums) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        nums = new int[] {3, 2, 4};
        target = 6;
        result = findTwoSumIndices(nums, target);
        System.out.println("Input - " + Arrays.toString(nums) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        nums = new int[] {3, 3};
        target = 6;
        result = findTwoSumIndices(nums, target);
        System.out.println("Input - " + Arrays.toString(nums) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        System.out.println("27 binary - " + Integer.toBinaryString(27));
        System.out.println("25 binary - " + Integer.toBinaryString(25));
        System.out.println(25 ^ 23);
    }

}
