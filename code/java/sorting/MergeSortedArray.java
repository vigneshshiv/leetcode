package code.java.sorting;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

    /**
     * Time complexity: O(a + b)
     * Space complexity: O(1)
     *
     * @param a Holds a + b array size, which can free up space to move b array to a in the sorted order
     * @param b Array to be merged
     * @param a_count Count of a array
     * @param b_count Count of b array
     */
    private static void merge(int[] a, int[] b, int a_count, int b_count) {
        int result_idx = a_count + b_count - 1;
        int a_idx = a_count - 1;
        int b_idx = b_count - 1;
        while (b_idx >= 0) {
            if (a_idx >= 0 && a[a_idx] > b[b_idx]) {
                a[result_idx] = a[a_idx];
                a_idx--;
            } else {
                a[result_idx] = b[b_idx];
                b_idx--;
            }
            result_idx--;
        }
    }

    /**
     * Solved latest on 08SEP2022
     */
    private static void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int idx = nums1.length - 1; m -= 1; n -= 1;
        while (n >= 0) {
            if (m >= 0 && nums1[m] > nums2[n]) {
                nums1[idx] = nums1[m--];
            } else {
                nums1[idx] = nums2[n--];
            }
            idx--;
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 7, 12, 0, 0, 0, 0, 0};
        int[] b = {2, 5, 10, 11, 13};
        merge(a, b, 4, b.length);
        System.out.println("Sorted Merged Array - " + Arrays.toString(a));
        //
        a = new int[] {1, 2, 3, 0, 0, 0};
        b = new int[] {2, 5, 6};
        merge(a, b, 3, b.length);
        System.out.println("Sorted Merged Array - " + Arrays.toString(a));
        //
        a = new int[] {0};
        b = new int[] {1};
        merge(a, b, 0, b.length);
        System.out.println("Sorted Merged Array - " + Arrays.toString(a));
        //
        a = new int[] {};
        b = new int[] {};
        merge(a, b, 0, 0);
        System.out.println("Sorted Merged Array - " + Arrays.toString(a));
    }

}
