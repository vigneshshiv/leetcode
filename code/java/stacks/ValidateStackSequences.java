package code.java.stacks;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/validate-stack-sequences/
 */
public class ValidateStackSequences {

    /**
     * Time complexity: O(n) time
     * Space complexity: O(n) space
     */
    private static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * In-place approach, modified input
     *
     * Time complexity: O(n) time
     * Space complexity: O(n) modified space (Not O(1) extra space)
     */
    private static boolean validateStackSequencesInPlace(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i -= 1; j += 1;
            }
        }
        return i == 0;
    }

    public static void main(String[] args) {
        BiConsumer<int[][], Boolean> logger = (input, result) -> {
            System.out.println("Pushed - " + Arrays.toString(input[0]) + ", Popped - " + Arrays.toString(input[1]) + ", Valid - " + result);
        };
        //
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};
        boolean result = validateStackSequences(pushed, popped);
        logger.accept(new int[][]{pushed, popped}, result);
        result = validateStackSequencesInPlace(pushed, popped);
        logger.accept(new int[][]{pushed, popped}, result);
        //
        pushed = new int[] {1, 2, 3, 4, 5}; popped = new int[] {4, 3, 5, 1, 2};
        result = validateStackSequences(pushed, popped);
        logger.accept(new int[][]{pushed, popped}, result);
        result = validateStackSequencesInPlace(pushed, popped);
        logger.accept(new int[][]{pushed, popped}, result);
    }

}
