package code.java.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/subsets/
 *
 * https://leetcode.com/problems/subsets-ii/
 */
public class Subsets {

    private static Supplier<List<Integer>> emptyListInt = () -> new ArrayList<>();
    private static Supplier<List<List<Integer>>> empty2DListInt = () -> {
        List<List<Integer>> sets = new ArrayList<>();
        sets.add(emptyListInt.get());
        return sets;
    };

    private static BiFunction<List<List<Integer>>, Integer, List<List<Integer>>> combinerInt = (subsets, item) -> {
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(subsets.stream().map(ArrayList::new).collect(Collectors.toList()));
        result.forEach(set -> set.add(0, item));
        return result;
    };

    private static Supplier<List<String>> emptyListString = () -> new ArrayList<>();

    private static List<List<Integer>> getSubsetsOfInt(int[] numbers, int index) {
        if (numbers.length == index) {
            return empty2DListInt.get();
        }
        List<List<Integer>> result = getSubsetsOfInt(numbers, index + 1);
        result.addAll(combinerInt.apply(result, numbers[index]));
        return result;
    }

    /**
     * Iterative solution
     *
     * n - # of subsets
     *
     * Time complexity: O(n * 2^n)
     * Space complexity: O(n * 2^n), where n is space taken by each subset, 2^n is the total subset
     */
    private static List<List<Integer>> getSubsetsOfInt(int[] numbers) {
        // Input: nums - [1, 2]
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> sets = new ArrayList<>();
        // Initial - { [] }
        subsets.add(sets);
        for (int num : numbers) {
            // num - 1 & n is 1 ( subsets - { [] } )
            // num - 2 & n is 2 ( subsets - { [], [1] } )
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                // num - 1 & i = 0, []
                // num - 2 & i = 0, []
                // num - 2 & i = 1, [1]
                List<Integer> item = new ArrayList<>(subsets.get(i));
                item.add(num);
                // num - 1 & i = 0, { [], [1] }
                // num - 2 & i = 0, { [], [1], [2] }
                // num - 2 & i = 1, { [], [1], [2], [1, 2] } -- Final Result
                subsets.add(item);
            }
        }
        return subsets;
    }

    /**
     * https://leetcode.com/problems/permutations/discuss/18239
     */
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsetsBacktrack(subsets, new ArrayList<>(), nums, 0);
        return subsets;
    }

    private static void subsetsBacktrack(List<List<Integer>> subsets, List<Integer> sets, int[] nums, int idx) {
        // index 0, [] added to result { [] }
        // index 1, { [], [1] }
        // index 2, { [], [1], [1, 2] } - index 2 is out of range, so it won't enter into loop
        subsets.add(new ArrayList<>(sets));
        for (int i = idx; i < nums.length; i++) {
            // index 0, sets add [1]
            // index 1, sets add, [1, 2]
            /*
             * index 1, (actually index 0 call stack, for loop can begin), so it's add [2] in sets
             * So, it calls subsets again and it to result set
             * Finally, result set is like - { [], [1], [1, 2], [2] }
             */
            sets.add(nums[i]);
            // index 0 - calling subsets - { [] }, sets - [1], index - 1
            // index 1 - calling subsets - { [], [1] }, sets - [1, 2], index 2
            subsetsBacktrack(subsets, sets, nums, i + 1);
            // index 1, sets removed it's last element 2, so sets [1], for loops end with 1
            // index 0, sets removed it's last element 1, so sets [], it can continue with index 1
            sets.remove(sets.size() - 1);
        }
    }

    /**
     * Subsets Duplicates
     */
    private static List<List<Integer>> getSubsetsOfIntDuplicates(int[] numbers) {
        // Sort the Array
        Arrays.sort(numbers);
        List<List<Integer>> subsets = empty2DListInt.get();
        int start = 0, end = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                start = end + 1;
            } else {
                start = 0;
            }
            end = subsets.size() - 1;
            int n = subsets.size();
            for (int j = start; j < n; j++) {
                List<Integer> item = new ArrayList<>(subsets.get(j));
                item.add(numbers[i]);
                subsets.add(item);
            }
        }
        return subsets;
    }

    private static List<String> getSubsetsOfString(String str, String prefix) {
        if (str.isEmpty()) {
            return emptyListString.get();
        }
        List<String> left = getSubsetsOfString(str.substring(1), prefix + str.charAt(0));
        left.add(prefix + str.charAt(0));
        List<String> right = getSubsetsOfString(str.substring(1), prefix);
        left.addAll(right);
        return left;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        List<List<Integer>> subsetsOfInt = getSubsetsOfInt(numbers, 0);
        System.out.println("Recursive - " + subsetsOfInt.toString());
        //
        subsetsOfInt = subsets(numbers);
        System.out.println("BackTrack - " + subsetsOfInt.toString());
        // Iterative
        int[] nums = {1, 2};
        subsetsOfInt = getSubsetsOfInt(nums);
        System.out.println("Iterative - " + subsetsOfInt.toString());
        // Duplicates
        numbers = new int[] {1, 2, 2};
        subsetsOfInt = getSubsetsOfIntDuplicates(numbers);
        System.out.println("Duplicates - " + subsetsOfInt.toString());
        //
        numbers = new int[] {1, 1, 2, 2};
        subsetsOfInt = getSubsetsOfIntDuplicates(numbers);
        System.out.println("Duplicates - " + subsetsOfInt.toString());
        //
        numbers = new int[] {4, 1, 4, 4, 4};
        subsetsOfInt = getSubsetsOfIntDuplicates(numbers);
        System.out.println("Duplicates - " + subsetsOfInt.toString());
        //
        String str = "abc";
        List<String> subsetsOfString = getSubsetsOfString(str, "");
        System.out.println(subsetsOfString);
    }

}
