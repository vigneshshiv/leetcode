package code.java.arrays;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class ArrayIntersection {

    /**
     * Not working for edges case like - Nums1 - [2,1], Nums2 - [1,2], Expected Result - [1,2], Actual Result - [2]
     *
     * Problem description not clear, as it says like elements must be an exact order, but it doesn't.
     */
    private static int[] intersectInitialTry(int[] nums1, int[] nums2) {
        int i = -1, j = -1;
        for (int num : nums1) {
            for (int idx = 0; idx < nums2.length; idx++) {
                if (num == nums2[idx]) {
                    if (i == -1) {
                        i = idx;
                        break;
                    } else if (idx > i) {
                        j = idx;
                    }
                }
            }
            if (i != -1 && j != -1) break;
        }
        return intersectData(nums2, i, j);
    }

    private static int[] intersectData(int[] nums, int i, int j) {
        if (i != -1 && j != -1) {
            int[] result = new int[j - i + 1];
            for (int idx = 0; i <= j; i++, idx++) {
                result[idx] = nums[i];
            }
            return result;
        } else {
            if (j == -1) return new int[] {nums[i]};
        }
        return new int[] {i, j};
    }

    private static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> table = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums1) {
            table.put(num, table.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (table.get(num) != null && table.get(num) > 0) {
                list.add(num);
                table.put(num, table.get(num) - 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Two Pointer works if array is sorted
     */
    private static int[] intersectTwoPointer(int[] nums1, int[] nums2) {
        // Sort just for the submission
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < n && j < m) {
            int a = nums1[i], b = nums2[j];
            if (a == b) {
                list.add(a);
                i += 1; j += 1;
            } else if (a < b) {
                i += 1;
            } else {
                j += 1;
            }
        }
        int[] result = new int[list.size()];
        for (i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Follow-up:
     * 1. What if the given array is already sorted? How would you optimize your algorithm?
     *      Two Pointer approach
     *
     * 2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
     *      Binary Search
     *
     * 3.What if elements of nums2 are stored on disk, and the memory is limited
     *   such that you cannot load all elements into the memory at once?
     *      - This one is open-ended. But Map-Reduce I believe is a good answer.
     *
     *  All details follow-up solutions at this link
     *  https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/282372
     */

    public static void main(String[] args) {
        BiConsumer<int[][], int[]> logger = (input, result) -> {
            System.out.println("Nums1 - " + Arrays.toString(input[0]) + ", Nums2 - " + Arrays.toString(input[1]) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = intersectTwoPointer(nums1, nums2);
        logger.accept(new int[][]{nums1, nums2}, result);
        //
        nums1 = new int[] {4, 9, 5};
        nums2 = new int[] {9, 4, 9, 8, 4};
        result = intersect(nums1, nums2);
        logger.accept(new int[][]{nums1, nums2}, result);
        //
        nums1 = new int[] {1};
        nums2 = new int[] {1};
        result = intersect(nums1, nums2);
        logger.accept(new int[][]{nums1, nums2}, result);
    }

}
