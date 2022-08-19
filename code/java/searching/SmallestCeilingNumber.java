package code.java.searching;

import java.util.Arrays;

public class SmallestCeilingNumber {

    private static int findNextCeilingNumber(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                return arr[mid];
            }
        }
        return low >= arr.length ? -1 : arr[low];
    }

    private static int findNextCeilingNumberRecursive(int[] arr, int target, int low, int high) {
        if (low > high) {
            if (high < 0) return arr[low];
            if (low >= arr.length) return -1;
            return arr[low];
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] < target) {
            return findNextCeilingNumberRecursive(arr, target, mid + 1, high);
        } else if (arr[mid] > target) {
            return findNextCeilingNumberRecursive(arr, target, low, mid - 1);
        } else {
            return arr[mid];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 9, 14, 16, 18};
        int target = 15;
        int result = findNextCeilingNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
        //
        target = 1;
        result = findNextCeilingNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
        //
        target = 20;
        result = findNextCeilingNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
    }

}
