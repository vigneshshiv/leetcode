package code.java.strings;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/ransom-note/
 */
public class RansomNote {

    /**
     * Time complexity: O(a + b), where a is ransomNote, b is magazine
     * Space complexity: O(b)
     */
    private static boolean canConstruct(String ransomNote, String magazine) {
        if (Objects.isNull(ransomNote) || Objects.isNull(magazine) || ransomNote.isEmpty() || magazine.isEmpty()) {
            return false;
        }
        Map<Character, Integer> frequency = magazine.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
        int char_value = 0;
        for (char ch : ransomNote.toCharArray()) {
            if (!frequency.containsKey(ch)) {
                return false;
            }
            char_value = frequency.get(ch);
            if (char_value < 2) {
                frequency.remove(ch);
            } else {
                frequency.put(ch, char_value - 1);
            }
        }
        return true;
    }

    /**
     * Time complexity: O(a + b)
     * Space complexity: O(1)
     */
    private static boolean canConstructOptimal(String ransomNote, String magazine) {
        if (Objects.isNull(ransomNote) || Objects.isNull(magazine) || ransomNote.isEmpty() || magazine.isEmpty()) {
            return false;
        }
        int[] chars = new int[256];
        for (char ch : magazine.toCharArray()) {
            chars[ch] += 1;
        }
        for (char ch : ransomNote.toCharArray()) {
            if (chars[ch] < 1) {
                return false;
            } else {
                chars[ch] -= 1;
            }
        }
        return true;
    }

    /**
     * Only for alphabets.
     *
     * Time complexity: O(a + b)
     * Space complexity: O(1)
     */
    private static boolean canConstructEasy(String ransomNote, String magazine) {
        if (Objects.isNull(ransomNote) || Objects.isNull(magazine) || ransomNote.isEmpty() || magazine.isEmpty()) {
            return false;
        }
        if (ransomNote.length() > magazine.length()) return false;
        int[] chars = new int[26];
        for (char ch : magazine.toCharArray()) {
            chars[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            if (chars[ch - 'a'] == 0) return false;
            chars[ch - 'a']--;
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
        System.out.println(ransomNote + " can be constructed by " + magazine + "? - " + canConstruct(ransomNote, magazine));
        System.out.println("Optimal: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructOptimal(ransomNote, magazine));
        System.out.println("Easy: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructEasy(ransomNote, magazine));
        System.out.println();
        //
        ransomNote = "aa";
        magazine = "ab";
        System.out.println(ransomNote + " can be constructed by " + magazine + "? - " + canConstruct(ransomNote, magazine));
        System.out.println("Optimal: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructOptimal(ransomNote, magazine));
        System.out.println("Easy: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructEasy(ransomNote, magazine));
        System.out.println();
        //
        ransomNote = "aa";
        magazine = "aab";
        System.out.println(ransomNote + " can be constructed by " + magazine + "? - " + canConstruct(ransomNote, magazine));
        System.out.println("Optimal: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructOptimal(ransomNote, magazine));
        System.out.println("Easy: " + ransomNote + " can be constructed by " + magazine + "? - " + canConstructEasy(ransomNote, magazine));
    }

}
