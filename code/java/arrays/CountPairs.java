package code.java.arrays;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
 */
public class CountPairs {

    private static int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indices.computeIfAbsent(nums[i], list -> new ArrayList<>()).add(i);
        }
        int count = 0;
        for (List<Integer> index : indices.values()) {
            for (int i = 0; i < index.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (index.get(i) * index.get(j) % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, count) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", K - " + Arrays.toString(input[1]) + ", Count - " + count);
        };
        //
        int[] nums = {3, 1, 2, 2, 2, 1, 3};
        int k = 2;
        int count = countPairs(nums, k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
        //
        nums = new int[] {1, 2, 3, 4}; k = 1;
        count = countPairs(nums, k);
        logger.accept(new int[][] {nums, new int[] {k}}, count);
    }

}
