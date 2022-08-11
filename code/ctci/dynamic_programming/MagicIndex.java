package code.ctci.dynamic_programming;

import java.util.Arrays;

public class MagicIndex {

    private static int findIndexInSortedArray(int[] array) {
        return findIndex(array, 0, array.length - 1);
    }

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int findIndex(int[] array, int start, int end) {
        if (end < start) return -1;
        int mid = (start + end) / 2;
        if (array[mid] == mid) {
            return mid;
        } else if (array[mid] < mid) {
            return findIndex(array, mid + 1, end);
        } else {
            return findIndex(array, start, mid - 1);
        }
    }

    private static int findIndexInNotDistinctElements(int[] array) {
        return findIndexNotDistinct(array, 0, array.length - 1);
    }

    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    private static int findIndexNotDistinct(int[] array, int start, int end) {
        if (end < start) return -1;
        int mid = (start + end) / 2;
        if (array[mid] == mid) return mid;
        // Search Left
        int leftIndex = Math.min(array[mid], mid - 1);
        int left = findIndexNotDistinct(array, start, leftIndex);
        if (left >= 0) {
            return left;
        }
        // Search Right
        int rightIndex = Math.max(array[mid], mid + 1);
        int right = findIndexNotDistinct(array, rightIndex, end);
        return right;
    }

    public static void main(String[] args) {
        int[] array = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        System.out.println("Distinct Input - " + Arrays.toString(array) + ", Magic Index - " + findIndexInSortedArray(array));
        int[] notDistinctArray = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        System.out.println("Not Distinct Input - " + Arrays.toString(array) + ", Magic Index - " + findIndexInNotDistinctElements(array));
    }

}
