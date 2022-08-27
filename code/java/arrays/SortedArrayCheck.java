package code.java.arrays;

import java.util.Arrays;

public class SortedArrayCheck {

    private static boolean isArraySortedInAscendingOrder(int[] arr) {
        return isSorted(arr, 0);
    }

    private static boolean isSorted(int[] arr, int i) {
        if (i == arr.length - 1) return true;
        return arr[i] < arr[i + 1] && isSorted(arr, i + 1);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 29, 76, 32};
        System.out.println(Arrays.toString(arr) + " is sorted in ascending order - " + isArraySortedInAscendingOrder(arr));
        //
        arr = new int[] {2, 3, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(arr) + " is sorted in ascending order - " + isArraySortedInAscendingOrder(arr));
    }

}
