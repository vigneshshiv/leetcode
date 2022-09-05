package code.java.strings;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/count-the-number-of-consistent-strings/
 */
public class CountConsistentStrings {

    private static int countConsistentStrings(String allowed, String[] words) {
        int count = 0;
        Set<Character> table = allowed.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        for (String word : words) {
            boolean allSet = true;
            for (char c : word.toCharArray()) {
                if (!table.contains(c))
                    allSet = false;
            }
            if (allSet) {
                count += 1;
            }
        }
        return count;
    }

    private static int countConsistentStringsOneLine(String allowed, String[] words) {
        return Arrays.stream(words) //
                .mapToInt(word -> word.chars().allMatch(c -> allowed.contains(String.valueOf((char) c))) ? 1 : 0) //
                .sum();
    }

    public static void main(String[] args) {
        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};
        int count = countConsistentStrings(allowed, words);
        System.out.println("Count - " + count);
        //
        allowed = "abc";
        words = new String[] {"a", "b", "c", "ab", "ac", "bc", "abc"};
        count = countConsistentStrings(allowed, words);
        System.out.println("Count - " + count);
        //
        allowed = "cad";
        words = new String[] {"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};
        count = countConsistentStringsOneLine(allowed, words);
        System.out.println("Count - " + count);
    }

}
