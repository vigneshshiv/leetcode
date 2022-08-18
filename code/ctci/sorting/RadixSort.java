package code.ctci.sorting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

public class RadixSort {

    private static final int BASE = 10;

    /**
     * The idea of Radix Sort is to do digit by digit sort starting from least significant digit to most significant digit.
     * Radix sort uses counting sort as a subroutine to sort.
     *
     * Elements are range from 1 to N...
     *
     * Time complexity: O(kn)
     * Space complexity: O(k + n)
     */
    private static int[] radixSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 1) {
            return arr;
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int placeValue = 1;
        while (placeValue < max) {
            arr = countingSort(arr, placeValue);
            placeValue *= BASE;
        }
        return arr;
    }

    private static int[] countingSort(int[] arr, int placeValue) {
        int[] result = new int[arr.length];
        int[] frequency = new int[BASE];
        int i = 1;
        //
        for (int num : arr) {
            int digit = (num / placeValue) % BASE;
            frequency[digit]++;
        }
        for ( ; i < BASE; i++) {
            frequency[i] += frequency[i - 1];
        }
        for (i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / placeValue) % BASE;
            result[frequency[digit] - 1] = arr[i];
            frequency[digit]--;
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result)
                -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        //
        int[] arr = {2, 4, 1, 3, 5};
        int[] input = arr.clone();
        int[] result = radixSort(arr);
        logger.accept(input, result);
        //
        arr = new int[] { 20, 91, 5, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        result = radixSort(arr);
        logger.accept(input, result);
        //
        arr = new int[] {3, 6, 4, 9, 2, 7, 1, 5};
        input = arr.clone();
        result = radixSort(arr);
        logger.accept(input, result);
    }

}
