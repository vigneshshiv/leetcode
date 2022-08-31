package code.java.strings;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class ReverseVowels {

    private static String reverseVowels(String str) {
        if (Objects.isNull(str) || str.length() == 1) {
            return str;
        }
        int i = 0, j = str.length() - 1;
        char[] chars = str.toCharArray();
        while (i < j) {
            if (!isVowel(Character.toLowerCase(chars[i]))) {
                i += 1;
            } else if (!isVowel(Character.toLowerCase(chars[j]))) {
                j -= 1;
            } else {
                char c = chars[i];
                chars[i++] = chars[j];
                chars[j--] = c;
            }
        }
        return new String(chars);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        /*
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
        */
    }

    public static void main(String[] args) {
        BiConsumer<String, String> str_logger = (input, result) -> {
            System.out.println("String Vowels Reverse: Input - " + input + ", Result - " + result);
        };
        //
        String str = "A man, a plan, a canal: Panama";
        String result = reverseVowels(str);
        str_logger.accept(str, result);
        //
        str = "leetcode";
        result = reverseVowels(str);
        str_logger.accept(str, result);
    }

}
