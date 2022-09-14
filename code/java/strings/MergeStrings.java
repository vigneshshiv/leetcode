package code.java.strings;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/merge-strings-alternately/
 */
public class MergeStrings {

    private static String mergeAlternately(String word1, String word2) {
        if (word1.length() == 1) {
            return word1 + word2;
        }
        int i = 0, j = 0, idx = 0;
        char[] chars = new char[word1.length() + word2.length()];
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                chars[idx++] = word1.charAt(i++);
            }
            if (j < word2.length()) {
                chars[idx++] = word2.charAt(j++);
            }
        }
        return new String(chars);
    }

    private static String mergeAlternatelyOptimal(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 1) {
            return word1 + word2;
        }
        int i = 0, j = 0, idx = 0;
        char[] chars = new char[len1 + len2];
        while (idx < chars.length) {
            if (i < len1) chars[idx++] = word1.charAt(i++);
            if (j < len2) chars[idx++] = word2.charAt(j++);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> str_logger = (input, result) -> {
            System.out.println("Merge Alternatively : word1 - " + input[0] + ", word2 - " + input[1] + ", Result - " + result);
        };
        //
        String word1 = "abc", word2 = "pqr";
        String result = mergeAlternately(word1, word2);
        str_logger.accept(new String[] {word1, word2}, result);
        //
        word1 = "ab";
        word2 = "pqrs";
        result = mergeAlternately(word1, word2);
        str_logger.accept(new String[] {word1, word2}, result);
        //
        word1 = "abcd";
        word2 = "pq";
        result = mergeAlternatelyOptimal(word1, word2);
        str_logger.accept(new String[] {word1, word2}, result);
        //
        word1 = "Ia";
        word2 = " m doing so well";
        result = mergeAlternately(word1, word2);
        str_logger.accept(new String[] {word1, word2}, result);
        //
        word1 = "U";
        word2 = " are Great!";
        result = mergeAlternately(word1, word2);
        str_logger.accept(new String[] {word1, word2}, result);
    }

}
