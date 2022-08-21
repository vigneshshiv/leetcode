package code.java.sorting;

import java.util.Arrays;
import java.util.function.Function;

public class SortValleyPeak {

    /**
     * Time complexity: O(n log(n))
     * Space complexity: O(1)
     */
    private static void sortValleyPeakEasy(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i += 2) {
            Sort.swap(arr, i - 1, i);
        }
    }

    private static void sortValleyPeakOptimal(int[] arr) {
        int bigIdx = 0;
        for (int i = 1; i < arr.length; i += 2) {
            bigIdx = maxIndex(arr, i - 1, i, i + 1);
            if (i != bigIdx) {
                Sort.swap(arr, i - 1, i);
            }
        }
    }

    private static int maxIndex(int[] arr, int prev_idx, int curr_idx, int next_idx) {
        int len = arr.length;
        Function<Integer, Integer> getIndexValue = idx -> idx >= 0 && idx < len ? arr[idx] : Integer.MIN_VALUE;
        int prev = getIndexValue.apply(prev_idx);
        int curr = getIndexValue.apply(curr_idx);
        int next = getIndexValue.apply(next_idx);
        int max = Math.max(prev, Math.max(curr, next));
        //
        if (prev == max) {
            return prev_idx;
        } else if (curr == max) {
            return curr_idx;
        } else {
            return next_idx;
        }
    }

    public static boolean confirmValleyPeak(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            int prev = arr[i - 1];
            int curr = arr[i];
            int next = arr[i + 1];
            if (prev <= curr && curr <= next) {
                continue;
            } else if (prev >= curr && curr <= next) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 2, 3};
        sortValleyPeakEasy(arr);
        System.out.println("Sorted Peak Valley - " + Arrays.toString(arr) + ", Is it valid? - " + confirmValleyPeak(arr));
        //
        arr = new int[] {5, 3, 1, 2, 3};
        sortValleyPeakOptimal(arr);
        System.out.println("Optimal Sorted Peak Valley - " + Arrays.toString(arr) + ", Is it valid? - " + confirmValleyPeak(arr));
        //
        arr = new int[] {3, 5, 1, 3, 2};
        System.out.println("Sorted Peak Valley Is it valid? - " + confirmValleyPeak(arr));
        //
        arr = new int[] {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        sortValleyPeakEasy(arr);
        System.out.println("Sorted Peak Valley - " + Arrays.toString(arr) + ", Is it valid? - " + confirmValleyPeak(arr));
        //
        arr = new int[] {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        sortValleyPeakOptimal(arr);
        System.out.println("Optimal Sorted Peak Valley - " + Arrays.toString(arr) + ", Is it valid? - " + confirmValleyPeak(arr));
    }

}
