package code.ctci.sorting;

import java.util.Arrays;

public class SelectionSort implements Sort {

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            var first = i;
            var minIndex = findMinIndex(arr, first);
            Sort.swap(arr, first, minIndex);
        }
    }
    
    private static int findMinIndex(int[] arr, int first) {
        int minIndex = first;
        for (int j = first + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        selectionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        selectionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        selectionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        selectionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        selectionSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
