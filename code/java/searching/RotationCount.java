package code.java.searching;

import java.util.Arrays;

public class RotationCount {

    /**
     * In a Rotated binary search, pivot (highest) is the indication that array is rotated (pivot index + 1) times
     */
    private static int findRotationCount(int[] arr) {
        return RotatedBinarySearch.findPivot(arr) + 1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int result = findRotationCount(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Rotation count - " + result);
    }

}
