package code.java.strings;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    private static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (char c : strs[0].toCharArray()) {
            boolean matched = true;
            for (int i = 1; i < strs.length; i++) {
                String word = strs[i];
                if (idx >= word.length() || (idx < word.length() && c != word.charAt(idx))) {
                    matched = false;
                    break;
                }
            }
            if (matched) sb.append(c);
            else break;
            idx += 1;
        }
        return sb.toString();
    }

    private static String longestCommonPrefixSorting(String[] strs) {
        int n = strs.length;
        if (n == 0) return "";
        // Sorting provides lexicographic order of strings.
        Arrays.sort(strs);
        String first = strs[0], last = strs[n - 1];
        int i = 0;
        while (i < first.length()) {
            if (first.charAt(i) == last.charAt(i)) {
                i += 1;
            } else {
                break;
            }
        }
        return i == 0 ? "" : first.substring(0, i);
    }

    /**
     * Time complexity: O(n * s), n - # of elements in the array, s - length of the substring
     * Space complexity: O(1) space
     */
    private static String longestCommonPrefixOptimal(String[] strs) {
        if (strs.length == 0) return "";
        String word = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // Keep minimize the prefix word (previous) which match starting position with the current word.
            while (!strs[i].startsWith(word)) {
                word = word.substring(0, word.length() - 1);
            }
            // If word has no matching prefix with the current word, then there is no common prefix,
            // which implies there won't be any common prefix in the subsequent non-processed words.
            if ("".equals(word)) {
                return "";
            }
        }
        return word;
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (strs, result) -> {
            System.out.println("Input - " + Arrays.toString(strs) + ", Result - " + result);
        };
        //
        String[] strs = {"flower", "flow", "flight"};
        String result = longestCommonPrefix(strs);
        logger.accept(strs, result);
        result = longestCommonPrefixOptimal(strs);
        logger.accept(strs, result);
        //
        strs = new String[] {"flower", "flow", "flo"};
        result = longestCommonPrefix(strs);
        logger.accept(strs, result);
        //
        strs = new String[] {"dog", "racecar", "car"};
        result = longestCommonPrefix(strs);
        logger.accept(strs, result);
        //
        strs = new String[] {"cir", "car"};
        result = longestCommonPrefix(strs);
        logger.accept(strs, result);
    }

}
