package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 */
public class DecryptString {

    private static String freqAlphabets(String s) {
        int idx = s.length() - 1;
        StringBuilder str = new StringBuilder();
        while (idx >= 0) {
            char c = s.charAt(idx);
            if (c == '#') {
                str.append((char) (Integer.valueOf(s.substring(idx - 2, idx)) - 1 + 'a'));
                idx -= 3;
            } else {
                str.append((char) (c - '0' - 1 + 'a'));
                idx -= 1;
            }
        }
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Str - " + input + ", Decrypt String - " + result);
        //
        String str = "10#11#12";
        String result = freqAlphabets(str);
        logger.accept(str, result);
        //
        str = "1326#"; result = freqAlphabets(str);
        logger.accept(str, result);
        //
        str = "12310#13#26#"; result = freqAlphabets(str);
        logger.accept(str, result);
    }

}
