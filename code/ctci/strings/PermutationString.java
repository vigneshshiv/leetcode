package code.ctci.strings;

import java.util.Objects;

public class PermutationString {

    /**
     * This code runs in O(n!) time - in terms of No. of characters
     */
    static void permutation(String str, String prefix) {
        System.out.println("str - " + str + ", prefix - " + prefix);
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                System.out.println("1st - " + str.substring(0, i) + ", 2nd - " + str.substring(i + 1) + ", Rem - " + rem + ", CharAt(i) - " + str.charAt(i));
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    /**
     * Q? Given two strings, check if one is permutation of the other
     *  OR
     * Check if the two strings have identical character counts
     *
     */
    static boolean identicalStrings(String s, String t) {
        if (Objects.isNull(s) || Objects.isNull(t) || s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[128];
        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // permutation("ABC", "");
        System.out.println("[abc] & [bca] Unique Characters - " + identicalStrings("abc", "bca"));
        System.out.println("[moviee] & [moviie] Unique Characters - " + identicalStrings("moviee", "moviie"));
    }

}
