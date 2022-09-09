package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumII {

    private static int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (numbers[start] + numbers[end] != target) {
            if (numbers[start] + numbers[end] > target) {
                end -= 1;
            } else {
                start += 1;
            }
        }
        return new int[] {start + 1, end + 1}; // 1 based index
    }

    private static int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (target == numbers[i] + numbers[mid]) {
                    return new int[] {i + 1, mid + 1};
                } else if (target < numbers[i] + numbers[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        BiConsumer<int[][], int[]> logger = (input, indices) -> {
            System.out.println("Numbers - " + Arrays.toString(input[0]) + ", Target - " + Arrays.toString(input[1])
                    + ", Indices - " + Arrays.toString(indices));
        };
        //
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] indices = twoSum(numbers, target);
        logger.accept(new int[][]{numbers, new int[] {target}}, indices);
        //
        numbers = new int[] {2, 3, 4};
        target = 6;
        indices = twoSum(numbers, target);
        logger.accept(new int[][]{numbers, new int[] {target}}, indices);
        //
        numbers = new int[] {-1, 0};
        target = -1;
        indices = twoSum(numbers, target);
        logger.accept(new int[][]{numbers, new int[] {target}}, indices);
    }

}
