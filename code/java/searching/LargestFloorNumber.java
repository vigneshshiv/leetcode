package code.java.searching;

import java.util.Arrays;

public class LargestFloorNumber {

    private static int findLargestFloorNumber(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
        if (target < arr[low]) return -1;
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
        return arr[high];
    }

    private static int findLargestFloorNumberRecursive(int[] arr, int target, int low, int high) {
        if (target < arr[low]) return -1;
        if (low > high) {
            if (low >= arr.length) return arr[low];
            return arr[high];
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] < target) {
            return findLargestFloorNumberRecursive(arr, target, mid + 1, high);
        } else if (arr[mid] > target) {
            return findLargestFloorNumberRecursive(arr, target, low, mid - 1);
        } else {
            return arr[mid];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 9, 14, 16, 18};
        int target = 15;
        int result = findLargestFloorNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
        //
        target = 1;
        result = findLargestFloorNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
        //
        target = 20;
        result = findLargestFloorNumber(arr, target);
        System.out.println("Input - " + Arrays.toString(arr) + ", Target - " + target + ", Next Ceiling Number - " + result);
    }

}
