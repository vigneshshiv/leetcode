package code.ctci.strings;

public class StringEditor {

    /**
     * There are three types of edits that can be performed on strings: insert a character,
     * remove a character, or replace a character. Given two strings, write a function to check if they are
     * one edit (or zero edits) away.
     *
     * pale, ple - true
     * pales, pale - true
     * pale, bale - true
     * pale, bake - false
     *
     * Time: O(n) time
     * Space: O(1) space
     */
    static boolean oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() == second.length() + 1) {
            return oneEditInsert(second, first);
        }
        return true;
    }

    /**
     * One Edit Replace
     */
    static boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    /**
     * One Edit Insert
     */
    static boolean oneEditInsert(String s1, String s2) {
        int idx1 = 0, idx2 = 0;
        while (idx2 < s2.length() && idx1 < s1.length()) {
            if (s1.charAt(idx1) != s2.charAt(idx2)) {
                if (idx1 != idx2) {
                    return false;
                }
                idx2++;
            } else {
                idx1++;
                idx2++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is Strings [pale] & [ple] one edit away - " + oneEditAway("pale", "ple"));
        System.out.println("Is Strings [pales] & [pale] one edit away - " + oneEditAway("pales", "pale"));
        System.out.println("Is Strings [pale] & [bale] one edit away - " + oneEditAway("pale", "bale"));
        System.out.println("Is Strings [pale] & [bae] one edit away - " + oneEditAway("pale", "bae"));
    }

}
