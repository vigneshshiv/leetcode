package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-the-original-array-of-prefix-xor/
 */
public class OriginalArrayPrefixXoR {
    
    public static int[] findArray(int[] pref) {
        int[] nums = new int[pref.length];
        nums[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            nums[i] = pref[i] ^ pref[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] pref = {5, 2, 0, 3, 1};
        int[] result = findArray(pref);
        logger.accept(pref, result);
        //
        pref = new int[] {13};
        result = findArray(pref);
        logger.accept(pref, result);
    }

}
