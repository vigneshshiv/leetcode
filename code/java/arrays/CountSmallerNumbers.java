package code.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class CountSmallerNumbers {

    private static int[] smallerNumbersThanCurrent(int[] nums) {
        Map<Integer, Integer> table = new HashMap<>();
        int[] input = nums.clone();
        Arrays.sort(input);
        int prev = input[0];
        for (int i = 1; i < input.length; i++) {
            if (prev == input[i]) {
                table.put(input[i], table.getOrDefault(input[i], 0));
            } else {
                table.put(input[i], i);
                prev = input[i];
            }
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = table.getOrDefault(nums[i],0);
        }
        return result;
    }

    private static int[] smallerNumbersThanCurrentModified(int[] nums) {
        Map<Integer, Integer> table = new HashMap<>();
        int[] copy = nums.clone();
        Arrays.sort(copy);
        for (int i = 0; i < nums.length; i++) {
            table.putIfAbsent(copy[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            copy[i] = table.get(nums[i]);
        }
        return copy;
    }

    private static int[] smallerNumbersThanCurrentOptimal(int[] nums) {
        int[] frequency = new int[101];
        int[] result = new int[nums.length];
        for (int num : nums) {
            frequency[num]++;
        }
        for (int i = 1; i <= 100; i++) {
            frequency[i] += frequency[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] == 0 ? 0 : frequency[nums[i] - 1];
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] nums = {8, 1, 2, 2, 3};
        int[] result = smallerNumbersThanCurrentOptimal(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {6, 5, 4, 8};
        result = smallerNumbersThanCurrentModified(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {7, 7, 7, 7};
        result = smallerNumbersThanCurrent(nums);
        logger.accept(nums, result);
    }

}
