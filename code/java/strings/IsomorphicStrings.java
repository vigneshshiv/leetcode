package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 */
public class IsomorphicStrings {

    private static boolean isIsomorphic(String s, String t) {
        int[] table = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i)] != table[t.charAt(i) + 128]) {
                return false;
            }
            table[s.charAt(i)] = table[t.charAt(i) + 128] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (input, isIsomorphic) -> {
            System.out.println("s - " + input[0] + ", t - " + input[1] + ", IsIsomorphic - " + isIsomorphic);
        };
        //
        String s = "egg", t = "add";
        boolean isIsomorphic = isIsomorphic(s, t);
        logger.accept(new String[] {s, t}, isIsomorphic);
        //
        s = "foo"; t = "bar";
        isIsomorphic = isIsomorphic(s, t);
        logger.accept(new String[] {s, t}, isIsomorphic);
        //
        s = "paper"; t = "title";
        isIsomorphic = isIsomorphic(s, t);
        logger.accept(new String[] {s, t}, isIsomorphic);
        //
        s = "badc"; t = "baba";
        isIsomorphic = isIsomorphic(s, t);
        logger.accept(new String[] {s, t}, isIsomorphic);
    }

}
