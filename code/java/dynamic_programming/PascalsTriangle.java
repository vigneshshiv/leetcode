package code.java.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalsTriangle {

    private static Supplier<List<Integer>> emptyList = () -> new ArrayList<>();
    private static Supplier<List<List<Integer>>> empty2DList = () -> {
        List<List<Integer>> sets = new ArrayList<>();
        return sets;
    };

    /**
     * Bottom-up approach
     */
    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = empty2DList.get();
        List<Integer> sets = emptyList.get();
        for (int i = 0; i < numRows; i++) {
            for (int j = sets.size() - 1; j >= 1; j--) {
                sets.set(j, sets.get(j) + sets.get(j - 1));
            }
            sets.add(1); // Last
            result.add(new ArrayList<>(sets));
        }
        return result;
    }

    private static BiFunction<List<List<Integer>>, Integer, List<Integer>> combiner = (sets, numRows) -> {
        List<Integer> result = emptyList.get();
        if (!sets.isEmpty()) {
            result.addAll(sets.get(numRows - 1).stream().collect(Collectors.toList())); // Previous Index
            for (int idx = result.size() - 1; idx >= 1; idx--) {
                result.set(idx, result.get(idx) + result.get(idx - 1));
            }
        }
        result.add(1); // Last
        return result;
    };

    private static List<List<Integer>> generateRecursive(int numRows) {
        if (numRows == 0) {
            return empty2DList.get();
        }
        List<List<Integer>> result = generateRecursive(numRows - 1);
        result.add(combiner.apply(result, numRows - 1));
        return result;
    }

    public static void main(String[] args) {
        int numRows = 4;
        List<List<Integer>> result = generate(numRows);
        System.out.println(result);
        // Recursive approach
        result = generateRecursive(numRows);
        System.out.println(result);
    }

}
