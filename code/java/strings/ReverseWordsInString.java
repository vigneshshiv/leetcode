package code.java.strings;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 */
public class ReverseWordsInString {

    private static String reverseWords(String str) {
        if (Objects.isNull(str) || str.length() < 2) {
            return str;
        }
        String[] words = str.trim().split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].trim().isEmpty()) {
                builder.append(words[i]);
                if (i > 0) {
                    builder.append(" ");
                }
            }
        }
        return builder.toString();
    }

    private static String reverseWordsIII(String str) {
        if (Objects.isNull(str) || str.length() < 2) {
            return str;
        }
        String[] words = str.trim().split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].trim().isEmpty()) {
                builder.append(reverseString(words[i]));
                if (i < words.length - 1) {
                    builder.append(" ");
                }
            }
        }
        return builder.toString();
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

    private static String reverseWordsIIIOptimal(String str) {
        if (Objects.isNull(str) || str.length() < 2) {
            return str;
        }
        return Arrays.stream(str.split("\\s")) //
                .map(s -> new StringBuilder(s).reverse().toString()) //
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        BiConsumer<String, String> word_reverse_logger = (input, result) -> {
            System.out.println("Words Reverse By space : Input String - " + input + ", Result - " + result);
        };
        BiConsumer<String, String> word_reverse_loggerIII = (input, result) -> {
            System.out.println("Words Reverse By space : Input String - " + input + ", Result - " + result);
        };
        //
        String str = "the sky is blue";
        String result = reverseWords(str);
        word_reverse_logger.accept(str, result);
        //
        str = "  hello world  ";
        result = reverseWords(str);
        word_reverse_logger.accept(str, result);
        //
        str = "a good   example";
        result = reverseWords(str);
        word_reverse_logger.accept(str, result);
        System.out.println();
        //
        str = "Let's take LeetCode contest";
        result = reverseWordsIII(str);
        word_reverse_loggerIII.accept(str, result);
        result = reverseWordsIIIOptimal(str);
        word_reverse_loggerIII.accept(str, result);
        //
        str = "God Ding";
        result = reverseWordsIII(str);
        word_reverse_loggerIII.accept(str, result);
    }

}
