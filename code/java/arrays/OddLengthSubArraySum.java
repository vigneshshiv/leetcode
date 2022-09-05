package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
 */
public class OddLengthSubArraySum {

    /**
     * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/discuss/981968
     */
    private static int sumOddLengthSubarrays(int[] arr) {
        int sum = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            sum += ((i + 1) * (n - i) + 1) / 2 * arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, sum) -> System.out.println("Input - " + Arrays.toString(input) + ", Sum - " + sum);
        //
        int[] arr = {1, 4, 2, 5, 3};
        int sum = sumOddLengthSubarrays(arr);
        logger.accept(arr, sum);
        //
        arr = new int[] {1, 2};
        sum = sumOddLengthSubarrays(arr);
        logger.accept(arr, sum);
        //
        arr = new int[] {10, 11, 12};
        sum = sumOddLengthSubarrays(arr);
        logger.accept(arr, sum);
    }

}
