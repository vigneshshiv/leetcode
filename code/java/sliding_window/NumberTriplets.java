package code.java.sliding_window;

/**
 * https://leetcode.com/problems/number-of-arithmetic-triplets/
 */
public class NumberTriplets {

    private static int arithmeticTriplets(int[] nums, int diff) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            while (nums[j] - nums[i] <= diff && j < nums.length - 1) {
                if (nums[j] - nums[i] == diff) break;
                else j++;
            }
            if (nums[j] - nums[i] > diff) continue;
            int k = j + 1;
            while (k < nums.length && nums[k] - nums[j] <= diff) {
                if (nums[k] - nums[j] == diff) break;
                else k++;
            }
            if (k < nums.length && nums[k] - nums[j] == diff && nums[j] - nums[i] == diff) {
                count += 1;
            }
        }
        return count;
    }

    private static int arithmeticTripletsOptimal(int[] nums, int diff) {
        int count = 0;
        int[] visited = new int[201];
        for (int num : nums) {
            if (num >= 2 * diff) {
                if (visited[num - diff] > 0 && visited[num - 2 * diff] > 0) {
                    count += 1;
                }
            }
            visited[num] = 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 4, 6, 7, 10};
        int diff = 3;
        System.out.println("Triplets difference - " + arithmeticTripletsOptimal(nums, diff));
        //
        nums = new int[] {4, 5, 6, 7, 8, 9};
        diff = 2;
        System.out.println("Triplets difference - " + arithmeticTriplets(nums, diff));
    }

}
