package code.java.strings;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/reverse-only-letters/
 */
public class ReverseLetters {

    /**
     * Reverse only letters
     */
    private static String reverseOnlyLetters(String str) {
        if (Objects.isNull(str) || str.length() <= 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (Character.toLowerCase(chars[i]) - 'a' < 0 || Character.toLowerCase(chars[i]) - 'a' > 25) {
                i += 1;
            } else if (Character.toLowerCase(chars[j]) - 'a' < 0 || Character.toLowerCase(chars[j]) - 'a' > 25) {
                j -= 1;
            } else {
                char c = chars[i];
                chars[i++] = chars[j];
                chars[j--] = c;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        BiConsumer<String, String> str_logger = (input, result) -> {
            System.out.println("String Reverse: Input - " + input + ", Result - " + result);
        };
        //
        String str = "abcdef";
        String result = reverseOnlyLetters(str);
        str_logger.accept(str, result);
        //
        str = "ab-cd";
        result = reverseOnlyLetters(str);
        str_logger.accept(str, result);
        //
        str = "a-bC-dEf-ghIj";
        result = reverseOnlyLetters(str);
        str_logger.accept(str, result);
        //
        str = "Test1ng-Leet=code-Q!";
        result = reverseOnlyLetters(str);
        str_logger.accept(str, result);
    }

}
