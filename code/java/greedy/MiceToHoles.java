package code.java.greedy;

import java.util.Arrays;
import java.util.Objects;

/**
 * There are N mice and N holes.
 * A mice takes 1 minute to travel 1 unit left or right.
 *
 * Find the minimum time after which all mice are in holes.
 *
 * Note: Mice never cross will each other
 */
public class MiceToHoles {

    /**
     * Time complexity: O(n log(n))
     * Space complexity: O(1)
     */
    private static int findMinimumMovement(int[] mice, int[] holes) {
        if (Objects.isNull(mice) || Objects.isNull(holes)
                || mice.length == 0 || holes.length == 0 || mice.length != holes.length) {
            return 0;
        }
        int result = 0;
        int idx = 0;
        Arrays.sort(mice);
        Arrays.sort(holes);
        while (idx < mice.length) {
            result = Math.max(result, Math.abs(mice[idx] - holes[idx]));
            idx++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] mice = {3, 2, -4};
        int[] holes = {0, -2, 4};
        System.out.println("Minimum movement - " + findMinimumMovement(mice, holes));
    }

}
