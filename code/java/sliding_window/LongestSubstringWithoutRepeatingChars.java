package code.java.sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingChars {

    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int max = 0;
        for (int idx = 0; idx < s.length() - 1; idx++) {
            int[] store = new int[128];
            int i = idx, j = idx + 1;
            while (j < s.length() && s.charAt(i) != s.charAt(j)) {
                if (store[s.charAt(j)] > 0) break;
                store[s.charAt(j)]++;
                j++;
            }
            max = Math.max(max, j - i);
        }
        return max;
    }

    private static int lengthOfLongestSubstringOptimal(String s) {
        if (s == null || s.length() == 0) return 0;
        int i = 0, j = 0, max = 0;
        Set<Character> store = new HashSet<>();
        while (j < s.length()) {
            if (store.add(s.charAt(j))) {
                max = Math.max(max, store.size());
                j += 1;
            } else {
                store.remove(s.charAt(i++));
            }
        }
        return max;
    }

    private static int lengthOfLongestSubstringOptional(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 0;
        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (table.containsKey(s.charAt(j))) {
                i = Math.max(i, table.get(s.charAt(j)) + 1);
            }
            table.put(s.charAt(j), j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (str, length) -> System.out.println("Str - " + str + ", Length - " + length);
        //
        String str = "abcabcbb";
        int length = lengthOfLongestSubstringOptimal(str);
        logger.accept(str, length);
        //
        str = "bbbbb";
        length = lengthOfLongestSubstringOptimal(str);
        logger.accept(str, length);
        //
        str = "pwwkew";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = "pw";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = "x";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = "";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = "  ";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = "  $sd ";
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
        //
        str = null;
        length = lengthOfLongestSubstring(str);
        logger.accept(str, length);
    }

}
