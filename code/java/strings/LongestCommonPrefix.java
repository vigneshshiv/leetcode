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

    private static String longestCommonPrefixOptimal(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (!strs[i].startsWith(pre))
                pre = pre.substring(0, pre.length() - 1);
        return pre;
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
