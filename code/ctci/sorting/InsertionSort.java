package code.ctci.sorting;

import java.util.Arrays;

public class InsertionSort implements Sort {

    private static void insertionSort(int[] arr) {
        int j, a;
        // For all integer values except the leftmost value ...
        for (int i = 1; i < arr.length; i++) {
            a = arr[i];
            // Initial insert position, if left side values greater than a
            j = i;
            while (j > 0 && arr[j - 1] > a) {
                // Shift left value arr[j - 1] -- one position to it's right -- arr[j]
                arr[j] = arr[j - 1];
                // Update insert position to one position to the left
                j--;
            }
            // Finally insert a at insert position, where a is a greater than or equal to all values to it's left.
            arr[j] = a;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        insertionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
