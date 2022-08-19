package code.java.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * LeetCode
 *
 * https://leetcode.com/problems/missing-number/
 *
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class MissingNumber {

    /**
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static int missingNumber(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] < arr.length && arr[i] != i) {
                Sort.swap(arr, arr[i], i);
            } else {
                i++;
            }
        }
        // Search for first missing number
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] != idx) {
                return idx;
            }
        }
        return arr.length;
    }

    /**
     * If array starts from 0-N then to get total = n * ( (n + 1) / 2 )
     * otherwise 1-N case, total should be - (n + 1) * ( (n + 2) / 2 ) and result should be Math.abs(total - sum)
     *
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static int missingNumberEasy(int[] arr, boolean zeroIndex) {
        if (Objects.isNull(arr)) return -1;
        int n = arr.length;
        int total = zeroIndex ? (n * ((n + 1) / 2)) : ((n + 1) * (n + 2)) / 2;
        int sum = Arrays.stream(arr).sum();
        return Math.abs(total - sum);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int missingNumberOptimal(int[] arr) {
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            x = x ^ arr[i] ^ (i + 1);
        }
        return x;
    }

    /**
     * Time complexity: O(n) where n is the number of elements in the array
     * Space complexity: O(m), where m is the missing numbers
     */
    private static List<Integer> findAllMissingNumbers(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int index = arr[i] - 1;
            if (arr[i] != arr[index]) {
                Sort.swap(arr, index, i);
            } else {
                i++;
            }
        }
        List<Integer> numbers = new ArrayList<>();
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] != idx + 1) {
                numbers.add(idx + 1);
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        int[] arr = {4, 0, 2, 1};
        int[] input = arr.clone();
        int num = missingNumber(arr);
        Sort.logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {2, 0, 1};
        input = arr.clone();
        num = missingNumberOptimal(arr);
        Sort.logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {0, 3, 1, 4, 6, 7, 5};
        input = arr.clone();
        num = missingNumberEasy(arr, true);
        Sort.logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {8, 3, 1, 4, 2, 6, 5};
        input = arr.clone();
        num = missingNumberEasy(arr, false);
        Sort.logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {8, 2, 1, 4, 7, 6, 3};
        input = arr.clone();
        num = missingNumberEasy(arr, false);
        Sort.logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {4,3,2,7,8,2,3,1};
        input = arr.clone();
        List<Integer> numbers = findAllMissingNumbers(arr);
        Sort.logger.accept(Arrays.toString(input), numbers.toString());
    }

}
