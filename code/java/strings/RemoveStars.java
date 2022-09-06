package code.java.strings;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/removing-stars-from-a-string/
 */
public class RemoveStars {

    private static String removeStars(String s) {
        StringBuilder builder = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (c == '*') {
                builder.deleteCharAt(--idx);
            } else {
                builder.insert(idx++, c);
            }
        }
        return builder.toString();
    }

    private static String removeStarsOptimal(String s) {
        int idx = 0;
        char[] chars = new char[s.length()];
        for (char c : s.toCharArray()) {
            if (c == '*') idx--;
            else chars[idx++] = c;
        }
        return new String(chars).substring(0, idx);
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String str = "leet**cod*e";
        String result = removeStarsOptimal(str);
        logger.accept(str, result);
        //
        str = "erase*****";
        result = removeStars(str);
        logger.accept(str, result);
    }

}
