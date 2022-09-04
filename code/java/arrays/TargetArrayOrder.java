package code.java.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/create-target-array-in-the-given-order/
 */
public class TargetArrayOrder {

    private static int[] createTargetArray(int[] nums, int[] index) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int idx = index[i];
            if (idx == i) {
                result[i] = nums[i];
            } else {
                int j = i;
                while (j != idx) {
                    result[j] = result[j - 1];
                    j--;
                }
                result[j] = nums[i];
            }
        }
        return result;
    }

    private static int[] createTargetArrayWithListApproach(int[] nums, int[] index) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(index[i], nums[i]);
        }
        return result.stream().mapToInt(item -> item).toArray();
    }


    public static void main(String[] args) {
        BiConsumer<int[], int[]> target_array_logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        int[] nums = {0, 1, 2, 3, 4};
        int[] index = {0, 1, 2, 2, 1};
        int[] result = createTargetArrayWithListApproach(nums, index);
        target_array_logger.accept(nums, result);
        //
        nums = new int[] {1, 2, 3, 4, 0};
        index = new int[] {0, 1, 2, 3, 0};
        result = createTargetArray(nums, index);
        target_array_logger.accept(nums, result);
        //
        nums = new int[] {1};
        index = new int[] {0};
        result = createTargetArray(nums, index);
        target_array_logger.accept(nums, result);
    }

}
