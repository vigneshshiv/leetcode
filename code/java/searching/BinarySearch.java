package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/binary-search/
 */
public class BinarySearch {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int binarySearch(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
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

    private static int binarySearchOrderAgnostic(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
        boolean isAsc = arr[low] < arr[high];
        while (low <= high) {
            mid = low + (high - low) / 2;
            // Target found
            if (arr[mid] == target) {
                return mid;
            }
            if (isAsc) {
                if (target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Time complexity: O(log n)
     * Space complexity: O(log n)
     */
    private static int binarySearchRecursive(int[] arr, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (target < arr[mid]) {
            return binarySearchRecursive(arr, target, low, mid - 1);
        } else if (target > arr[mid]) {
            return binarySearchRecursive(arr, target, mid + 1, high);
        } else {
            return mid;
        }
    }

    private static int binarySearchClosest(int[] arr, int target, int low, int high) {
        if (low > high) {
            if (high < 0) return low;
            if (low >= arr.length) return high;
            if (target - arr[high] < arr[low] - target) {
                return high;
            }
            return low;
        }
        int mid = low + (high - low) / 2;
        if (target < arr[mid]) {
            return binarySearchClosest(arr, target, low, mid - 1);
        } else if (target > arr[mid]) {
            return binarySearchClosest(arr, target, mid + 1, high);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        int target = 9;
        int result = binarySearch(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {3, 6, 9, 12, 15, 18};
        target = 12;
        result = binarySearch(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 45;
        result = binarySearch(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        target = 14;
        result = binarySearchOrderAgnostic(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 54;
        result = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {3, 6, 9, 12, 15, 18};
        target = 3;
        result = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 45;
        result = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 43;
        result = binarySearchClosest(arr, target, 0, arr.length - 1);
        System.out.println("Closest: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {3, 6, 9, 12, 15, 18};
        target = 7;
        result = binarySearchClosest(arr, target, 0, arr.length - 1);
        System.out.println("Closest: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
    }

}
