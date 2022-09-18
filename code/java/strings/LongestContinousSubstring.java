package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/
 */
public class LongestContinousSubstring {

    private static int longestContinuousSubstring(String s) {
        int sequence = 1, result = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            sequence = (chars[i] == chars[i - 1] + 1) ? sequence + 1 : 1;
            result = Math.max(result, sequence);
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (str, result) -> System.out.println("Str - " + str + ", Longest Sequence - " + result);
        //
        String s = "abacaba";
        int result = longestContinuousSubstring(s);
        logger.accept(s, result);
        //
        s = "abcde";
        result = longestContinuousSubstring(s);
        logger.accept(s, result);
    }

}
