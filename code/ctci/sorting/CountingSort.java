package code.ctci.sorting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

public class CountingSort {

    /**
     * Time complexity: O(n + k), where n is the number of elements and k is the largest number in the array
     * Space complexity: O(n + k)
     */
    private static int[] countingSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 1) {
            return arr;
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int[] result = new int[arr.length];
        int[] frequency = new int[max + 1];
        int i = 1;
        //
        for (int num : arr) {
            frequency[num]++;
        }
        for ( ; i < frequency.length; i++) {
            frequency[i] += frequency[i - 1];
        }
        for (i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            result[frequency[value] - 1] = value;
            frequency[value]--;
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result)
                -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        //
        int[] arr = {2, 4, 1, 3, 5};
        int[] input = arr.clone();
        int[] result = countingSort(arr);
        logger.accept(input, result);
        //
        arr = new int[] {3, 6, 4, 9, 2, 7, 1, 5};
        input = arr.clone();
        result = countingSort(arr);
        logger.accept(input, result);
    }

}
