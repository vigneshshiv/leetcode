package code.java.greedy;

import code.java.sorting.Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of a random permutation of numbers from 1 to N.
 * Given no. of swaps in array that we can make, find the largest permutation possible
 */
public class LargestPermutation {

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static void largestPermutation(int[] arr, int swaps) {
        int max = Arrays.stream(arr).max().getAsInt();
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            table.put(arr[i], i);
        }
        int idx = 0;
        while (swaps > 0 && idx < arr.length) {
            int j = table.get(max);
            if (idx == j) {
                continue;
            } else {
                swaps -= 1;
                Sort.swap(arr, idx, j);
                // Update table index as well
                table.put(arr[idx], idx);
                table.put(arr[j], j);
            }
            idx += 1;
            max -= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2};
        int swaps = 1;
        int[] input = arr.clone();
        largestPermutation(arr, swaps);
        System.out.println("Largest Permutation - Input - " + Arrays.toString(input) + ", Swaps - " + swaps + ", Result - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4};
        swaps = 1;
        input = arr.clone();
        largestPermutation(arr, swaps);
        System.out.println("Largest Permutation - Input - " + Arrays.toString(input) + ", Swaps - " + swaps + ", Result - " + Arrays.toString(arr));
        //
        arr = new int[] {3, 2, 4, 1, 5};
        swaps = 3;
        input = arr.clone();
        largestPermutation(arr, swaps);
        System.out.println("Largest Permutation - Input - " + Arrays.toString(input) + ", Swaps - " + swaps + ", Result - " + Arrays.toString(arr));
    }

}
