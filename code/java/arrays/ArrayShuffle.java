package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/shuffle-the-array/
 */
public class ArrayShuffle {

    private static int[] shuffle(int[] nums, int n) {
        int i = 0, j = n, idx = 0;
        int[] result = new int[nums.length];
        while (j < nums.length) {
            result[idx++] = nums[i++];
            result[idx++] = nums[j++];
        }
        return result;
    }

    /**
     * TODO: Needs a working solution
     * https://leetcode.com/problems/shuffle-the-array/discuss/675956/In-Place-O(n)-Time-O(1)-Space-With-Explanation-and-Analysis
     */
    private static int[] shuffleInPlace(int[] nums, int n) {
        int i = n - 1, j = nums.length - 1;
        while (j >= n && i >= 0) {
            nums[j] <<= 10;
            nums[j--] |= nums[i--];
        }
        i = 0; j = n;
        while (j < nums.length && i < nums.length) {
            nums[i] = nums[j] & 1023;
            nums[i + 1] = nums[j] >> 10;
            i += 2;
        }
        return nums;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        int[] nums = {2, 5, 1, 3, 4, 7};
        int[] result = shuffle(nums, 3);
        logger.accept(nums, result);
        int[] input = nums.clone();
        shuffleInPlace(nums, 3);
        logger.accept(input, nums);
        //
        nums = new int[] {1, 2, 3, 4, 4, 3, 2, 1};
        result = shuffle(nums, 4);
        logger.accept(nums, result);
        input = nums.clone();
        shuffleInPlace(nums, 3);
        logger.accept(input, nums);
        //
        nums = new int[] {1, 1, 2, 2};
        result = shuffle(nums, 2);
        logger.accept(nums, result);
        input = nums.clone();
        shuffleInPlace(nums, 3);
        logger.accept(input, nums);
    }

}
