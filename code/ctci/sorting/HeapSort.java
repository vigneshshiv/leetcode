package code.ctci.sorting;

import java.util.Arrays;
import java.util.Objects;

public class HeapSort {

    /**
     * Time complexity: O(n log(n))
     * Space complexity: O(1)
     */
    private static void heapSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 1) {
            return;
        }
        // Build Max-Heapify structure from an unordered array
        buildHeap(arr);
        // Iterate and swap the elements and run heapify again till last element
        for (int idx = arr.length - 1; idx > 0; idx--) {
            Sort.swap(arr, idx, 0);
            heapify(arr, idx, 0);
        }
    }

    private static void buildHeap(int[] arr) {
        int n = arr.length;
        for (int idx = n / 2 - 1; idx >= 0; idx--) {
            heapify(arr, n, idx);
        }
    }

    private static void heapify(int[] arr, int n, int idx) {
        // Initialize largest as root
        int largest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        // If left child is greater than a root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != idx) {
            Sort.swap(arr, largest, idx);
            // Recursively heapify the root (modified sub-tree)
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {1};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {10, 5, 3, 4, 1};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {7, 9, 3, 16, 10, 14, 8, 1, 4};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
        //
        arr = new int[] {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        input = arr.clone();
        heapSort(arr);
        Sort.logger.accept(Arrays.toString(input), Arrays.toString(arr));
    }

}
