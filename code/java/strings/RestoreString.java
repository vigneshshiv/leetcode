package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/shuffle-string/
 */
public class RestoreString {

    private static String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        for (int idx = 0; idx < indices.length; idx++) {
            chars[indices[idx]] = s.charAt(idx);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        BiConsumer<String, String> str_logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String str = "codeleet";
        int[] indices = {4, 5, 6, 7, 0, 2, 1, 3};
        String result = restoreString(str, indices);
        str_logger.accept(str, result);
        //
        str = "abc";
        indices = new int[] {0, 1, 2};
        result = restoreString(str, indices);
        str_logger.accept(str, result);

    }

}
