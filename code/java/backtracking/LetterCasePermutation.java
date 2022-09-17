package code.java.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {

    private static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        permutation(result, s.toCharArray(), 0);
        return result;
    }

    private static void permutation(List<String> result, char[] chars, int idx) {
        if (idx == chars.length) {
            result.add(new String(chars));
            return;
        }
        permutation(result, chars, idx + 1);
        if (Character.isLetter(chars[idx])) {
            chars[idx] ^= (1 << 5);
            permutation(result, chars, idx + 1);
            chars[idx] ^= (1 << 5);
        }
    }

    public static void main(String[] args) {
        BiConsumer<String, List<String>> logger = (str, result) -> System.out.println("Str - " + str + ", Result - " + result);
        //
        String s = "a1b2";
        List<String> result = letterCasePermutation(s);
        logger.accept(s, result);
        //
        s = "3z4";
        result = letterCasePermutation(s);
        logger.accept(s, result);
        //
        s = "C";
        result = letterCasePermutation(s);
        logger.accept(s, result);
    }

}
