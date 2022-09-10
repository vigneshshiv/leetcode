package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

    private static boolean isAnagram(String s, String t) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            chars[c - 'a']--;
            if (chars[c - 'a'] < 0) return false;
        }
        for (int num : chars) {
            if (num != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (input, result) -> {
            System.out.println("S - " + input[0] + ", T - " + input[1] + ", is anagram? - " + result);
        };
        //
        String s = "anagram", t = "nagaram";
        boolean result = isAnagram(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "rat"; t = "car";
        result = isAnagram(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "s"; t = "t";
        result = isAnagram(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "asdfasdfasdfasdfasdfasfassssssssssssssssssssssssssssssssss"; t = "i";
        result = isAnagram(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "asssssssbsssssssssssssssssssssssscs"; t = "sssssssscssssssssssassssssssssbssss";
        result = isAnagram(s, t);
        logger.accept(new String[] {s, t}, result);
    }

}
