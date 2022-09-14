package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/find-the-difference/
 */
public class FindTheDifference {

    private static char findTheDifference(String s, String t) {
        if (s == null || s.length() == 0) return t.charAt(0);
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            chars[c - 'a']--;
            if (chars[c - 'a'] < 0) return c;
        }
        return '\0';
    }

    public static void main(String[] args) {
        BiConsumer<String[], Character> logger = (input, ch) -> System.out.println("S - " + input[0] + ", T - " + input[1] + ", Char - " + ch);
        //
        String s = "abcd", t = "abcde";
        char ch = findTheDifference(s, t);
        logger.accept(new String[]{s, t}, ch);
        //
        s = ""; t = "y";
        ch = findTheDifference(s, t);
        logger.accept(new String[]{s, t}, ch);
        //
        s = "asdfasdfasdfasdfasf"; t = "asdfasdfasdfasdfasfu";
        ch = findTheDifference(s, t);
        logger.accept(new String[]{s, t}, ch);
        //
        s = "asdfasdfasdfasdfasf"; t = "asdfasdfasdfasdfasff";
        ch = findTheDifference(s, t);
        logger.accept(new String[]{s, t}, ch);
    }

}
