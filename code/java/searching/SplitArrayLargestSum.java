package code.java.searching;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 */
public class SplitArrayLargestSum {

    private static int splitArray(int[] nums, int m) {
        int low = 0, high = 0;
        // Find the range for low & high
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        if (m == nums.length) {
            return low;
        }
        // Binary Search
        int mid = 0;
        while (low < high) {
            // Potential answer to decide which side to move on
            mid = low + (high - low) / 2;
            int sum = 0;
            int pieces = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    // If the sub-array is greater than mid, initiate a new sum from this element
                    sum = num;
                    // Found the range till mid, in the previous iteration so increase pieces count
                    pieces++;
                } else {
                    sum += num;
                }
            }
            if (pieces > m) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high; // Both low & high is same
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        int result = splitArray(nums, m);
        System.out.println("Input - " + Arrays.toString(nums) + ", M - " + m + ", Result - " + result);
        //
        nums = new int[] {1, 2, 3, 4, 5};
        m = 2;
        result = splitArray(nums, m);
        System.out.println("Input - " + Arrays.toString(nums) + ", M - " + m + ", Result - " + result);
        //
        nums = new int[] {1, 4, 4};
        m = 3;
        result = splitArray(nums, m);
        System.out.println("Input - " + Arrays.toString(nums) + ", M - " + m + ", Result - " + result);
    }

}
