package code.java.sorting;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class QuickSort {

    /**
     * Time complexity: Best and Average - O(n log(n)), Worst case - O(n^2), where n is the number of elements
     * Space complexity:
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int start = low, end = high;
        int mid = start + (end - start) / 2;
        int pivot = arr[mid];
        while (start <= end) {
            while (arr[start] < pivot) {
                start++;
            }
            while (arr[end] > pivot) {
                end--;
            }
            if (start <= end) {
                Sort.swap(arr, start, end);
                start++;
                end--;
            }
        }
        quickSort(arr, low, end);
        quickSort(arr, start, high);
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result)
                -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        //
        int[] arr = {2, 4, 1, 3, 5};
        int[] input = arr.clone();
        quickSort(arr, 0, arr.length - 1);
        logger.accept(input, arr);
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        quickSort(arr, 0, arr.length - 1);
        logger.accept(input, arr);
        //
        arr = new int[] {3, 6, 4, 9, 2, 7, 1, 5};
        input = arr.clone();
        quickSort(arr, 0, arr.length - 1);
        logger.accept(input, arr);
    }

}
