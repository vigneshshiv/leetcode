package code.ctci.numbers;

import code.ctci.sorting.Sort;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/set-mismatch/
 */
public class SetMismatch {

    /**
     * Time complexity: O(n) where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static int[] findErrorNums(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int index = arr[i] - 1;
            if (arr[i] != arr[index]) {
                Sort.swap(arr, index, i);
            } else {
                i++;
            }
        }
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] != idx + 1) {
                return new int[] {arr[idx], idx + 1};
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        int[] arr = {1, 2, 2, 4};
        int[] input = arr.clone();
        int[] numbers = findErrorNums(arr);
        logger.accept(Arrays.toString(input), Arrays.toString(numbers));
        //
        arr = new int[] {1, 1};
        input = arr.clone();
        numbers = findErrorNums(arr);
        logger.accept(Arrays.toString(input), Arrays.toString(numbers));
    }

}
