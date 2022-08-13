package code.ctci.dynamic_programming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Paranthesis {

    private static BiFunction<String, Integer, String> insertPair = (str, idx) -> {
        return str.substring(0, idx + 1) + "()" + str.substring(idx + 1, str.length());
    };

    private static Set<String> generateParenthesis(int count) {
        Set<String> combinations = new HashSet<>();
        if (count == 0) {
            combinations.add("");
        } else {
            Set<String> pairs = generateParenthesis(count - 1);
            for (String pair : pairs) {
                for (int idx = 0; idx < pair.length(); idx++) {
                    if (pair.charAt(idx) == '(') {
                        combinations.add(insertPair.apply(pair, idx));
                    }
                }
                combinations.add("()" + pair);
            }
        }
        return combinations;
    }

    private static List<String> generateParenthesisOptimal(int count) {
        List<String> result = new ArrayList<>();
        char[] pairs = new char[count * 2];
        generatePairs(pairs, count, count, 0, result);
        return result;
    }

    private static void generatePairs(char[] pairs, int open, int close, int index, List<String> result) {
        if (open < 0 || close < open) return;
        if (open == 0 && close == 0) {
            result.add(String.valueOf(pairs));
        } else {
            pairs[index] = '(';
            generatePairs(pairs, open - 1, close, index + 1, result);
            // Close it properly at the right index
            pairs[index] = ')';
            generatePairs(pairs, open, close - 1, index + 1, result);
        }
    }

    public static void main(String[] args) {
        Set<String> pairs = generateParenthesis(3);
        System.out.println(pairs);
        List<String> combinations = generateParenthesisOptimal(3);
        System.out.println(combinations);
    }

}
