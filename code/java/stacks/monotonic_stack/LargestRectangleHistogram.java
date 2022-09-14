package code.java.stacks.monotonic_stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleHistogram {

    private static int largestRectangleArea(int[] heights) {
        int area = 0;
        Stack<Integer> stack = new Stack<>();
        for (int idx = 0; idx <= heights.length; idx++) {
            int height = (idx == heights.length) ? 0 : heights[idx];
            while (!stack.isEmpty() && heights[stack.peek()] > height) {
                int last = heights[stack.pop()];
                int width = last * (stack.isEmpty() ? idx : idx - stack.peek() - 1);
                area = Math.max(area, width);
            }
            stack.push(idx);
        }
        return area;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, result) -> {
            System.out.println("Heights - " + Arrays.toString(input) + ", Largest Rectangle Area - " + result);
        };
        //
        int[] heights = {2, 1, 5, 6, 2, 3};
        int largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
        //
        heights = new int[] {2, 4};
        largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
        //
        heights = new int[] {6, 2, 5, 4, 5, 1, 6};
        largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
        //
        heights = new int[] {1, 1};
        largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
        //
        heights = new int[] {2, 3};
        largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
        //
        heights = new int[] {2, 1, 2};
        largestRectangleArea = largestRectangleArea(heights);
        logger.accept(heights, largestRectangleArea);
    }

}
