package code.java.graphs;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
public class RemoveStones {

    private static int removeStones(int[][] stones) {
        int islandCount = 0;
        Set<int[]> visited = new HashSet<>();
        for (int[] x : stones) {
            if (visited.contains(x)) continue;
            dfs(stones, x, visited);
            islandCount += 1;
        }
        return stones.length - islandCount;
    }

    private static void dfs(int[][] stones, int[] x, Set<int[]> visited) {
        visited.add(x);
        for (int[] y : stones) {
            if (visited.contains(y)) continue;
            // Stone placed in the same row (x-axis) or column (y-axis)
            if (x[0] == y[0] || x[1] == y[1]) {
                dfs(stones, y, visited);
            }
        }
    }

    private static Map<Integer, Integer> map;
    private static int islandCount;

    private static int removeStonesUnionFind(int[][] stones) {
        // For Local Testing reset always;
        map = new HashMap<>();
        islandCount = 0;
        for (int[] stone : stones) {
            union(stone[0], ~stone[1]);
        }
        return stones.length - islandCount;
    }

    private static int find(int x) {
        if (map.putIfAbsent(x, x) == null) {
            islandCount += 1;
        }
        if (x != map.get(x)) {
            map.put(x, find(map.get(x)));
        }
        return map.get(x);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            map.put(x, y);
            islandCount -= 1;
        }
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (stones, result) -> {
            System.out.println("Stones - " + Arrays.deepToString(stones) + ", Result - " + result);
        };
        //
        int[][] stones = {
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        };
        int result = removeStones(stones);
        logger.accept(stones, result);
        result = removeStonesUnionFind(stones);
        logger.accept(stones, result);
        //
        stones = new int[][] {
                {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        };
        result = removeStones(stones);
        logger.accept(stones, result);
        result = removeStonesUnionFind(stones);
        logger.accept(stones, result);
        //
        stones = new int[][] { {0, 0} };
        result = removeStones(stones);
        logger.accept(stones, result);
        result = removeStonesUnionFind(stones);
        logger.accept(stones, result);
    }

}
