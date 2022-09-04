package code.java.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/decompress-run-length-encoded-list/
 */
public class ArrayDecompress {

    private static int[] decompressRLElist(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            int value = nums[i + 1];
            int j = nums[i];
            while (j > 0) {
                result.add(value);
                j--;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] decompressRLElistOptimal(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i += 2) {
            size += nums[i];
        }
        int[] result = new int[size];
        int startIdx = 0;
        for (int i = 0; i < nums.length; i += 2) {
            Arrays.fill(result, startIdx, startIdx + nums[i], nums[i + 1]);
            startIdx += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> decompress_logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        int[] nums = {1, 2, 3, 4};
        int[] result = decompressRLElist(nums);
        decompress_logger.accept(nums, result);
        //
        nums = new int[] {1, 1, 2, 3};
        result = decompressRLElistOptimal(nums);
        decompress_logger.accept(nums, result);
    }

}
