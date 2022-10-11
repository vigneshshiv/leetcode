package code.java.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    private static void backtrack(int[] candidates, int target, List<List<Integer>> result, List<Integer> sets, int index) {
        if (target < 0 || index >= candidates.length) return;
        if (target == 0) {
            result.add(new ArrayList<>(sets));
            return;
        }
        sets.add(candidates[index]);
        backtrack(candidates, target - candidates[index], result, sets, index);
        sets.remove(sets.size() - 1);
        backtrack(candidates, target, result, sets, index + 1);
    }

    public static void main(String[] args) {
        BiConsumer<int[][], List<List<Integer>>> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", Target - " + Arrays.toString(input[1])
                    + ", Combinations Result - " + result);
        };
        //
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = combinationSum(candidates, target);
        logger.accept(new int[][] {candidates, new int[] {target}}, result);
        //
        candidates = new int[] {2, 3, 5};
        target = 8;
        result = combinationSum(candidates, target);
        logger.accept(new int[][] {candidates, new int[] {target}}, result);
        //
        candidates = new int[] {2};
        target = 1;
        result = combinationSum(candidates, target);
        logger.accept(new int[][] {candidates, new int[] {target}}, result);
    }

}
