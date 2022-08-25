package code.java.sliding_window;

import java.util.Arrays;
import java.util.Objects;

public class SubArraySum {

    /**
     * Time complexity: O(n - k)
     * Space complexity: O(1)
     */
    private static int findSubArraySum(int[] arr, int k) {
        if (Objects.isNull(arr) || arr.length == 0 || arr.length < k) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < arr.length - k + 1; i++) {
            sum += arr[i] + arr[i + 1] + arr[i + 2];
            if (i > 0) {
                sum -= arr[i - 1];
            }
        }
        return sum;
    }

    /**
     * Time complexity: O(n), actual is O(2n)
     * Space complexity: O(m), m is target
     */
    private static int[] findMinSubArrayOfSum(int[] arr, int target) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return new int[] {-1};
        }
        int[] result = new int[target];
        int start = 0, end = 0, sum = 0;
        int min_length = Integer.MAX_VALUE;
        while (end < arr.length) {
            sum += arr[end];
            end += 1;
            while (start < end && sum >= target) {
                sum -= arr[start];
                start += 1;
                if (sum == target && end - start + 1 < min_length) {
                    min_length = end - start + 1;
                    result = Arrays.copyOfRange(arr, start, end);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 3;
        int result = findSubArraySum(arr, k);
        System.out.println("SubArraySum: Input - " + Arrays.toString(arr) + ", k - " + k + ", Result - " + result);
        //
        arr = new int[] {1, 2, 3, 4, 5, 6};
        int target = 3;
        int[] nums = findMinSubArrayOfSum(arr, target);
        System.out.println("Min SubArrayOfSum: Input - " + Arrays.toString(arr) + ", target - " + target + ", Result - " + Arrays.toString(nums));
        //
        target = 6;
        nums = findMinSubArrayOfSum(arr, target);
        System.out.println("Min SubArrayOfSum: Input - " + Arrays.toString(arr) + ", target - " + target + ", Result - " + Arrays.toString(nums));
    }

}
