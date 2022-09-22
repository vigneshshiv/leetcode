package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 */
public class FindDistanceBetween2Arrays {

    private static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int result = 0;
        Arrays.sort(arr2);
        for (int num : arr1) {
            int distance = findDistance(arr2, num);
            if (Math.abs(num - distance) > d) {
                result += 1;
            }
        }
        return result;
    }

    private static int findDistance(int[] arr, int target) {
        int low = 0, mid = 0, high = arr.length - 1;
        int distance = Integer.MAX_VALUE;
        while (low <= high) {
            mid = low + (high - low) / 2;
            distance = Math.abs(target - arr[mid]) < Math.abs(target - distance) ? arr[mid] : distance;
            if (target == arr[mid]) {
                return arr[mid];
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 8}, arr2 = {10, 9, 1, 8};
        int d = 2;
        int result = findTheDistanceValue(arr1, arr2, d);
        System.out.println("Result - " + result);
        //
        arr1 = new int[] {1, 4, 2, 3}; arr2 = new int[] {-4, -3, 6, 10, 20, 30};
        d = 3;
        result = findTheDistanceValue(arr1, arr2, d);
        System.out.println("Result - " + result);
        //
        arr1 = new int[] {2, 1, 100, 3}; arr2 = new int[] {-5, -2, 10, -3, 7};
        d = 6;
        result = findTheDistanceValue(arr1, arr2, d);
        System.out.println("Result - " + result);
    }

}
