package code.java.arrays;

import java.util.Arrays;

public class ArrayReverse {

    /**
     * The code runs in O(N) time,
     * Since iterating half of the elements does not impact Big O time
     */
    void reverse(int[] arr) {
        System.out.println("Received Array - " + Arrays.toString(arr));
        for (int i = 0; i < arr.length / 2; i++) {
            int other = arr.length - i - 1;
            int temp = arr[i];
            arr[i] = arr[other];
            arr[other] = temp;
        }
        System.out.println("Modified Array - " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        ArrayReverse arrayReverse = new ArrayReverse();
        int[] arr = {2, 5, 8, 6, 4, 7, 3};
        arrayReverse.reverse(arr);
    }

}
