package code.java.dynamic_programming;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

    private static int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        return numWays(s, s.length(), memo);
    }

    private static int numWays(String s, int idx, int[] memo) {
        if (idx == 0) return 1;
        int first = s.length() - idx;
        if (s.charAt(first) == '0') return 0;
        if (memo[idx] > 0) return memo[idx];
        int result = numWays(s, idx - 1, memo);
        if (idx >= 2 && Integer.valueOf(s.substring(first, first + 2)) <= 26) {
            result += numWays(s, idx - 2, memo);
        }
        memo[idx] = result;
        return result;
    }

    private static int nunDecodingsBottomUp(String s) {
        if (s.charAt(0) - '0' == 0) return 0;
        int[] table = new int[s.length() + 1];
        table[0] = 1; table[1] = s.charAt(0) - '0' != 0 ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            int first = s.charAt(i - 1) - '0';
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                table[i] += table[i - 1];
            }
            if (second >= 10 && second <= 26) {
                table[i] += table[i - 2];
            }
        }
        return table[s.length()];
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (str, numWays) -> System.out.println("Str - " + str + ", No of Ways - " + numWays);
        //
        String s = "12";
        int noOfWays = numDecodings(s);
        logger.accept(s, noOfWays);
        //
        s = "06"; noOfWays = numDecodings(s);
        logger.accept(s, noOfWays);
        //
        s = "226"; noOfWays = nunDecodingsBottomUp(s);
        logger.accept(s, noOfWays);
    }

}
