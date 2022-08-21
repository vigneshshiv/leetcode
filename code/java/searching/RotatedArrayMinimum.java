package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class RotatedArrayMinimum {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int findMinimum(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 1. if middle element is greater than next element, which means middle element is the highest,
            // and next element starts in ascending order, so it's the min element
            if (mid < high && arr[mid] > arr[mid + 1]) {
                return arr[mid + 1];
            }
            // 2. if middle element is smaller than the previous element, which means previous element is the highest,
            // and middle element is the min element
            if (mid > low && arr[mid] < arr[mid - 1]) {
                return arr[mid];
            }
            // 3. If middle element less than the start element, the highest element and min element are on the left side
            if (arr[mid] <= arr[low]) {
                high = mid - 1;
            } else {
                // 4. Search after middle element, to find the pivot which is the highest element
                low = mid + 1;
            }
        }
        // Above cases, min element not found in the rotated array, which means array is not rotated.
        // So first element is the minimum element
        return arr[0];
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {3, 4, 5, 1, 2};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {4, 5, 6, 7, 0, 1, 2};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {11, 13, 15, 17};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {3};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
        //
        arr = new int[] {3, 1, 2};
        result = findMinimum(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Min Element - " + result);
    }

}
