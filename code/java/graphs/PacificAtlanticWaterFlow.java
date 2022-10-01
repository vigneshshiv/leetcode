package code.java.graphs;

import code.java.utils.MethodsUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {

    private static final int NO_DIRS = 4;
    private static int[] DIRS = {0, 1, 0, -1, 0};

    private static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        // Rows, Cols range
        int rows = heights.length, cols = heights[0].length;
        // Pacific and Atlantic ocean visit array
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        // Iterate on the Pacific ocean borders, i,e COLS 0-N and O-M
        for (int c = 0; c < cols; c++) {
            dfs(heights, 0, c, Integer.MIN_VALUE, pacific);
            dfs(heights, rows - 1, c, Integer.MIN_VALUE, atlantic);
        }
        for (int r = 0; r < rows; r++) {
            dfs(heights, r, 0, Integer.MIN_VALUE, pacific);
            dfs(heights, r, cols - 1, Integer.MIN_VALUE, atlantic);
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] heights, int r, int c, int prev, boolean[][] ocean) {
        // Boundary conditions
        if (r < 0 || r >= ocean.length || c < 0 || c >= ocean[0].length) {
            return;
        };
        if (ocean[r][c] || heights[r][c] < prev) {
            return;
        }
        ocean[r][c] = true;
        for (int dir = 0; dir < NO_DIRS; dir++) {
            dfs(heights, r + DIRS[dir], c + DIRS[dir + 1], heights[r][c], ocean);
        }
    }

    public static void main(String[] args) {
        int rows = 5, cols = 5;
        int[][] heights = {
                {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        };
        MethodsUtility.printArray(heights, rows, cols);
        List<List<Integer>> result = pacificAtlantic(heights);
        System.out.println(result);
        System.out.println();
        //
        rows = 1; cols = 1;
        heights = new int[][] { {1} };
        result = pacificAtlantic(heights);
        System.out.println(result);
        System.out.println();
        //
        rows = 3; cols = 6;
        heights = new int[][] {
                {3, 3, 3, 3, 3, 3}, {3, 0, 3, 3, 0, 3}, {3, 3, 3, 3, 3, 3}
        };
        MethodsUtility.printArray(heights, rows, cols);
        result = pacificAtlantic(heights);
        System.out.println(result);
    }

}
