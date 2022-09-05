package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/to-lower-case/
 */
public class ToLowerCase {

    private static String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('A' <= chars[i] && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] - 'A' + 'a');
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Lowercase - " + result);
        //
        String str = "Hello";
        String result = toLowerCase(str);
        logger.accept(str, result);
        //
        str = "here";
        result = toLowerCase(str);
        logger.accept(str, result);
        //
        str = "LOVELY";
        result = toLowerCase(str);
        logger.accept(str, result);
    }

}
