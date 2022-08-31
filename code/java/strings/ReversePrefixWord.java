package code.java.strings;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/reverse-prefix-of-word/
 */
public class ReversePrefixWord {

    /**
     * Reverse only prefix
     */
    private static String reversePrefix(String word, char ch) {
        if (Objects.isNull(word) || word.length() < 2
                || word.startsWith(String.valueOf(ch)) || !word.contains(String.valueOf(ch))) {
            return word;
        }
        int pos = word.indexOf(ch) + 1;
        return reverseString(word.substring(0, pos)) + word.substring(pos);
    }

    private static String reverseString(String str) {
        int i = 0, j = str.length() - 1;
        char[] chars = str.toCharArray();
        while (i < j) {
            char c = chars[i];
            chars[i++] = chars[j];
            chars[j--] = c;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> word_prefix_char_logger = (input, result) -> {
            System.out.println("Word Prefix char Reverse: Input String - " + input[0] + ", Char - " + input[1] + ", Result - " + result);
        };
        //
        String str = "abcdefd";
        char ch = 'd';
        String result = reversePrefix(str, ch);
        word_prefix_char_logger.accept(new String[] {str, String.valueOf(ch)}, result);
        //
        str = "xyxzxe";
        ch = 'z';
        result = reversePrefix(str, ch);
        word_prefix_char_logger.accept(new String[] {str, String.valueOf(ch)}, result);
        //
        str = "xaydxzxe";
        ch = 'a';
        result = reversePrefix(str, ch);
        word_prefix_char_logger.accept(new String[] {str, String.valueOf(ch)}, result);
        //
        str = "abcd";
        ch = 'z';
        result = reversePrefix(str, ch);
        word_prefix_char_logger.accept(new String[] {str, String.valueOf(ch)}, result);
    }

}
