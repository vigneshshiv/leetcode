package code.java.greedy;

import java.util.*;
import java.util.function.Function;

/**
 * There is a row of empty (.) and filled (x) seats
 *
 * Find the minimum number of moves required to make the people sit together
 *
 * Input: "..x..x."
 * Output: 2
 * Explanation: Either of the "x"s can move to the seat closest to the other one.
 *  "..xx..." OR "....xx."
 *
 */
public class GroupSeats {

    /**
     * Median
     */
    private static Function<List<Integer>, Integer> median = list -> {
        var middle = (int) Math.floor(list.size() / 2);
        Collections.sort(list);
        return (list.size() & 1) == 0 ? list.get(middle) : (list.get(middle - 1) + list.get(middle + 1) / 2);
    };

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int findMinimumMoveRequired(String[] seats) {
        List<Integer> crosses = new ArrayList<>();
        // All the indices of x's
        for (int idx = 0; idx < seats.length; idx++) {
            if (Objects.equals(seats[idx], "x")) {
                crosses.add(idx);
            }
        }
        if (crosses.size() == 0) {
            return 0;
        }
        // Moves required assuming starting position is 0
        for (int idx = 0; idx < crosses.size(); idx++) {
            crosses.set(idx, crosses.get(idx) - idx);
        }
        int segment_start = median.apply(crosses);
        int total = 0;
        for (int cross : crosses) {
            total += Math.abs(cross - segment_start);
        }
        return Math.min(Integer.MAX_VALUE, total);
    }

    public static void main(String[] args) {
        String[] seats = {".", ".", "x", ".", ".", "x", "."};
        int minimumMove = findMinimumMoveRequired(seats);
        System.out.println("Seats - " + Arrays.toString(seats) + ", Minimum move - " + minimumMove);
        //
        seats = new String[] {".", "x", ".", ".", "x", ".", ".", "x", "x", "."};
        minimumMove = findMinimumMoveRequired(seats);
        System.out.println("Seats - " + Arrays.toString(seats) + ", Minimum move - " + minimumMove);
        //
        seats = new String[] {"x", "x", ".", ".", "x", "x", ".", ".", ".", ".", "x", "x", "x"};
        minimumMove = findMinimumMoveRequired(seats);
        System.out.println("Seats - " + Arrays.toString(seats) + ", Minimum move - " + minimumMove);
    }

}
