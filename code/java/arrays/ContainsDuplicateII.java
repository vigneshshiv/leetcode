package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 */
public class ContainsDuplicateII {

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Boolean> logger = (input, result) -> {
            System.out.println("Nums - " + Arrays.toString(input[0]) + ", K - " + input[1][0] + ", Result - " + result);
        };
        //
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean result = containsNearbyDuplicate(nums, k);
        logger.accept(new int[][] {nums, new int[] {k}}, result);
        //
        nums = new int[] {1, 0, 1, 1};
        k = 1;
        result = containsNearbyDuplicate(nums, k);
        logger.accept(new int[][] {nums, new int[] {k}}, result);
        //
        nums = new int[] {1, 2, 3, 1, 2, 3};
        k = 2;
        result = containsNearbyDuplicate(nums, k);
        logger.accept(new int[][] {nums, new int[] {k}}, result);
    }

}
