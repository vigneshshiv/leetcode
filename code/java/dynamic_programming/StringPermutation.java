package code.java.dynamic_programming;

import java.util.*;
import java.util.function.BiConsumer;

public class StringPermutation {

    /**
     * This algorithm runs exponential time.
     *
     * Time complexity: O(n^2 * n!)
     * Space complexity: O(n * n!)
     */
    private static List<String> getStringCombinations(String str) {
        if (Objects.isNull(str)) return null;
        List<String> result = new ArrayList<>();
        if (str.isEmpty()) {
            result.add("");
            return result;
        }
        char first = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = getStringCombinations(remainder);
        for (String word : words) {
            for (int idx = 0; idx <= word.length(); idx++) {
                result.add(insertCharAt(word, first, idx));
            }
        }
        return result;
    }

    private static String insertCharAt(String word, char c, int idx) {
        return word.substring(0, idx) + c + word.substring(idx);
    }

    private static List<String> getStringCombinationsPrefixApproach(String str) {
        List<String> result = new ArrayList<>();
        getCombinations("", str, result);
        return result;
    }

    private static void getCombinations(String prefix, String str, List<String> result) {
        if (str.length() == 0) {
            result.add(prefix);
        }
        int len = str.length();
        for (int idx = 0; idx < len; idx++) {
            String before = str.substring(0, idx);
            String after = str.substring(idx + 1, len);
            char c = str.charAt(idx);
            getCombinations(prefix + c, before + after, result);
        }
    }

    private static List<String> getStringCombinationsWithDupsChar(String str) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> table = buildCharFrequency(str);
        getCombinations("", str.length(), result, table);
        return result;
    }

    private static Map<Character, Integer> buildCharFrequency(String str) {
        Map<Character, Integer> table = new HashMap<>();
        for (char c : str.toCharArray()) {
            table.compute(c, (key, value) -> Objects.nonNull(value) ? value + 1 : 1);
        }
        return table;
    }

    private static void getCombinations(String prefix, int len, List<String> result, Map<Character, Integer> table) {
        if (len == 0) { // Base case permutation has been completed
            result.add(prefix);
            return;
        }
        // Try remaining letters for next char, and generate remaining permutations
        for (Character c : table.keySet()) {
            int count = table.get(c);
            if (count > 0) {
                table.put(c, count - 1);
                getCombinations(prefix + c, len - 1, result, table);
                table.put(c, count);
            }
        }
    }

    public static void main(String[] args) {
        BiConsumer<String, List<String>> logger = (word, combinations) -> System.out.println("Word - " + word + ", Combinations - " + combinations);
        String str = "abcd";
        List<String> result = getStringCombinations(str);
        logger.accept(str, result);
        str = "abc";
        result = getStringCombinationsPrefixApproach(str);
        logger.accept(str, result);
        str = "aabc";
        result = getStringCombinationsWithDupsChar(str);
        logger.accept(str, result);
    }

}
