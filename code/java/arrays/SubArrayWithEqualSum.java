package code.java.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-subarrays-with-equal-sum/
 */
public class SubArrayWithEqualSum {

    private static boolean findSubarrays(int[] nums) {
        Set<Integer> store = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            store.add(nums[i] + nums[i + 1]);
        }
        return store.size() != nums.length - 1;
    }

    private static boolean findSubarraysOptimal(int[] nums) {
        Set<Integer> store = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (!store.add(nums[i] + nums[i + 1])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (nums, result) -> System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        //
        int[] nums = {4, 2, 4};
        boolean result = findSubarrays(nums);
        logger.accept(nums, result);
        result = findSubarraysOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {1, 2, 3, 4, 5};
        result = findSubarrays(nums);
        logger.accept(nums, result);
        result = findSubarraysOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 0, 0};
        result = findSubarrays(nums);
        logger.accept(nums, result);
        result = findSubarraysOptimal(nums);
        logger.accept(nums, result);
    }

}
