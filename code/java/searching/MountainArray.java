package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 *
 * https://leetcode.com/problems/find-peak-element/
 */
public class MountainArray {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // Descending part of the array, current index might be the answer but look at left side to confirm
                high = mid;
            } else {
                // Ascending order of the array, so look at right side ignoring the middle element
                low = mid + 1;
            }
        }
        // Both low and high are equal, so it found the highest element which is peak of the array element
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 4, 3, 2};
        int result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);
        //
        arr = new int[] {0, 1, 0};
        result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);
        //
        arr = new int[] {0, 2, 1, 0};
        result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);
        //
        arr = new int[] {0, 10, 5, 2};
        result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);
        // 162. Find Peak Element
        arr = new int[] {1, 2, 3, 1};
        result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);
        // 162. Find Peak Element
        arr = new int[] {1, 2, 1, 3, 5, 6, 4};
        result = peakIndexInMountainArray(arr);
        System.out.println("Input - " + Arrays.toString(arr) + ", Result - " + result);

    }

}
