package code.java.searching;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/kth-missing-positive-number/
 */
public class KthMissingPositiveNumber {

    /**
     * Time complexity: O(log(n))
     * Space complexity: O(1)
     */
    private static int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] - 1 - mid < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low + k;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Integer> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", K - " + Arrays.toString(input[1])
                    + ", Missing positive number - " + result);
        };
        //
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        int result = findKthPositive(arr, k);
        logger.accept(new int[][]{arr, new int[] {k}}, result);
        //
        arr = new int[] {1, 2, 3, 4}; k = 2;
        result = findKthPositive(arr, k);
        logger.accept(new int[][]{arr, new int[] {k}}, result);
    }

}
