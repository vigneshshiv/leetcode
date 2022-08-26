package code.java.stacks;

import java.util.Objects;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    /**
     * Time complexity: O(n), # of chars in s
     * Space complexity: O(n)
     */
    private static boolean isValid(String str) {
        if (Objects.isNull(str) || str.isEmpty() || str.length() < 2) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (Objects.equals(c, '(') || Objects.equals(c, '[') || Objects.equals(c, '{')) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char last = stack.pop();
                if (Objects.equals(c, ')') && !Objects.equals(last, '(')) {
                    return false;
                }
                if (Objects.equals(c, ']') && !Objects.equals(last, '[')) {
                    return false;
                }
                if (Objects.equals(c, '}') && !Objects.equals(last, '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "()";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
        //
        str = "()[]{}";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
        //
        str = "(]";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
        //
        str = "{{}[][[[]]]}";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
        //
        str = "{{({})}}";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
        //
        str = "{[](";
        System.out.println("Input - " + str + ", isValid - " + isValid(str));
    }

}
