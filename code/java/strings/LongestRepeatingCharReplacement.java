package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharReplacement {

    private static int characterReplacement(String s, int k) {
        // Max_length the longest subsequence without repeating chars and k changes
        // Max_count is the high count chars in the answer subsequence
        int max_length = 0, max_count = 0;
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'A'] += 1;
            // Find the new max_count. This is much like Kadane's
            // Where we only consider if the new length exceeds the max_length overall
            max_count = Math.max(max_count, chars[s.charAt(i) - 'A']);
            // The answer is always max_count + k
            if (max_length < k + max_count) {
                max_length += 1;
            } else {
                // This removes the char at the start of the subsequence s[i - max_length]
                // This serves as "correction" for the subsequence problem
                chars[s.charAt(i - max_length) - 'A'] -= 1;
            }
        }
        return max_length;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Integer> logger = (input, result) -> {
            System.out.println("Str - " + input[0] + ", K - " + input[1] + ", Result - " + result);
        };
        //
        String s = "ABAB";
        int k = 2;
        int result = characterReplacement(s, k);
        logger.accept(new String[] {s, String.valueOf(k)}, result);
        //
        s = "AABABBA"; k = 1;
        result = characterReplacement(s, k);
        logger.accept(new String[] {s, String.valueOf(k)}, result);
    }

}
