package code.java.strings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

    private static String decodeStringInitialTry(String s) {
        int i = s.length() - 1, close = 0;
        String k = "", str = "";
        StringBuilder decoded = new StringBuilder();
        //
        while (i >= 0) {
            char c = s.charAt(i);
            if (c == ']') {
                close += 1;
                if (!str.isEmpty()) {
                    decoded = new StringBuilder(str + decoded);
                    str = "";
                }
            }
            else if (c == '[') close -= 1;
            else if (!Character.isDigit(c)) {
                str = c + str;
                if (i == 0 && !str.isEmpty()) {
                    decoded = new StringBuilder(str + decoded);
                    str = "";
                }
            } else {
                do {
                    k = c + k; // K times
                    i -= 1;
                    if (i < 0) break;
                    c = s.charAt(i);
                } while (i >= 0 && Character.isDigit(c));
                // Actual logic of repeating
                if (!str.isEmpty() && !k.isEmpty()) {
                    String repeated = str.repeat(Integer.valueOf(k));
                    // close == 0 - Final decoded string, otherwise keeps str and append new values
                    if (close == 0) {
                        decoded = new StringBuilder(repeated + decoded);
                        str = "";
                    } else {
                        str = repeated;
                    }
                    k = "";
                }
                continue;
            }
            i -= 1;
        }
        return decoded.toString();
    }

    private static String decodeString(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        return decode(queue);
    }

    private static String decode(Deque<Character> queue) {
        StringBuilder builder = new StringBuilder();
        int k = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                String str = decode(queue);
                for ( ; k > 0; k--) builder.append(str);
            } else if (c == ']') {
                break;
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    // Solution by Stack Approach -- https://leetcode.com/problems/decode-string/discuss/87534
    private static String decodeStringByStack(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder str = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; k--) {
                    cur.append(str);
                }
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String s = "3[a]2[bc]";
        String result = decodeString(s);
        logger.accept(s, result);
        //
        s = "3[a2[c]]";
        result = decodeString(s);
        logger.accept(s, result);
        //
        s = "2[abc]3[cd]ef";
        result = decodeString(s);
        logger.accept(s, result);
        //
        s = "abc3[cd]xyz";
        result = decodeString(s);
        logger.accept(s, result);
        //
        s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        result = decodeString(s);
        logger.accept(s, result);
    }

}
