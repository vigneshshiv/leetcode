package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElement {

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums2.length - 1; i++) {
            table.put(nums2[i], nums2[i] < nums2[i + 1] ? nums2[i + 1] : findNextGreaterElement(nums2, nums2[i], i + 2));
        }
        table.put(nums2[nums2.length - 1], -1);
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = table.get(nums1[i]);
        }
        return result;
    }

    private static int findNextGreaterElement(int[] nums, int num, int idx) {
        for ( ; idx < nums.length; idx++) {
            if (nums[idx] > num) return nums[idx];
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/next-greater-element-i/discuss/97595
     */
    private static int[] nextGreaterElementOptimal(int[] nums1, int[] nums2) {
        Map<Integer, Integer> table = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                table.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = table.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
        int[] result = nextGreaterElement(nums1, nums2);
        System.out.println("Result - " + Arrays.toString(result));
        //
        nums1 = new int[] {2, 4};
        nums2 = new int[] {1, 2, 3, 4};
        result = nextGreaterElement(nums1, nums2);
        System.out.println("Result - " + Arrays.toString(result));
        //
        //
        nums1 = new int[] {1, 3, 5, 2, 4};
        nums2 = new int[] {6, 5, 4, 3, 2, 1, 7};
        result = nextGreaterElementOptimal(nums1, nums2);
        System.out.println("Result - " + Arrays.toString(result));
    }

}
