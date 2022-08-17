package code.ctci.sorting;

import java.util.Arrays;

public class CycleSort implements Sort {

    /**
     * Time complexity: O(2n), where n is the number of elements in the array
     * Space complexity: O(1)
     */
    private static void cycleSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] - 1 != i) {
                Sort.swap(arr, arr[i] - 1, i);
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        cycleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {3, 5, 2, 1, 4};
        input = arr.clone();
        cycleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        cycleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        cycleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
