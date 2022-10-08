package code.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

    private static int[][] merge(int[][] intervals) {
        // Sort with interval start
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        List<int[]> result = new ArrayList<>();
        int[] prev = null;
        for (int[] interval : intervals) {
            // If previous interval is null OR
            // previous end is not overlapping with current interval starting then add it to the result
            if (prev == null || prev[1] < interval[0]) {
                result.add(interval);
                prev = interval;
            } else if (prev[1] < interval[1]) {
                // Previous interval end is lies between current interval end, so modify the end
                prev[1] = interval[1];
            }
        }
        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String[] args) {
        BiConsumer<int[][], int[][]> logger = (input, result) -> {
            System.out.println("Intervals - " + Arrays.deepToString(input));
            System.out.println("Merged Intervals - " + Arrays.deepToString(result));
            System.out.println();
        };
        //
        int[][] intervals = {
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        int[][] result = merge(intervals);
        logger.accept(intervals, result);
        //
        intervals = new int[][] {
                {1, 4}, {4, 5}
        };
        result = merge(intervals);
        logger.accept(intervals, result);
    }

}
