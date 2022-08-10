package code.ctci.dynamic_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Say that you are a traveler on a 2D grid.
 * You begin in the top-left corner and your goal is to travel to the bottom-right corner. You may only move down or right.
 *
 * In how many ways can you travel to the goal on a grid with dimensions m * n?
 */
public class GridTraveler {

    private static BiFunction<Integer, Integer, String> getKey = (n, m) -> n + "#" + m;

    /**
     * Time complexity: O(2^(n + m)) time
     * Space complexity: O(n + m) space, height of the tree
     */
    static int findWays(int n, int m) {
        if (n == 1 && m == 1) return 1;
        if (n == 0 || m == 0) return 0;
        return findWays(n - 1, m) + findWays(n, m - 1);
    }

    /**
     * Time complexity: O(n * m) time, where n & m are the possible ways
     * Space complexity: O(n + m) space, Multi-linear function
     */
    static int findWaysMemo(int n, int m, Map<String, Integer> memo) {
        String key = getKey.apply(n, m);
        if (memo.containsKey(key)) return memo.get(key);
        if (n == 1 && m == 1) return 1;
        if (n == 0 || m == 0) return 0;
        int result = findWaysMemo(n - 1, m, memo) + findWaysMemo(n, m - 1, memo);
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (matrix, noOfWays) -> System.out.println(matrix + " matrix - " + noOfWays);
        /*
        logger.accept("1x1", findWays(1, 1));
        logger.accept("2x3", findWays(2, 3));
        logger.accept("3x2", findWays(3, 2));
        logger.accept("3x3", findWays(3, 3));
        logger.accept("15x15", findWays(15, 15)); // 40116600
        */
        // Memoized solution
        logger.accept("1x1", findWaysMemo(1, 1, new HashMap<>()));
        logger.accept("2x3", findWaysMemo(2, 3, new HashMap<>()));
        logger.accept("3x2", findWaysMemo(3, 2, new HashMap<>()));
        logger.accept("3x3", findWaysMemo(3, 3, new HashMap<>()));
        logger.accept("15x15", findWaysMemo(15, 15, new HashMap<>())); // 40116600
    }

}
