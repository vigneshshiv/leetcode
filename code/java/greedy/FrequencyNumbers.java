package code.java.greedy;

import java.util.*;

/**
 * Given an array of integers of length N.
 * Find the majority element occurs with > [N/2] frequency.
 *
 * Input: [3,2,2,4,2,2]
 * Output: 2
 * Explanation: 2 occurs with the frequency of 2 > [6/2]
 *
 * Reference: Advanced algorithm
 * https://www.topcoder.com/thrive/articles/boyer-moore-algorithm-with-bad-character-heuristic
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
 *
 */
public class FrequencyNumbers {

    /**
     * By Sorting
     *
     * Time complexity: O(n log(n))
     * Space complexity: O(1) - ? (Sorting)
     */
    private static int findMajorityElementBySorting(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int findMajorityElement(int[] nums) {
        if (Objects.isNull(nums) || nums.length < 1) {
            return -1;
        }
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        int limit = nums.length / 2;
        Optional<Map.Entry<Integer, Integer>> number = frequency.entrySet().stream().filter(entry -> entry.getValue() > limit).findFirst();
        return number.isPresent() ? number.get().getKey() : 0;
    }

    /**
     * Time complexity: O(n log(w))
     * Space complexity: O(1)
     */
    private static int findMajorityElementOptimal(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) return nums[0];
        int result = 0;
        int limit = nums.length / 2;
        for (int idx = 0; idx < 32; idx++) {
            int ones = 0;
            int mask = 1 << idx;
            for (int num : nums) {
                if ((mask & num) == mask) {
                    ones += 1;
                }
            }
            if (ones > limit) {
                result += mask; // OR result |= mask; also works.
            }
        }
        return result;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int findMajorityElementEasyBitComplex(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return -1;
        }
        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count = 1;
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 4, 2, 2};
        int[] input = nums.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Majority Element (N>2) - " + findMajorityElementBySorting(nums));
        System.out.println("Input - " + Arrays.toString(input) + ", Majority Element (N>2) - " + findMajorityElement(input));
        System.out.println("Optimal: Input - " + Arrays.toString(input) + ", Majority Element (N>2) - " + findMajorityElementOptimal(input));
        //
        System.out.println("Easy Bit complex: Input - " + Arrays.toString(input) + ", Majority Element (N>2) - " + findMajorityElementEasyBitComplex(input));
    }

}
