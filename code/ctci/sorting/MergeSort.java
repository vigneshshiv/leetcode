package code.ctci.sorting;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class MergeSort {

    /**
     * Time complexity: O(n log(n)), where n is the number of elements in the array
     * Space complexity: O(n)
     */
    private static int[] mergeSortStandard(int[] arr) {
        if (arr.length == 1) return arr;
        int mid = arr.length / 2;
        int[] left = mergeSortStandard(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSortStandard(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        // Left over from left array
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        // Left over from right array
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }

    /**
     * Time complexity: O(n log(n)), where n is the number of elements in the array
     * Space complexity: O(n)
     */
    private static void mergeSortInPlace(int[] arr, int start, int end) {
        if (end - start == 1) return;
        int mid = (start + end) / 2;
        mergeSortInPlace(arr, start, mid);
        mergeSortInPlace(arr, mid, end);
        mergeInPlace(arr, start, mid, end);
    }

    private static void mergeInPlace(int[] arr, int start, int mid, int end) {
        int[] result = new int[end - start];
        int i = start, j = mid, k = 0;
        while (i < mid && j < end) {
            if (arr[i] <= arr[j]) {
                result[k] = arr[i];
                i++;
            } else {
                result[k] = arr[j];
                j++;
            }
            k++;
        }
        // Left over from left array
        while (i < mid) {
            result[k] = arr[i];
            i++;
            k++;
        }
        // Left over from right array
        while (j < end) {
            result[k] = arr[j];
            j++;
            k++;
        }
        // Modify the original array
        for (int idx = 0; idx < result.length; idx++) {
            arr[start + idx] = result[idx];
        }
    }

    /**
     * Time complexity: O(n log(n)), where n is the number of elements in the array
     * Space complexity: O(n)
     */
    private static int[] mergeSortOptimal(int[] arr) {
        if (arr.length == 1) return arr;
        int mid = arr.length / 2;
        int[] left = mergeSortStandard(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSortStandard(Arrays.copyOfRange(arr, mid, arr.length));
        return mergeOptimal(left, right);
    }

    private static int[] mergeOptimal(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = left.length - 1, j = right.length - 1, k = result.length;
        while (k > 0) {
            result[--k] = (j < 0 || (i >= 0 && left[i] >= right[j])) ? left[i--] : right[j--];
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result)
                -> System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        //
        int[] arr = {2, 4, 1, 3, 5};
        int[] input = arr.clone();
        int[] result = mergeSortStandard(arr);
        logger.accept(input, result);
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        result = mergeSortStandard(arr);
        logger.accept(input, result);
        //
        arr = new int[] {2, 4, 1, 3, 5};
        input = arr.clone();
        mergeSortInPlace(arr, 0, arr.length);
        logger.accept(input, arr);
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        mergeSortInPlace(arr, 0, arr.length);
        logger.accept(input, result);
        //
        arr = new int[] {2, 4, 1, 3, 5};
        input = arr.clone();
        result = mergeSortOptimal(arr);
        logger.accept(input, result);
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        result = mergeSortOptimal(arr);
        logger.accept(input, result);
        //
        arr = new int[] {3, 6, 4, 9, 2, 7, 1, 5};
        input = arr.clone();
        result = mergeSortOptimal(arr);
        logger.accept(input, result);
    }

}
