package code.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

    private static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;
        // Non-overlapping intervals before new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        // Overlapping and merge interval together
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i += 1;
        }
        result.add(newInterval);
        // Add the rest of interval points
        while (i < n) result.add(intervals[i++]);
        // Convert to array
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
                {1, 3}, {6, 9}
        };
        int[] newInterval = {2, 5};
        int[][] result = insert(intervals, newInterval);
        logger.accept(intervals, result);
        //
        intervals = new int[][] {
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        };
        newInterval = new int[] {4, 8};
        result = insert(intervals, newInterval);
        logger.accept(intervals, result);
    }

}
