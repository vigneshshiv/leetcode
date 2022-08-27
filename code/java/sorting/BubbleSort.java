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

    /**
     * Time complexity: O(n^2) average and worst case, Best case is O(n)
     * Space complexity: O(n^2)
     */
    private static void bubbleSortRecursive(int[] arr) {
        if (arr.length == 0) return;
        bubbleSortRecursionHelper(arr, arr.length - 1, 0);
    }

    private static void bubbleSortRecursionHelper(int[] arr, int itr, int idx) {
        if (itr == 0) return;
        if (idx < itr) {
            if (idx + 1 <= itr && arr[idx] > arr[idx + 1]) {
                Sort.swap(arr, idx, idx + 1);
            }
            bubbleSortRecursionHelper(arr, itr, idx + 1);
        } else {
            bubbleSortRecursionHelper(arr, itr - 1, 0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        // bubbleSort(arr);
        bubbleSortRecursive(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        arr = new int[] {1, 2, 3, 4, 5};
        bubbleSortRecursive(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        arr = new int[] {1};
        bubbleSortRecursive(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        arr = new int[] {5, 7, -2, 0, -3};
        bubbleSortRecursive(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        bubbleSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        arr = new int[] {};
        bubbleSortRecursive(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
