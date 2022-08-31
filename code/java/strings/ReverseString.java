package code.java.strings;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/reverse-string/
 *
 * https://leetcode.com/problems/reverse-string-ii/
 *
 */
public class ReverseString {

    /**
     * In-Place reverse by changing the position of a string or char array
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static void reverseString(char[] s) {
        if (Objects.isNull(s) || s.length <= 1) {
            return;
        }
        int i = 0, j = s.length - 1;
        while (i < j) {
            char c = s[i];
            s[i++] = s[j];
            s[j--] = c;
        }
    }

    /**
     * Reverse only K characters
     */
    private static String reverseKCharactersInString(String str, int k) {
        if (k < 2 || Objects.isNull(str) || str.length() < 2) {
            return str;
        }
        if (str.length() <= k) {
            return reverseString(str);
        }
        char[] chars = str.toCharArray();
        int char_length = chars.length - 1;
        //
        for (int idx = 0; idx < char_length; idx += (2 * k)) {
            int i = idx, j = (idx + k) > char_length ? char_length : idx + k - 1;
            while (i < j) {
                char c = chars[i];
                chars[i++] = chars[j];
                chars[j--] = c;
            }
        }
        return new String(chars);
    }

    /**
     * Util method for the above
     */
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
        BiConsumer<char[], char[]> char_logger = (input, result) -> {
            System.out.println("Char Array Reverse: Input - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        BiConsumer<String[], String> str_k_logger = (input, result) -> {
            System.out.println("String K Reverse: Input String - " + input[0] + ", K - " + input[1] + ", Result - " + result);
        };
        //
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        char[] input = arr.clone();
        reverseString(arr);
        char_logger.accept(input, arr);
        //
        arr = new char[] {'p', 'e', 'r', 's', 'o', 'n'};
        reverseString(arr);
        char_logger.accept(input, arr);
        System.out.println();
        //
        String str = "abcdef";
        int k = 2;
        String result = reverseKCharactersInString(str, k);
        str_k_logger.accept(new String[] {str, String.valueOf(k)}, result);
        //
        str = "abcde";
        result = reverseKCharactersInString(str, k);
        str_k_logger.accept(new String[] {str, String.valueOf(k)}, result);
        //
        str = "abcdefgh";
        k = 3;
        result = reverseKCharactersInString(str, k);
        str_k_logger.accept(new String[] {str, String.valueOf(k)}, result);
    }

}
