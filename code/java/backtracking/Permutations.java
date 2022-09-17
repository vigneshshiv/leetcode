package code.java.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutations(nums, result, new ArrayList<>());
        return result;
    }

    private static void permutations(int[] nums, List<List<Integer>> result, List<Integer> sets) {
        if (sets.size() == nums.length) {
            result.add(new ArrayList<>(sets));
        }
        for (int i = 0; i < nums.length; i++) {
            if (sets.contains(nums[i])) continue;
            sets.add(nums[i]);
            permutations(nums, result, sets);
            sets.remove(sets.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
        //
        nums = new int[] {1, 2, 3};
        result = permute(nums);
        System.out.println(result);
        //
        nums = new int[] {0, 1};
        result = permute(nums);
        System.out.println(result);
        //
        nums = new int[] {1};
        result = permute(nums);
        System.out.println(result);
    }

}
