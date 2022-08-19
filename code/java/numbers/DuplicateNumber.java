package code.java.numbers;

import code.java.sorting.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 *
 */
public class DuplicateNumber {

    /**
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static int findDuplicate(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] != i + 1) {
                if (arr[arr[i] - 1] != arr[i]) {
                    Sort.swap(arr, arr[i] - 1, i);
                } else {
                    return arr[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    /**
     * Given a list of integers where all integers occur odd times, expect one which occur even time. Find out that integer.
     *
     * Solution - Slow & Fast approach similar to Linked List Cycle detection
     *
     * Time: O(n) time, where n is the number of elements in the array
     * Space: O(1) space, no extra space allocated
     */
    static int findDuplicateOptimal(int[] arr) {
        int slow = arr[0];
        int fast = arr[arr[0]];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    /**
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(m)
     */
    private static List<Integer> findAllDuplicates(int[] arr) {
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
                numbers.add(arr[idx]);
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        int[] arr = {1, 3, 4, 2, 2};
        int[] input = arr.clone();
        int num = findDuplicate(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {3, 1, 3, 4, 2};
        input = arr.clone();
        num = findDuplicate(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {2, 2, 2, 2, 2};
        input = arr.clone();
        num = findDuplicate(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {1, 3, 4, 2, 2};
        input = arr.clone();
        num = findDuplicateOptimal(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {3, 1, 3, 4, 2};
        input = arr.clone();
        num = findDuplicateOptimal(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {2, 2, 2, 2, 2};
        input = arr.clone();
        num = findDuplicateOptimal(arr);
        logger.accept(Arrays.toString(input), String.valueOf(num));
        //
        arr = new int[] {4, 3, 2, 7, 8, 2, 3, 1};
        input = arr.clone();
        List<Integer> numbers = findAllDuplicates(arr);
        logger.accept(Arrays.toString(input), numbers.toString());
        //
        arr = new int[] {1, 1, 2};
        input = arr.clone();
        numbers = findAllDuplicates(arr);
        logger.accept(Arrays.toString(input), numbers.toString());
        //
        arr = new int[] {};
        input = arr.clone();
        numbers = findAllDuplicates(arr);
        logger.accept(Arrays.toString(input), numbers.toString());
    }

}
