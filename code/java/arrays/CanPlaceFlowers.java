package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/can-place-flowers/
 */
public class CanPlaceFlowers {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // Case 1. No flowers to plant, so return true
        if (n == 0) return true;
        // Case 2. If No. of flowers to plant adajacently is higher than the half flowerbed array 
        int range = (flowerbed.length / 2) + ((flowerbed.length & 1) == 0 ? 0 : 1);
        if (n > range) return false;
        // Case 3. Iterate to check prev[i - 1] and next[i + 1] elements to satisfy the constraint
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n -= 1;
                if (n == 0) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Boolean> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", N - " + Arrays.toString(input[1]) + ", Result " + result);
        };
        //
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;
        boolean result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {1, 0, 0, 0, 1}; n = 2;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {1, 0, 0, 1}; n = 1;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {0, 0, 0, 0, 0}; n = 1;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {0, 1, 0, 0, 0}; n = 1;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        // TBC
        flowerbed = new int[] {0, 1, 0, 0, 1}; n = 1;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {0, 1, 0, 1, 0}; n = 1;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {1, 0, 0, 0, 0, 0, 1}; n = 2;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        //
        flowerbed = new int[] {1, 0, 1, 0, 1, 0, 1}; n = 0;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
        // 
        flowerbed = new int[] {1, 0, 0, 0, 1, 0, 0}; n = 2;
        result = canPlaceFlowers(flowerbed, n);
        logger.accept(new int[][] {flowerbed, new int[] {n}}, result);
    }

}
