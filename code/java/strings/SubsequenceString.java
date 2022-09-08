package code.java.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/is-subsequence/
 */
public class SubsequenceString {

    private static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i += 1;
            }
            j += 1;
        }
        return i == s.length();
    }

    private static boolean isSubsequenceOptimal(String s, String t) {
        int idx = -1;
        for (char c : s.toCharArray()) {
            idx = t.indexOf(c, idx + 1);
            if (idx == -1) return false;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/is-subsequence/discuss/87302
     *
     * Eg-1. s="abc", t="bahbgdca"
     *  idx=[a={1,7}, b={0,3}, c={6}]
     *  i=0 ('a'): prev=1
     *  i=1 ('b'): prev=3
     *  i=2 ('c'): prev=6 (return true)
     *
     * Eg-2. s="abc", t="bahgdcb"
     *  idx=[a={1}, b={0,6}, c={5}]
     *  i=0 ('a'): prev=1
     *  i=1 ('b'): prev=6
     *  i=2 ('c'): prev=? (return false)
     */
    private static boolean isSubsequenceOptimalForLargeSets(String s, String t) {
        List<Integer>[] idx = new List[256];
        // Pre-process of t
        int i = 0;
        for (char c : t.toCharArray()) {
            if (idx[c] == null) {
                idx[c] = new ArrayList<>();
            }
            idx[c].add(i++);
        }
        int prev = -1;
        for (char c : s.toCharArray()) {
            if (idx[c] == null) return false;
            prev = binarySearch(idx[c], prev);
            if (prev == -1) return false;
        }
        return true;
    }

    private static int binarySearch(List<Integer> list, int index) {
        int start = 0, mid = 0, end = list.size() - 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (index < list.get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start == list.size() ? -1 : list.get(start) + 1;
    }

    private static boolean isSubsequenceUsingStack(String s, String t) {
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            stack.push(s.charAt(i));
        }
        for (char c : t.toCharArray()) {
            if (stack.isEmpty()) return true;
            if (c == stack.peek()) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (input, isSubsequence) -> {
            System.out.println("S - " + input[0] + ", T - " + input[1] + ", Is Subsequence - " + isSubsequence);
        };
        //
        String s = "ace", t = "abcde";
        boolean isSubsequence = isSubsequence(s, t);
        logger.accept(new String[] {s, t}, isSubsequence);
        //
        s = "aec"; t = "abcde";
        isSubsequence = isSubsequenceUsingStack(s, t);
        logger.accept(new String[] {s, t}, isSubsequence);
        //
        s = "abc"; t = "ahbgdc";
        isSubsequence = isSubsequenceOptimalForLargeSets(s, t);
        logger.accept(new String[] {s, t}, isSubsequence);
        //
        s = "axc"; t = "ahbgdc";
        isSubsequence = isSubsequenceOptimal(s, t);
        logger.accept(new String[] {s, t}, isSubsequence);
    }

}
