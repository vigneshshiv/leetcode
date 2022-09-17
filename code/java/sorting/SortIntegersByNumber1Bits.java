package code.java.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
 */
public class SortIntegersByNumber1Bits {

    /**
     * Since the input values can range from 0 to 10000,
     *  we can add to each element on the array the value of the bit count (number of 1s) multiplied by 10001.
     * 10001 is not a magic number, it is merely the max possible number the array may have + 1,
     *  which ensures that the bit count has the maximum priority
     */
    private static int[] sortByBitsOptimal(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += (Integer.bitCount(arr[i]) * 10001);
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] %= 10001;
        }
        return arr;
    }

    private static int[] sortByBits(int[] arr) {
        var nums = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
        var comparator = Comparator.comparing(Integer::bitCount).thenComparing(Integer::intValue);
        Arrays.sort(nums, comparator);
        return Arrays.stream(nums).mapToInt(num -> num).toArray();
    }

    private static int[] sortByBitsOneLiner(int[] arr) {
        return Arrays.stream(arr) //
                .boxed() //
                .sorted(Comparator.comparing(Integer::bitCount).thenComparing(Integer::intValue)) //
                .mapToInt(num -> num) //
                .toArray();
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (arr, result) -> {
            System.out.println("Array - " + Arrays.toString(arr));
            System.out.println("Sorted By Number of 1 Bits - " + Arrays.toString(result));
            System.out.println();
        };
        //
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = sortByBitsOptimal(arr.clone());
        logger.accept(arr, result);
        result = sortByBits(arr.clone());
        logger.accept(arr, result);
        result = sortByBitsOneLiner(arr.clone());
        logger.accept(arr, result);
        //
        arr = new int[] {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        result = sortByBitsOptimal(arr.clone());
        logger.accept(arr, result);
        result = sortByBits(arr.clone());
        logger.accept(arr, result);
        result = sortByBitsOneLiner(arr.clone());
        logger.accept(arr, result);
    }

}
