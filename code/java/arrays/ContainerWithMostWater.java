package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

    private static int maxArea(int[] height) {
        int mxarea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            mxarea = Math.max(mxarea, area);
            if (height[i] < height[j]) {
                i += 1;
            } else {
                j -= 1;
            }
        }
        return mxarea;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (height, area) -> {
            System.out.println("Heights - " + Arrays.toString(height) + ", Max area - " + area);
        };
        //
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxArea(height);
        logger.accept(height, area);
        //
        height = new int[] {1, 1};
        area = maxArea(height);
        logger.accept(height, area);
    }

}
