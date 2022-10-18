package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

    private static boolean isPalindrome(String s) {
        int n = s.length();
        if (n == 1) return true;
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < j && !isAlphaNumeric(s.charAt(i))) {
                i += 1;
            }
            while (j > i && !isAlphaNumeric(s.charAt(j))) {
                j -= 1;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i += 1; j -= 1;
        }
        return true;
    }

    private static boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    private static boolean isPalindromeOptimal(String s) {
        char[] chars = new char[256];
        for (int i = 0; i < 10; i++) {
            chars['0' + i] = (char) (1 + i);
        }
        for (int i = 0; i < 26; i++) {
            chars['a' + i] = chars['A' + i] = (char) (11 + i);
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && chars[s.charAt(i)] == 0) {
                i += 1;
            }
            while (j > i && chars[s.charAt(j)] == 0) {
                j -= 1;
            }
            if (chars[s.charAt(i)] != chars[s.charAt(j)]) return false;
            i += 1; j -= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<String, Boolean> logger = (input, result) -> System.out.println("Input - " + input + ", Valid Palindrome - " + result);
        //
        String s = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome(s);
        logger.accept(s, result);
        result = isPalindromeOptimal(s);
        logger.accept(s, result);
        //
        s = "race a car";
        result = isPalindrome(s);
        logger.accept(s, result);
        result = isPalindromeOptimal(s);
        logger.accept(s, result);
        //
        s = " ";
        result = isPalindrome(s);
        logger.accept(s, result);
        result = isPalindromeOptimal(s);
        logger.accept(s, result);
        //
        s = "0P";
        result = isPalindrome(s);
        logger.accept(s, result);
        result = isPalindromeOptimal(s);
        logger.accept(s, result);
    }

}
