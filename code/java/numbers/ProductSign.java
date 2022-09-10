package code.java.numbers;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/sign-of-the-product-of-an-array/
 */
public class ProductSign {

    private static int arraySign(int[] nums) {
        int sign = 1;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) {
                sign = -sign;
            }
        }
        return sign;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, sign) -> System.out.println("Nums - " + Arrays.toString(nums) + ", Sign - " + sign);
        //
        int[] nums = {-1, -2, -3, -4, 3, 2, 1};
        int sign = arraySign(nums);
        logger.accept(nums, sign);
        //
        nums = new int[] {1, 5, 0, 2, -3};
        sign = arraySign(nums);
        logger.accept(nums, sign);
        //
        nums = new int[] {-1, 1, -1, 1, -1};
        sign = arraySign(nums);
        logger.accept(nums, sign);
        //
        nums = new int[] {7, 36, 96, 70, 85, 23, 5, 18, 4, 12, 89, 92, 9, 30, 53, 14, 96, 32, 13, 43, 37, 60, 75, 7,
                83, 68, 20, 8, -24, -80, -27, -92, -96, -20, -16, -52, -49, -38};
        sign = arraySign(nums);
        logger.accept(nums, sign);
    }

}
