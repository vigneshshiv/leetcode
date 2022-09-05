package code.java.arrays;

/**
 * https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/
 */
public class CountKDifference {

    private static int countKDifference(int[] nums, int k) {
        int count = 0;
        int[] frequency = new int[101];
        for (int num : nums) {
            frequency[num]++;
        }
        for (int i = k + 1; i < 101; i++) {
            count += frequency[i] * frequency[i - k];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1};
        int k = 1;
        System.out.println("K difference count - " + countKDifference(nums, k));
        //
        nums = new int[] {1, 3};
        k = 3;
        System.out.println("K difference count - " + countKDifference(nums, k));
        //
        nums = new int[] {3, 2, 1, 5, 4};
        k = 2;
        System.out.println("K difference count - " + countKDifference(nums, k));
    }

}
