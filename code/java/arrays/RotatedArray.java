package code.java.arrays;

import java.util.Arrays;
import java.util.Objects;

/**
 * https://leetcode.com/problems/rotate-array/
 */
public class RotatedArray {

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int[] rotateArray(int[] arr, int rotateTimes) {
        int[] result = new int[arr.length];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            idx = (i + rotateTimes) % arr.length;
            result[idx] = arr[i];
        }
        return result;
    }

    /**
     * Right Rotation
     * In place is fixed for only 2 elements, not the best version of other rotate times
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static void rotateArrayInPlace(int[] arr, int rotateTimes) {
        int arr_length = arr.length;
        if (Objects.isNull(arr) || arr_length < 2) {
            return;
        }
        int idx_0_val = arr[arr_length - 2];
        int idx_1_val = arr[arr_length - 1];
        for (int i = arr_length - 1; i > 1; i -= rotateTimes) {
            // Place previous index values to current index
            arr[i] = arr[(i - rotateTimes) % arr_length];
            arr[i - 1] = arr[(i - 1 - rotateTimes) % arr_length];
        }
        arr[0] = idx_0_val;
        arr[1] = idx_1_val;
    }

    /**
     * Juggling Algorithm
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static void rotateArrayOptimal(int[] arr, int rotateTimes, boolean rightRotate) {
        int arr_length = arr.length;
        if (Objects.isNull(arr) || arr_length < 2 || rotateTimes == 0) {
            return;
        }
        rotateTimes = rotateTimes % arr_length;
        // To use as left rotation
        if (rightRotate) {
            rotateTimes = arr_length - rotateTimes;
        }
        // GCD
        int g_c_d = gcd(rotateTimes, arr_length);
        int i, j, k, temp;
        for (i = 0; i < g_c_d; i++) {
            temp = arr[i];
            j = i;
            while (true) {
                k = (j + rotateTimes) % arr_length;
                if (k == i) {
                    break;
                }
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * Two Pointer approach with XOR for swapping arr position
     *
     * @param arr
     * @param rotateTimes
     */
    private static void rotateArrayWithTwoPointer(int[] arr, int rotateTimes) {
        int arr_length = arr.length;
        rotateTimes %= arr_length;
        reverse(arr, 0, arr_length - 1);
        reverse(arr, 0, rotateTimes - 1);
        reverse(arr, rotateTimes, arr_length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            arr[start] ^= arr[end];
            arr[end] ^= arr[start];
            arr[start] ^= arr[end];
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7};
        int rotateTimes = 2;
        int[] result = rotateArray(arr, rotateTimes);
        System.out.println("Input - " + Arrays.toString(arr) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(result));
        //
        arr = new int[] {1, 2, 4, 5, 6, 7};
        int[] input = arr.clone();
        rotateTimes = 2;
        rotateArrayInPlace(arr, rotateTimes);
        System.out.println("In-Place: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 4, 5, 6, 7};
        input = arr.clone();
        rotateTimes = 3;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 4, 5, 6, 7};
        input = arr.clone();
        rotateTimes = 1;
        rotateArrayOptimal(arr, rotateTimes, false);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        input = arr.clone();
        rotateTimes = 3;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {-1, -100, 3, 99};
        input = arr.clone();
        rotateTimes = 2;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {13};
        input = arr.clone();
        rotateTimes = 1;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2};
        input = arr.clone();
        rotateTimes = 3;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3};
        input = arr.clone();
        rotateTimes = 4;
        rotateArrayOptimal(arr, rotateTimes, true);
        System.out.println("Juggling: Input - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2};
        input = arr.clone();
        rotateTimes = 3;
        rotateArrayWithTwoPointer(arr, rotateTimes);
        System.out.println("Two Pointers - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
        //
        arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        input = arr.clone();
        rotateTimes = 3;
        rotateArrayWithTwoPointer(arr, rotateTimes);
        System.out.println("Two Pointers - " + Arrays.toString(input) + ", Rotate times - " + rotateTimes + ", Rotated Array - " + Arrays.toString(arr));
    }

}
