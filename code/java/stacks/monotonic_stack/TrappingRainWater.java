package code.java.stacks.monotonic_stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    /**
     * Monotonic Stack approach
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int trap(int[] height) {
        int water = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int j = stack.pop();
                if (!stack.isEmpty()) {
                    int h = Math.min(height[stack.peek()], height[i]) - height[j];
                    int w = i - (stack.peek() + 1);
                    water += (h * w);
                }
            }
            stack.push(i);
        }
        return water;
    }

    /**
     * Two Pointer approach
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int trapTwoPointers(int[] height) {
        int i = 0, j = height.length - 1;
        int LM = 0, RM = 0, water = 0;
        while (i < j) {
            int lh = height[i], rh = height[j];
            if (lh < rh) {
                LM = Math.max(lh, LM);
                water += LM - lh;
                i += 1;
            } else {
                RM = Math.max(rh, RM);
                water += RM - rh;
                j -= 1;
            }
        }
        return water;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int trapOptimal(int[] height) {
        int i = 0, j = height.length - 1;
        int level = 0, water = 0;
        while (i < j) {
            int lower = height[height[i] < height[j] ? i++ : j--];
            level = Math.max(level, lower);
            water += level - lower;
        }
        return water;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (height, water) -> {
            System.out.println("Height - " + Arrays.toString(height) + ", Water - " + water);
        };
        //
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int water = trap(height);
        logger.accept(height, water);
        water = trapTwoPointers(height);
        logger.accept(height, water);
        water = trapOptimal(height);
        logger.accept(height, water);
        //
        height = new int[] {4, 2, 0, 3, 2, 5};
        water = trap(height);
        logger.accept(height, water);
        water = trapTwoPointers(height);
        logger.accept(height, water);
        water = trapOptimal(height);
        logger.accept(height, water);
    }

}
