package code.java.arrays;

/**
 * https://leetcode.com/problems/count-good-triplets/
 */
public class CountTriplets {

    // TODO!
    private static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int i = 0, j = 1, k = 2;
        while (i < arr.length - 2) {
            if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                count += 1;
                k++;
                continue;
            }
            while (j < arr.length - 1 && Math.abs(arr[i] - arr[j]) > a) {
                j++;
            }
            if (j == arr.length - 1) {
                i++;
                j = i + 1;
                k = i + 2;
                continue;
            } else {
                j++;
                k = j + 1;
            }
            while (k < arr.length - 2 && Math.abs(arr[j] - arr[k]) > b) {
                k++;
            }
            if (k == arr.length - 2) {
                i++;
                j = i + 1;
                k = i + 2;
                continue;
            } else {
                k++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, 1, 1, 9, 7};
        int a = 7, b = 2, c = 3;
        int count = countGoodTriplets(arr, a, b, c);
        System.out.println(count);
        //
        arr = new int[] {1, 1, 2, 2, 3};
        a = 0; b = 0; c = 1;
        count = countGoodTriplets(arr, a, b, c);
        System.out.println(count);
    }

}
