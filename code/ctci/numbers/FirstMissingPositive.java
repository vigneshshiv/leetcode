package code.ctci.numbers;

import code.ctci.sorting.Sort;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {

    /**
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static int firstMissingPositive(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int index = arr[i] - 1;
            if (arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[index]) {
                Sort.swap(arr, index, i);
            } else {
                i++;
            }
        }
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] != idx + 1) {
                return idx + 1;
            }
        }
        return arr.length + 1;
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        int[] arr = {1, 2, 0};
        int[] input = arr.clone();
        int num = firstMissingPositive(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {3, 4, -1, 1};
        input = arr.clone();
        num = firstMissingPositive(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {7, 8, 9, 11, 12};
        input = arr.clone();
        num = firstMissingPositive(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
    }

}
