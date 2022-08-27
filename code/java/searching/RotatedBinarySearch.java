package code.java.searching;

import java.util.Arrays;
import java.util.Objects;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class RotatedBinarySearch {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(log n)
     */
    private static int findIndexRecursive(int[] arr, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[low] <= arr[mid]) {
            if (target >= arr[low] && target <= arr[mid]) {
                return findIndexRecursive(arr, target, low, mid - 1);
            } else {
                return findIndexRecursive(arr, target, mid + 1, high);
            }
        }
        if (target >= arr[mid] && target <= arr[high]) {
            return findIndexRecursive(arr, target, mid + 1, high);
        }
        return findIndexRecursive(arr, target, low, mid - 1);
    }

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int findIndexInRotatedArray(int[] arr, int target) {
        // Base case
        if (Objects.isNull(arr) || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int pivot = findPivot(arr);
        // If pivot not found, Do a normal binary search
        if (pivot == -1) {
            return binarySearch(arr, target, 0, arr.length - 1);
        }
        if (arr[pivot] == target) {
            return pivot;
        }
        if (target >= arr[0]) {
            return binarySearch(arr, target, 0, pivot - 1);
        }
        return binarySearch(arr, target, pivot + 1, arr.length - 1);
    }

    /**
     * Find maximum element index
     */
    public static int findPivot(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 1. if middle element is greater than next element, which means middle element is the highest,
            // and next element starts in ascending order
            if (mid < high && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // 2. if middle element is smaller than the previous element, which means from middle element,
            // elements are placed in ascending order, and previous element is the highest
            if (mid > low && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }
            // 3. If middle element less than the start element, the highest element or arrays are the left side
            if (arr[mid] <= arr[low]) {
                high = mid - 1;
            } else {
                // 4. Search after middle element, to find the pivot which is the highest element
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Find maximum element index
     */
    private static int findPivotWithDuplicates(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 1. if middle element is greater than next element, which means middle element is the highest,
            // and next element starts in ascending order
            if (mid < high && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // 2. if middle element is smaller than the previous element, which means from middle element,
            // elements are placed in ascending order, and previous element is the highest
            if (mid > low && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }
            // 3. If elements are at middle, start, end are equal then skip the duplicates
            if (arr[mid] == arr[low] && arr[mid] == arr[high]) {
                // Skip the duplicates
                if (arr[low] > arr[low + 1]) {
                    return low;
                }
                low++;
                // Check whether pivot is high
                if (arr[high] < arr[high - 1]) {
                    return high - 1;
                }
                high--;
            } else if (arr[low] == arr[mid] || (arr[low] == arr[mid] && arr[mid] > arr[high]) ) {
                low = mid + 1;
            } else {
                high = high - 1;
            }
        }
        return -1;
    }

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
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = findIndexInRotatedArray(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        result = findIndexRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursion: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        //
        arr = new int[] {4, 5, 6, 7, 0, 1, 2};
        target = 3;
        result = findIndexInRotatedArray(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        result = findIndexRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursion: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        //
        arr = new int[] {1};
        target = 0;
        result = findIndexInRotatedArray(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        result = findIndexRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursion: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
    }

}
