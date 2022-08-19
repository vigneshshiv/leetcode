package code.java.sorting;

import java.util.Arrays;

public class ShellSort {

    /**
     * Time complexity: O(n log(n)) best and average, O(n (log(n))^2) worse case.
     * Space complexity: O(1)
     */
    private static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // Insertion sort approach
            // For all integer values except the leftmost value ...
            for (int i = gap; i < arr.length; i++) {
                int a = arr[i];
                // Initial insert position, if left side values greater than a
                int j = i;
                while (j >= gap && arr[j - gap] > a) {
                    // Shift left value arr[j - gap] -- gap position to it's right -- arr[j]
                    arr[j] = arr[j - gap];
                    // Update insert position to gap position to the left
                    j -= gap;
                }
                // Finally insert a at insert position, where a is a greater than or equal to all values to it's left.
                arr[j] = a;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        shellSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
