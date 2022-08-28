package code.java.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Maze {

    private static Supplier<List<String>> emptyList = () -> new ArrayList<>();

    private static List<String> allPathsToExit(String prefix, int n, int m, boolean[][] maze) {
        List<String> result = emptyList.get();
        if (n == maze.length - 1 && m == maze[n].length - 1) {
            result.add(prefix);
            return result;
        }
        if (maze[n][m]) return result;
        // If not visited, mark as visited
        maze[n][m] = true;
        if (n < maze.length - 1) { // Down
            result.addAll(allPathsToExit(prefix + 'D', n + 1, m, maze));
        }
        if (m < maze[n].length - 1) { // Right
            result.addAll(allPathsToExit(prefix + 'R', n, m + 1, maze));
        }
        if (n > 0) { // Up
            result.addAll(allPathsToExit(prefix + 'U', n - 1, m, maze));
        }
        if (m > 0) { // Left
            result.addAll(allPathsToExit(prefix + 'L', n, m - 1, maze));
        }
        // Reset for backtracking
        maze[n][m] = false;
        return result;
    }

    private static void printAllPathsToExit(String prefix, int n, int m, boolean[][] maze, int[][] path, int step) {
        if (n == maze.length - 1 && m == maze[n].length - 1) {
            path[n][m] = step;
            for (int[] route : path) {
                System.out.println(Arrays.toString(route));
            }
            System.out.println(prefix);
            System.out.println();
            return;
        }
        if (maze[n][m]) return;
        // If not visited, mark as visited
        maze[n][m] = true;
        path[n][m] = step;
        if (n < maze.length - 1) { // Down
            printAllPathsToExit(prefix + 'D', n + 1, m, maze, path, step + 1);
        }
        if (m < maze[n].length - 1) { // Right
            printAllPathsToExit(prefix + 'R', n, m + 1, maze, path, step + 1);
        }
        if (n > 0) { // Up
            printAllPathsToExit(prefix + 'U', n - 1, m, maze, path, step + 1);
        }
        if (m > 0) { // Left
            printAllPathsToExit(prefix + 'L', n, m - 1, maze, path, step + 1);
        }
        // Reset for backtracking
        maze[n][m] = false;
        path[n][m] = 0;
    }

    public static void main(String[] args) {
        int rows = 3, cols = 3;
        boolean[][] maze = new boolean[rows][cols];
        // Arrays fill
        Arrays.setAll(maze, r -> {
            Arrays.fill(maze[r], false);
            return maze[r];
        });
        List<String> result = allPathsToExit("", 0, 0, maze);
        System.out.println("Result - " + result);
        //
        // Arrays fill
        Arrays.setAll(maze, r -> {
            Arrays.fill(maze[r], false);
            return maze[r];
        });
        int[][] path = new int[rows][cols];
        Arrays.setAll(path, r -> {
            Arrays.setAll(path[r], c -> 0);
            return path[r];
        });
        printAllPathsToExit("", 0, 0, maze, path, 1);
    }

}
