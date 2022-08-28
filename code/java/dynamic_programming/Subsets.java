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
        List<List<Integer>> subsets = empty2DListInt.get();
        for (int num : numbers) {
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> item = new ArrayList<>(subsets.get(i));
                item.add(num);
                subsets.add(item);
            }
        }
        return subsets;
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
        // Iterative
        subsetsOfInt = getSubsetsOfInt(numbers);
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
