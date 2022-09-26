package code.java.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
 */
public class LongestPalindromeTwoWords {

    private static int longestPalindrome(String[] words) {
        int unpaired = 0, result = 0;
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.putIfAbsent(word, 0);
            if (word.charAt(0) == word.charAt(1)) { // Both char is same
                if (frequency.get(word) > 0) {
                    result += 4;
                    unpaired -= 1;
                    frequency.merge(word, -1, Integer::sum);
                } else {
                    frequency.merge(word, 1, Integer::sum);
                    unpaired += 1;
                }
            } else {
                String rev = word.charAt(1) + "" + word.charAt(0);
                if (frequency.containsKey(rev) && frequency.get(rev) > 0) {
                    result += 4;
                    frequency.merge(rev, -1, Integer::sum);
                } else {
                    frequency.merge(word, 1, Integer::sum);
                }
            }
        }
        if (unpaired > 0) {
            result += 2;
        }
        return result;
    }

    private static int longestPalindromeOptimal(String[] words) {
        int[][] frequency = new int[26][26];
        int result = 0;
        for (String word : words) {
            int a = word.charAt(0) - 'a', b = word.charAt(1) - 'a';
            if (frequency[b][a] > 0) {
                result += 4;
                frequency[b][a]--;
            } else {
                frequency[a][b]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (frequency[i][i] > 0) {
                result += 2;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Integer> logger = (words, result) -> {
            System.out.println("Words - " + Arrays.toString(words) + ", Longest Palindrome Length - " + result);
        };
        //
        String[] words = {"lc", "cl", "gg"};
        int result = longestPalindrome(words);
        logger.accept(words, result);
        result = longestPalindromeOptimal(words);
        logger.accept(words, result);
        //
        words = new String[] {"ab", "ty", "yt", "lc", "cl", "ab"};
        result = longestPalindrome(words);
        logger.accept(words, result);
        result = longestPalindromeOptimal(words);
        logger.accept(words, result);
        //
        words = new String[] {"cc", "ll", "xx"};
        result = longestPalindrome(words);
        logger.accept(words, result);
        result = longestPalindromeOptimal(words);
        logger.accept(words, result);
    }

}
