package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 */
public class ParenthesesDepth {

    private static int maxDepth(String s) {
        int mx = 0, d = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') d++;
            else if (c == ')') d--;
            mx = Math.max(mx, d);
        }
        return mx;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (input, depth) -> System.out.println("Input - " + input + ", Max Depth - " + depth);
        //
        String str = "(1+(2*3)+((8)/4))+1";
        int depth = maxDepth(str);
        logger.accept(str, depth);
        //
        str = "(1)+((2))+(((3)))";
        depth = maxDepth(str);
        logger.accept(str, depth);
    }

}
