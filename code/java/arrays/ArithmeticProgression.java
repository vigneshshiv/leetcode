package code.java.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 */
public class ArithmeticProgression {

    private static boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }

    /**
     * No Sorting, with extra space of O(n)
     */
    private static boolean canMakeArithmeticProgressionOptimal(int[] arr) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE, n = arr.length;
        Set<Integer> table = new HashSet<>();
        for (int num : arr) {
            low = Math.min(low, num);
            high = Math.max(high, num);
            // Duplicate elements Additional check - if num already exists and so far, low & high not zero, then return false;
            if (!table.add(num) && high - low != 0) {
                return false;
            }
        }
        int diff = high - low;
        if (diff % (n - 1) != 0) {
            return false;
        }
        diff /= n - 1;
        while (--n > 0) {
            if (!table.contains(low)) {
                return false;
            }
            low += diff;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Same Difference - " + result);
        };
        //
        int[] nums = {3, 5, 1};
        boolean sameDifference = canMakeArithmeticProgressionOptimal(nums);
        logger.accept(nums, sameDifference);
        //
        nums = new int[] {1, 2, 4};
        sameDifference = canMakeArithmeticProgression(nums);
        logger.accept(nums, sameDifference);
    }

}
