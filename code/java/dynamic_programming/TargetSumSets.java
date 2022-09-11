package code.java.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * TODO:
 * Find Target Sum sets
 */
public class TargetSumSets {

    private static Supplier<List<Integer>> emptyList = () -> new ArrayList<>();
    private static Supplier<List<List<Integer>>> empty2DList = () -> {
        List<List<Integer>> sets = new ArrayList<>();
        sets.add(emptyList.get());
        return sets;
    };

    private static BiFunction<List<List<Integer>>, Integer, List<List<Integer>>> combiner = (remainderResult, number) -> {
        List<List<Integer>> result = empty2DList.get();
        result.addAll(remainderResult.stream().map(ArrayList::new).collect(Collectors.toList()));
        result.add(Arrays.asList(number));
        return result;
    };

    private static List<List<Integer>> findTargetSumSets(int[] nums, int target) {
        return targetSumSets(nums, target, nums.length - 1);
    }

    private static List<List<Integer>> targetSumSets(int[] nums, int target, int index) {
        if (target == 0) return empty2DList.get();
        if (target < 0) return null;
        List<List<Integer>> result = null;
        for (int i = index; i >= 0; i--) {
            var remainder = target - nums[i];
            result = targetSumSets(nums, remainder, index - 1);
            if (Objects.nonNull(result)) {
                combiner.apply(result, nums[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 10};
        int target = 6;
        List<List<Integer>> sets = findTargetSumSets(nums, target);
        System.out.println(sets);
    }

}
