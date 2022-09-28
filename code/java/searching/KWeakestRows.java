package code.java.searching;

import code.java.utils.MethodsUtility;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 */
public class KWeakestRows {

    private static int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] != y[0] ? y[0]-x[0] : y[1]-x[1]);
        int[] result = new int[k];
        for (int r = 0; r < mat.length; r++) {
            pq.offer(new int[] {findOnesIdx(mat[r]), r});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (k-- > 0) {
            result[k] = pq.poll()[1];
        }
        return result;
    }

    private static int findOnesIdx(int[] row) {
        int low = 0, mid = 0, high = row.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int rows = 5, cols = 5;
        int[][] mat = {
                {1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}
        };
        int k = 3;
        MethodsUtility.printArray(mat, rows, cols);
        int[] result = kWeakestRows(mat, k);
        System.out.println("Result - " + Arrays.toString(result));
        System.out.println();
        //
        rows = 4; cols = 4;
        mat = new int[][] {
                {1,0,0,0}, {1,1,1,1}, {1,0,0,0}, {1,0,0,0}
        };
        k = 2;
        MethodsUtility.printArray(mat, rows, cols);
        result = kWeakestRows(mat, k);
        System.out.println("Result - " + Arrays.toString(result));
    }

}
