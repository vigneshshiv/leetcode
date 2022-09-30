package code.java.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class PhoneLetterPad {

    private static Supplier<List<String>> emptyList = () -> new ArrayList<>();

    /**
     * n - # of digits length
     * Time complexity: (n^n + 2n) // Exponential
     * Space complexity: O(n^n)
     */
    private static List<String> letterCombinations(String prefix, String digits) {
        List<String> result = emptyList.get();
        if (digits.isEmpty()) {
            result.add(prefix);
            return result;
        }
        int digit = (int) digits.charAt(0) - '0';
        int start = digit > 7 ? (digit - 1) * 3 - 2 : (digit - 2) * 3;
        int end = digit == 9 ? (digit * 3) - 1 : digit > 6 ? (digit * 3) - 2 : (digit - 1) * 3;
        for (int i = start; i < end; i++) {
            char ch = (char) ('a' + i);
            result.addAll(letterCombinations(prefix + ch, digits.substring(1)));
        }
        return result;
    }

    private static Map<Character, String> digitToChar = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );

    /**
     * https://github.com/neetcode-gh/leetcode/blob/main/java/17-Letter-Combinations-of-a-Phone-Number.java
     */
    private static List<String> letterCombinationsEasy(String prefix, String digits) {
        if (digits.length() == 0) {
            return emptyList.get();
        }
        List<String> result = new ArrayList();
        String cur = "";
        backtrack(digits, result, cur, 0);
        return result;
    }

    private static void backtrack(String digits, List<String> result, String cur, int index) {
        if (cur.length() == digits.length()) {
            result.add(cur);
            return;
        } else if (index >= digits.length()) {
            return;
        } else {
            String digit = digitToChar.get(digits.charAt(index));
            for (char c : digit.toCharArray()) {
                backtrack(digits, result, cur + c, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "7";
        List<String> result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "23";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "456";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        result = letterCombinationsEasy("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "78";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "89";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "79";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = "492";
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
        //
        digits = ""; // Base case to return [] not [""]
        result = letterCombinations("", digits);
        System.out.println("Digits - " + digits + ", Combinations - " + result);
    }

}
