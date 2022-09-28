package code.java.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/check-if-n-and-its-double-exist/
 */
public class CheckNIfDoubleExist {

    private static boolean checkIfExist(int[] arr) {
        Set<Integer> store = new HashSet<>();
        for (int x : arr) {
            if (store.contains(2 * x) || x % 2 == 0 && store.contains(x / 2))
                return true;
            store.add(x);
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Boolean> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {10, 2, 5, 3};
        boolean result = checkIfExist(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {3, 1, 7, 11};
        result = checkIfExist(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {7, 1, 14, 11};
        result = checkIfExist(nums);
        logger.accept(nums, result);
    }

}
