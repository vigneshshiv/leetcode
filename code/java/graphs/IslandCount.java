package code.java.graphs;

import code.java.utils.MethodsUtility;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * https://leetcode.com/problems/number-of-islands/
 *
 * https://leetcode.com/problems/max-area-of-island/
 */
public class IslandCount {

    private static BiFunction<Integer, Integer, String> getPosition = (r, c) -> r + "#" + c;

    private static final int NO_DIRS = 4;
    private static int[] DIRS = {0, 1, 0, -1, 0};
    // OR
    private static int[] DR = {0, 0, 1, -1};
    private static int[] DC = {1, -1, 0, 0};

    /**
     * r - # of rows
     * c - # of cols
     *
     * Time complexity: O(rc)
     * Space complexity: O(rc)
     */
    private static int islandCount(String[][] grid) {
        int count = 0;
        if (Objects.isNull(grid) || grid.length == 0 || grid[0].length == 0) {
            return count;
        }
        Set<String> visited = new HashSet<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (exploreGrid(grid, r, c, visited)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    private static boolean exploreGrid(String[][] grid, int row, int col, Set<String> visited) {
        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean colInbounds = 0 <= col && col < grid[0].length;
        if (!rowInbounds || !colInbounds) {
            return false;
        }
        if (Objects.equals(grid[row][col], "W")) return false;
        String pos = getPosition.apply(row, col);
        if (visited.contains(pos)) return false;
        // Mark as visited
        visited.add(pos);
        for (int dir = 0; dir < NO_DIRS; dir++) {
            exploreGrid(grid, row + DIRS[dir], col + DIRS[dir + 1], visited);
        }
        return true;
    }

    /**
     * r - # of rows
     * c - # of cols
     *
     * Time complexity: O(rc)
     * Space complexity: O(rc)
     */
    private static int minimumIsland(String[][] grid) {
        int count = Integer.MAX_VALUE;
        if (Objects.isNull(grid) || grid.length == 0 || grid[0].length == 0) {
            return count;
        }
        Set<String> visited = new HashSet<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                int islandCount = explore(grid, r, c, visited);
                if (islandCount > 0) {
                    count = Math.min(count, islandCount);
                }
            }
        }
        return Objects.equals(count, Integer.MAX_VALUE) ? 0 : count;
    }

    private static int explore(String[][] grid, int row, int col, Set<String> visited) {
        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean colInbounds = 0 <= col && col < grid[0].length;
        //
        if (!rowInbounds || !colInbounds) {
            return 0;
        }
        if (Objects.equals(grid[row][col], "W")) {
            return 0;
        }
        int landCount = 1;
        String pos = getPosition.apply(row, col);
        if (visited.contains(pos)) return 0;
        // Mark as visited
        visited.add(pos);
        landCount += explore(grid, row - 1, col, visited);
        landCount += explore(grid, row + 1, col, visited);
        landCount += explore(grid, row, col - 1, visited);
        landCount += explore(grid, row, col + 1, visited);
        return landCount;
    }

    private static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        if (rows == 1 && cols == 1) {
            return grid[0][0];
        }
        int maxArea = 0;
        Set<String> visited = new HashSet<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, exploreArea(grid, r, c, visited));
                }
            }
        }
        return maxArea;
    }

    private static int exploreArea(int[][] grid, int row, int col, Set<String> visited) {
        boolean rowInbounds = (0 <= row && row < grid.length);
        boolean colInbounds = (0 <= col && col < grid[0].length);
        String key = row + "#" + col;
        //
        if (!rowInbounds || !colInbounds) return 0;
        if (grid[row][col] == 0) return 0;
        if (!visited.add(key)) return 0; // If it's already added, no need to visit
        int count = 1;
        for (int idx = 0; idx < NO_DIRS; idx++) {
            count += exploreArea(grid, row + DIRS[idx], col + DIRS[idx + 1], visited);
        }
        return count;
    }

    public static void main(String[] args) {
        String[][] grid = {
                {"W", "L", "W", "W", "W"},
                {"W", "L", "W", "W", "W"},
                {"W", "W", "W", "L", "W"},
                {"W", "W", "L", "L", "W"},
                {"L", "W", "W", "L", "L"},
                {"L", "L", "W", "W", "W"},
        }; // 3
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println("Minimum Island Count - " + minimumIsland(grid)); // 2
        System.out.println();
        //
        grid = new String[][] {
                {"L", "W", "W", "L", "W"},
                {"L", "W", "W", "L", "L"},
                {"W", "L", "W", "L", "W"},
                {"W", "W", "W", "W", "W"},
                {"W", "W", "L", "L", "L"}
        }; // 4
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println("Minimum Island Count - " + minimumIsland(grid)); // 1
        System.out.println();
        //
        grid = new String[][] {
                {"L", "L", "L", "L", "W"},
                {"L", "L", "W", "L", "W"},
                {"L", "L", "W", "W", "W"},
                {"W", "W", "W", "W", "W"},
        }; // 1
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println();
        //
        grid = new String[][] {
                {"L", "L", "W", "W", "W"},
                {"L", "L", "W", "W", "W"},
                {"W", "W", "L", "W", "W"},
                {"W", "W", "W", "L", "L"},
        }; // 3
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println();
        //
        grid = new String[][] {
                {"L", "L", "L"},
                {"L", "L", "L"},
                {"L", "L", "L"}
        }; // 1
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println("Minimum Island Count - " + minimumIsland(grid)); // 1
        System.out.println();
        //
        grid = new String[][] {
                {"W", "W"},
                {"W", "W"},
                {"W", "W"}
        }; // 0
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Island Count - " + islandCount(grid));
        System.out.println("Minimum Island Count - " + minimumIsland(grid)); // 0
        System.out.println();
        //
        grid = new String[][] {
                {"L", "W"},
                {"L", "W"},
                {"W", "L"}
        };
        MethodsUtility.printArray(grid, grid.length, grid[0].length);
        System.out.println("Minimum Island Count - " + minimumIsland(grid)); // 1
        System.out.println();
        //
        int[][] island = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        MethodsUtility.printArray(island, island.length, island[0].length);
        System.out.println("Maximum Area of Island - " + maxAreaOfIsland(island)); // 1

    }

}
