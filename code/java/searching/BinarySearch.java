package code.java.searching;

import java.util.Arrays;

public class BinarySearch {

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int binarySearch(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
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
        int mid = (low + high) / 2;
        if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, high);
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, low, mid - 1);
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
        int mid = (low + high) / 2;
        if (arr[mid] < target) {
            return binarySearchClosest(arr, target, mid + 1, high);
        } else if (arr[mid] > target) {
            return binarySearchClosest(arr, target, low, mid - 1);
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
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 54;
        result = binarySearchRecursive(arr, target, 0, arr.length);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {3, 6, 9, 12, 15, 18};
        target = 3;
        result = binarySearchRecursive(arr, target, 0, arr.length);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 45;
        result = binarySearchRecursive(arr, target, 0, arr.length);
        System.out.println("Recursive: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {2, 4, 5, 7, 9, 11, 34, 54, 65, 78, 87, 89};
        target = 43;
        result = binarySearchClosest(arr, target, 0, arr.length);
        System.out.println("Closest: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
        //
        arr = new int[] {3, 6, 9, 12, 15, 18};
        target = 7;
        result = binarySearchClosest(arr, target, 0, arr.length);
        System.out.println("Closest: Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result Index - " + result);
    }

}