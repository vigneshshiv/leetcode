package code.java.greedy;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {

    private static String findLargestNumber(int[] nums) {
        String[] arr = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(arr, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        return Arrays.stream(arr).reduce((x, y) -> x.equals("0") ? y : x + y).get();
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
