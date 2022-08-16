package code.ctci.sorting;

import java.util.Arrays;

public class BubbleSort {

    /**
     * Time complexity: O(n^2) average and worst case, Best case is O(n)
     * Space complexity: O(1)
     */
    private static void bubbleSort(int[] arr) {
        boolean swapped = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j -1] = temp;
                    swapped = true;
                }
            }
            if (i != arr.length && !swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println("Sorted array - " + Arrays.toString(arr));
        int[] array = {1, 2, 3, 4, 5};
        bubbleSort(array);
        System.out.println("Sorted array - " + Arrays.toString(array));
    }

}
