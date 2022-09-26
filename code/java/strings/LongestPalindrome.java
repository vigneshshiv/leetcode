package code.java.strings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-palindrome/
 */
public class LongestPalindrome {

    private static int longestPalindrome(String s) {
        if (s.length() == 1) return 1;
        int count = 0;
        Set<Character> store = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!store.add(c)) {
                count += 2;
                store.remove(c);
            }
        }
        return !store.isEmpty() ? count + 1 : count;
    }

    private static int longestPalindromeOptimal(String s) {
        if (s.length() == 1) return 1;
        int count = 0;
        int[] chars = new int[52];
        for (char c : s.toCharArray()) {
            if (c >= 97) chars[c - 'a' + 26]++;
            else chars[c - 'A']++;
        }
        for (int num : chars) {
            count += (num / 2) * 2;
        }
        return count == s.length() ? count : count + 1;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (str, result) -> System.out.println("S - " + str + ", Longest Palindrome - " + result);
        //
        String str = "abccccdd";
        int result = longestPalindromeOptimal(str);
        logger.accept(str, result);
        //
        str = "a";
        result = longestPalindrome(str);
        logger.accept(str, result);
        //
        str = "a";
        result = longestPalindrome(str);
        logger.accept(str, result);
        //
        str = "aA";
        result = longestPalindrome(str);
        logger.accept(str, result);
    }

}
