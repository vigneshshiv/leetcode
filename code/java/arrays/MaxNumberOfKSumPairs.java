package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 */
public class MaxNumberOfKSumPairs {

    public static int maxOperations(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j < nums.length) {
                if (nums[i] != 0 && nums[j] != 0 && k - nums[j] == nums[i]) {
                    count += 1;
                    nums[i] = 0;
                    nums[j] = 0;
                    break;
                }
                j += 1;
            }
        }
        return count;
    }
    
    public static int maxOperationsOptimal(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> table = new HashMap<>();
        for (int x : nums) {
            int reminder = k - x;
            if (table.containsKey(reminder)) {
                count += 1;
                if (table.get(reminder) == 1) {
                    table.remove(reminder);
                } else {
                    table.merge(reminder, -1, Integer::sum);
                }
            } else {
                table.merge(x, 1, Integer::sum);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (nums, count) -> {
            System.out.println("Nums - " + Arrays.toString(nums[0]) + ", K - " + Arrays.toString(nums[1]) + ", Count - " + count);
        };
        //
        int[] nums = {1, 2, 3, 4};
        int k = 5;
        int count = maxOperations(nums.clone(), k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
        //
        nums = new int[] {3, 1, 3, 4, 3}; k = 6;
        count = maxOperations(nums.clone(), k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
        //
        nums = new int[] {4, 4, 1, 3, 1, 3, 2, 2, 5, 5, 1, 5, 2, 1, 2, 3, 5, 4}; k = 2;
        count = maxOperations(nums.clone(), k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
        //
        nums = new int[] {2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2}; k = 3;
        count = maxOperationsOptimal(nums.clone(), k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
    }
}
