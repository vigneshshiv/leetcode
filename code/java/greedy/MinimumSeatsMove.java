package code.java.greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
 */
public class MinimumSeatsMove {

    private static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int moves = 0;
        for (int i = 0; i < seats.length; i++) {
            moves += Math.abs(seats[i] - students[i]);
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] seats = {3, 1, 5};
        int[] students = {2, 7, 4};
        System.out.println("Minimum move - " + minMovesToSeat(seats, students));
        //
        seats = new int[] {4, 1, 5, 9};
        students = new int[] {1, 3, 2, 6};
        System.out.println("Minimum move - " + minMovesToSeat(seats, students));
        //
        seats = new int[] {2, 2, 6, 6};
        students = new int[] {1, 3, 2, 6};
        System.out.println("Minimum move - " + minMovesToSeat(seats, students));
    }

}
