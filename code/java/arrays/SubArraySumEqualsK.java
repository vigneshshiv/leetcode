package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubArraySumEqualsK {

    private static int subarraySum(int[] nums, int k) {
        int sum = 0, count = 0;
        Map<Integer, Integer> table = new HashMap<>();
        table.put(0, 1); // Defaults to 1 for any sum - k equals to zero while iteration
        for (int num : nums) {
            sum += num;
            if (table.containsKey(sum - k)) {
                count += table.get(sum - k);
            }
            table.put(sum, table.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", K - " + Arrays.toString(input[1]) + ", Result - " + result);
        };
        //
        int[] nums = {1, 1, 1};
        int k = 2;
        int count = subarraySum(nums, k);
        logger.accept(new int[][]{nums, new int[] {k}}, count);
        //
        nums = new int[] {1, 2, 3}; k = 3;
        count = subarraySum(nums, k);
        logger.accept(new int[][]{nums, new int[] {k}}, count);
        //
        nums = new int[] {1, 2, 3, 4, 5}; k = 5;
        count = subarraySum(nums, k);
        logger.accept(new int[][]{nums, new int[] {k}}, count);
        //
        nums = new int[] {1, 2, 3, 4, 5, -1}; k = 4;
        count = subarraySum(nums, k);
        logger.accept(new int[][]{nums, new int[] {k}}, count);
    }

}
