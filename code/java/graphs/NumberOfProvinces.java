package code.java.graphs;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/number-of-provinces/
 */
public class NumberOfProvinces {

    private static int findCircleNum(int[][] isConnected) {
        int m = isConnected.length, count = 0;
        boolean[] visited = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count += 1;
            }
        }
        return count;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }

    private static int findCircleNumWithModifyInput(int[][] isConnected) {
        int m = isConnected.length, count = 0;
        for (int i = 0; i < m; i++) {
            if (isConnected[i][i] == 1) {
                dfs(isConnected, i);
                count += 1;
            }
        }
        return count;
    }

    private static void dfs(int[][] isConnected, int i) {
        if (isConnected[i][i] == 0) return;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1) {
                isConnected[i][j] = isConnected[j][i] = 0;
                dfs(isConnected, j);
            }
        }
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.deepToString(input) + ", Result - " + result);
        };
        //
        int[][] isConnected = {
                {1, 1, 0}, {1, 1, 0}, {0, 0, 1}
        };
        int result = findCircleNum(isConnected);
        logger.accept(isConnected, result);
        result = findCircleNumWithModifyInput(isConnected);
        logger.accept(isConnected, result);
        //
        isConnected = new int[][] {
                {1, 0, 0}, {0, 1, 0}, {0, 0, 1}
        };
        result = findCircleNum(isConnected);
        logger.accept(isConnected, result);
        result = findCircleNumWithModifyInput(isConnected);
        logger.accept(isConnected, result);
    }

}
