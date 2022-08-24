package code.java.greedy;

import code.java.utils.MethodsUtility;

import java.util.Arrays;

public class DisjointIntervals {

    /**
     * Ends early approach
     */
    private static int findIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prev_start = intervals[0][0];
        int prev_end = intervals[0][1];
        int count = 1;
        for (int[] interval : intervals) {
            int curr_start = interval[0];
            int curr_end = interval[1];
            if (curr_start <= prev_end) {
                continue;
            } else {
                prev_start = curr_start;
                prev_end = curr_end;
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 2}, {2, 10}, {4, 6}
        };
        MethodsUtility.printArray(intervals, 3, 2);
        System.out.println("Non overlapping intervals count - " + findIntervals(intervals));
        System.out.println();
        //
        intervals = new int[][] {
                {1, 4}, {2, 3}, {4, 6}, {8, 9}
        };
        MethodsUtility.printArray(intervals, 4, 2);
        System.out.println("Non overlapping intervals count - " + findIntervals(intervals));
    }

}
