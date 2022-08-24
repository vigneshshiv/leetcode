package code.java.greedy;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * TODO:
 * https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {

    private static final int BASE = 10;

    private static String findLargestNumber(int[] arr) {
        if (Objects.isNull(arr)) return null;
        if (arr.length == 1) {
            return String.valueOf(arr[0]);
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int digits = (int) Math.log10(max) + 1;
        while (digits > 0) {
            arr = countingSort(arr, digits);
            digits /= BASE;
        }
        StringBuilder result = new StringBuilder();
        for (int num : arr) {
            result.append(num);
        }
        return result.toString();
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
        BiConsumer<int[], String> logger = (input, result)
                -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + result);
        //
        int[] arr = {10, 2};
        int[] input = arr.clone();
        String result = findLargestNumber(arr);
        logger.accept(input, result);
        //
        arr = new int[] {3, 30, 34, 5, 9};
        input = arr.clone();
        result = findLargestNumber(arr);
        logger.accept(input, result);
        //
        arr = new int[] {3, 6, 4, 9, 2, 7, 1, 5};
        input = arr.clone();
        result = findLargestNumber(arr);
        logger.accept(input, result);
    }

}
