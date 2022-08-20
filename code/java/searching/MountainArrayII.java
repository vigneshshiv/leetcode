package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-in-mountain-array/
 */
public class MountainArrayII {

    private static int findInMountainArray(int[] arr, int target) {
        int peakIndex = MountainArray.peakIndexInMountainArray(arr);
        int leftIndex = binarySearchOrderAgnostic(arr, target, 0, peakIndex);
        if (leftIndex != -1) {
            return leftIndex;
        }
        return binarySearchOrderAgnostic(arr, target, peakIndex + 1, arr.length - 1);
    }

    private static int binarySearchOrderAgnostic(int[] arr, int target, int low, int high) {
        int mid = 0;
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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 3, 1};
        int target = 3;
        int result = findInMountainArray(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
        //
        arr = new int[] {0, 1, 2, 4, 2, 1};
        target = 3;
        result = findInMountainArray(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Result - " + result);
    }

}
