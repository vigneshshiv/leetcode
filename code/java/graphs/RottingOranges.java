package code.java.graphs;

import code.java.utils.MethodsUtility;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

    private static final int NO_DIRS = 4;
    private static final int[] DIRS = {0, 1, 0, -1, 0};

    private static int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int minutes = 0, fresh = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        // Put the position of all rotten oranges in queue
        // Count the number of fresh oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[] {r, c});
                } else if (grid[r][c] == 1) {
                    fresh += 1;
                }
            }
        }
        if (fresh == 0) return 0;
        while (!queue.isEmpty()) {
            int k = queue.size();
            while (k-- > 0) {
                int[] idx = queue.poll();
                int r = idx[0], c = idx[1];
                for (int i = 0; i < NO_DIRS; i++) {
                    int nr = r + DIRS[i], nc = c + DIRS[i + 1];
                    if (nr < 0 || nr == rows || nc < 0 || nc == cols || grid[nr][nc] != 1) {
                        continue;
                    };
                    grid[nr][nc] = 2; // Mark the orange at (nr, nc) as rotten
                    queue.offer(new int[] {nr, nc}); // Put the new rotton position in the queue
                    fresh -= 1; // Decrease the count of fresh oranges by 1
                }
            }
            if (!queue.isEmpty()) {
                minutes += 1;
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        int rows = 3, cols = 3;
        int[][] grid = {
                {2, 1, 1}, {1, 1, 0}, {0, 1, 1}
        };
        MethodsUtility.printArray(grid, rows, cols);
        int rottonOrangesSteps = orangesRotting(grid);
        System.out.println("No of minutes it took to change over - " + rottonOrangesSteps);
        //
        grid = new int[][] {
                {2, 1, 1}, {0, 1, 1}, {1, 0, 1}
        };
        MethodsUtility.printArray(grid, rows, cols);
        rottonOrangesSteps = orangesRotting(grid);
        System.out.println("No of minutes it took to change over - " + rottonOrangesSteps);
        //
        rows = 1; cols = 2;
        grid = new int[][] {
                {0, 2}
        };
        MethodsUtility.printArray(grid, rows, cols);
        rottonOrangesSteps = orangesRotting(grid);
        System.out.println("No of minutes it took to change over - " + rottonOrangesSteps);
    }

}
