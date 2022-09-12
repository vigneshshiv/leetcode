package code.java.sliding_window;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {

    private static boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;
        int[] chars = new int[26];
        for (char c : s1.toCharArray()) {
            chars[c - 'a']++;
        }
        int i = 0, j = 0;
        while (j < s2.length()) {
            chars[s2.charAt(j) - 'a']--;
            int range = j - i + 1;
            if (range == len1) {
                if (stringsAreEqual(chars)) return true;
                chars[s2.charAt(i) - 'a']++;
                i += 1; j += 1;
            } else if (range < len1) {
                j += 1;
            }
        }
        return false;
    }

    private static boolean checkInclusionOptimal(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;
        int[] chars = new int[26];
        for (int idx = 0; idx < len1; idx++) {
            chars[s1.charAt(idx) - 'a']++;
            chars[s2.charAt(idx) - 'a']--;
        }
        if (stringsAreEqual(chars)) return true;
        for (int idx = len1; idx < len2; idx++) {
            chars[s2.charAt(idx) - 'a']--;
            chars[s2.charAt(idx - len1) - 'a']++;
            if (stringsAreEqual(chars)) return true;
        }
        return false;
    }

    private static boolean stringsAreEqual(int[] chars) {
        for (int idx = 0; idx < 26; idx++) {
            if (chars[idx] != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (input, result) -> {
            System.out.println("S1 - " + input[0] + ", S2 - " + input[1] + ", Result - " + result);
        };
        //
        String s1 = "ab", s2 = "eidbaooo";
        boolean result = checkInclusion(s1, s2);
        logger.accept(new String[]{s1, s2}, result);
        //
        s1 = "ab"; s2 = "eidboaoo";
        result = checkInclusionOptimal(s1, s2);
        logger.accept(new String[]{s1, s2}, result);
    }

}
