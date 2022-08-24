package code.java.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 *
 * Priority Queue is another way to solve this, if no of product increases
 */
public class ProductOf3Numbers {

    /**
     * Time complexity: O(n log(n)) - sorting
     * Space complexity: O(1)
     */
    private static int maximumProduct(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0 || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length - 1;
        // All are positive or negative
        int last3NumProduct = nums[n - 2] * nums[n - 1] * nums[n];
        // First 2 Negative and Last one positive
        int first2AndLast1Product = nums[0] * nums[1] * nums[n];
        return Math.max(last3NumProduct, first2AndLast1Product);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    private static int maximumProductOptimal(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0 || nums.length < 3) {
            return 0;
        }
        int max1 = Integer.MIN_VALUE, max2 = max1, max3 = max2;
        int min1 = Integer.MAX_VALUE, min2 = min1;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    /**
     * Time complexity: O(n) / ?
     * Space complexity: O(n)
     */
    private static int maximumProductByPriorityQueue(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            minHeap.offer(num);
            if (maxHeap.size() > 3) {
                maxHeap.poll();
            }
            if (minHeap.size() > 2) {
                minHeap.poll();
            }
        }
        int last3NumProduct = 1;
        // First 2 Negative and Last one positive
        int first2AndLast1Product = 0;
        while (!maxHeap.isEmpty()) {
            first2AndLast1Product = maxHeap.poll();
            last3NumProduct *= first2AndLast1Product;
        }
        while (!minHeap.isEmpty()) {
            first2AndLast1Product *= minHeap.poll();
        }
        return Math.max(last3NumProduct, first2AndLast1Product);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProduct(nums));
        //
        nums = new int[] {1, 2, 3, 4};
        System.out.println("Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProduct(nums));
        //
        nums = new int[] {-1, -2, -3};
        System.out.println("Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProduct(nums));
        //
        nums = new int[] {-5, -2, -1, 0, 0, 3, 4, 5};
        System.out.println("Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProduct(nums));
        System.out.println("PriorityQueue: Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProductByPriorityQueue(nums));
        //
        nums = new int[] {-5, -2, -1, 0, 0, 1, 1, 5};
        System.out.println("Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProductOptimal(nums));
        System.out.println("PriorityQueue: Input - " + Arrays.toString(nums) + ". Product of 3 Numbers - " + maximumProductByPriorityQueue(nums));
    }

}
