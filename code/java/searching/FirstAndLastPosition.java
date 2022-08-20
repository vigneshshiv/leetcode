package code.java.searching;

import java.util.Arrays;

public class FirstAndLastPosition {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int[] findFirstAndLastPosition(int[] arr, int target) {
        int start = -1, end = -1;
        start = searchIndex(arr, target, true);
        if (start != -1) {
            end = searchIndex(arr, target, false);
        }
        return new int[] {start, end};
    }

    private static int searchIndex(int[] arr, int target, boolean startIndex) {
        int low = 0, mid = 0, high = arr.length - 1;
        int index = -1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < arr[mid]) {
                high = mid - 1;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                index = mid;
                if (startIndex) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return index;
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 7, 7, 8, 8, 10};
        int target = 7;
        int[] result = findFirstAndLastPosition(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 8;
        result = findFirstAndLastPosition(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 4;
        result = findFirstAndLastPosition(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 5;
        result = findFirstAndLastPosition(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + Arrays.toString(result));
        //
        target = 10;
        result = findFirstAndLastPosition(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + Arrays.toString(result));
    }

}
