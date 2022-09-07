package code.java.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {

    private static boolean containsDuplicate(int[] nums) {
        Set<Integer> table = new HashSet<>();
        for (int num : nums) {
            if (!table.add(num)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (input, hasDuplicate) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Has Duplicate - " + hasDuplicate);
        };
        //
        int[] nums = {1, 2, 3, 1};
        boolean hasDuplicate = containsDuplicate(nums);
        logger.accept(nums, hasDuplicate);
        //
        nums = new int[] {1, 2, 3, 4};
        hasDuplicate = containsDuplicate(nums);
        logger.accept(nums, hasDuplicate);
        //
        nums = new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        hasDuplicate = containsDuplicate(nums);
        logger.accept(nums, hasDuplicate);
    }

}
