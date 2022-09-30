package code.java.searching;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
 */
public class MaximumDistance {

    private static int maxDistance(int[] nums1, int[] nums2) {
        int distance = 0, idx = 0;
        for (int num : nums1) {
            distance = Math.max(distance, findIndex(nums2, num, idx));
            idx += 1;
        }
        return distance;
    }

    private static int findIndex(int[] nums, int target, int start) {
        int low = start, mid = 0, high = nums.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high - start;
    }

    /**
     * https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/discuss/1198733
     */
    private static int maxDistanceTwoPointers(int[] nums1, int[] nums2) {
        int distance = 0, i = 0, j = 0, n = nums1.length, m = nums2.length;
        while (i < n && j < m) {
            if (nums1[i] > nums2[j]) {
                i += 1;
            } else {
                distance = Math.max(distance, j - i);
                j += 1;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (nums, result) -> {
            System.out.println("Nums1 - " + Arrays.toString(nums[0]) + ", Nums2 - " + Arrays.toString(nums[1]) + ", Result - " + result);
        };
        //
        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {100, 20, 10, 10, 5};
        int distance = maxDistance(nums1, nums2);
        logger.accept(new int[][] {nums1, nums2}, distance);
        //
        nums1 = new int[] {2, 2, 1}; nums2 = new int[] {10, 10, 1};
        distance = maxDistance(nums1, nums2);
        logger.accept(new int[][] {nums1, nums2}, distance);
        //
        nums1 = new int[] {30, 29, 19, 5}; nums2 = new int[] {25, 25, 25, 25, 25};
        distance = maxDistanceTwoPointers(nums1, nums2);
        logger.accept(new int[][] {nums1, nums2}, distance);
    }
    
}
