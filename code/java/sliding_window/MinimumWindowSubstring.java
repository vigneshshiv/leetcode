package code.java.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    private static String minWindow(String s, String t) {
        String empty = "";
        int m = s.length(), n = t.length();
        // If String T length is greater than S, then all of T chars can't fit in S, so return ""
        if (n > m) return empty;
        // If S & T equals, then that's the min
        if (m == n && s.equals(t)) return t;
        // Build T character hashmap and counts
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // i - refers start position usually left
        int i = 0, start = -1, matched = 0, min = s.length() + 1;
        for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                map.merge(c, -1, Integer::sum);
                // If map character matches 0, then found valid char, increase matched by 1
                if (map.get(c) == 0) {
                    matched += 1;
                }
            }
            // If matched equals all of T's character, then find minimum window
            while (matched == map.size()) {
                if (min > j - i + 1) {
                    min = j - i + 1;
                    start = i;
                }
                char del = s.charAt(i++);
                if (map.containsKey(del)) {
                    // If we are seeing one of T's del char then increase count by 1
                    // If the char count is 0, then decrement matched by 1 (reason count will be increment by 1)
                    if (map.get(del) == 0) {
                        matched -= 1;
                    }
                    map.merge(del, 1, Integer::sum);
                }
            }
        }
        return start == -1 ? empty : s.substring(start, start + min);
    }

    /**
     * Fastest
     *
     * Time complexity: O(n)
     * Space complexity: O(1), map of 128 chars is considered as a constant
     */
    private static String minWindowASCII(String s, String t) {
        String empty = "";
        int m = s.length(), n = t.length();
        // If String T length is greater than S, then all of T chars can't fit in S, so return ""
        if (n > m) return empty;
        // If S & T equals, then that's the min
        if (m == n && s.equals(t)) return t;
        // Build T character hashmap and counts
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c] += 1;
        }
        // i - refers start position usually left
        int i = 0, start = -1, matched = 0, min = s.length() + 1;
        for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            if (map[c]-- > 0) {
                matched += 1;
            }
            while (matched == n) {
                if (min > j - i + 1) {
                    min = j - i + 1;
                    start = i;
                }
                char del = s.charAt(i++);
                if (map[del]++ >= 0) {
                    matched -= 1;
                }
            }
        }
        return start == -1 ? empty : s.substring(start, start + min);
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (input, result) -> {
            System.out.println("S - " + input[0] + ", T - " + input[1] + ", Result - " + result);
        };
        //
        String s = "ADOBECODEBANC", t = "ABC";
        String result = minWindowASCII(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "a"; t = "a";
        result = minWindow(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "a"; t = "aa";
        result = minWindow(s, t);
        logger.accept(new String[] {s, t}, result);
    }

}
