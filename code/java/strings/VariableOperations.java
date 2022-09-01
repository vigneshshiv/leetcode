package code.java.strings;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
 */
public class VariableOperations {

    private static int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (String s : operations) {
            x = s.charAt(1) == '+' ? x + 1 : x - 1;
        }
        return x;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Integer> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Result - " + result);
        };
        //
        String[] input = {"--X", "X++", "X++"};
        int result = finalValueAfterOperations(input);
        logger.accept(input, result);
        //
        input = new String[] {"++X", "++X", "X++"};
        result = finalValueAfterOperations(input);
        logger.accept(input, result);
        //
        input = new String[] {"X++", "++X", "--X", "X--"};
        result = finalValueAfterOperations(input);
        logger.accept(input, result);
    }

}
