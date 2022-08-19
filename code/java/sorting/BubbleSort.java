package code.java.sorting;

import java.util.Arrays;

public class BubbleSort implements Sort {

    /**
     * Time complexity: O(n^2) average and worst case, Best case is O(n)
     * Space complexity: O(1)
     */
    private static void bubbleSort(int[] arr) {
        boolean swapped = false;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    Sort.swap(arr, j, j - 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
