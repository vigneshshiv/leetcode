package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/remove-outermost-parentheses/
 */
public class RemoveOuterParentheses {

    private static String removeOuterParentheses(String s) {
        int idx = 0;
        int depth = 0;
        char[] pairs = new char[s.length()];
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (depth > 0) pairs[idx++] = c;
                depth++;
            } else {
                if (depth > 1) pairs[idx++] = c;
                depth--;
            }
        }
        return new String(pairs).substring(0, idx);
    }

    private static String removeOuterParenthesesStrBuilder(String s) {
        int depth = 0;
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (depth > 0) builder.append(c);
                depth++;
            } else {
                if (depth > 1) builder.append(c);
                depth--;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String str = "(()())(())";
        String result = removeOuterParentheses(str);
        logger.accept(str, result);
        //
        str = "(()())(())(()(()))"; result = removeOuterParenthesesStrBuilder(str);
        logger.accept(str, result);
        //
        str = "()()"; result = removeOuterParentheses(str);
        logger.accept(str, result);
    }

}
