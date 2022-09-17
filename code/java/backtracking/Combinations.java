package code.java.backtracking;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/combinations/
 */
public class Combinations {

    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private static void combine(List<List<Integer>> result, List<Integer> sets, int start, int n, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(sets));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            sets.add(i);
            combine(result, sets, i + 1, n, k - 1);
            sets.remove(sets.size() - 1);
        }
    }

    public static void main(String[] args) {
        BiConsumer<int[], List<List<Integer>>> logger = (input, result) -> {
            System.out.println("Input N - " + input[0] + ", K - " + input[1] + ", Result - " + result);
        };
        //
        int n = 4, k = 2;
        List<List<Integer>> result = combine(n, k);
        logger.accept(new int[] {n, k}, result);
        //
        n = 1; k = 1;
        result = combine(n, k);
        logger.accept(new int[] {n, k}, result);
        //
        n = 2; k = 1;
        result = combine(n, k);
        logger.accept(new int[] {n, k}, result);
    }

}
