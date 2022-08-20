package code.java.searching;

import java.util.Arrays;

public class InfiniteArray {

    private static int findElementIndex(int[] arr, int target) {
        // Find the range
        int low = 0, high = 1;
        while (target > arr[high]) {
            int start = high + 1;
            high = high + (high - low + 1) * 2;
            low = start;
        }
        return binarySearch(arr, target, low, high);
    }

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int binarySearch(int[] arr, int target, int low, int high) {
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < arr[mid]) {
                high = mid - 1;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};
        int target = 10;
        int result = findElementIndex(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + "< Result - " + result);
    }

}
