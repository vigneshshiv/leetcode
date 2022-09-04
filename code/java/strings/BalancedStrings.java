package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/
 */
public class BalancedStrings {

    private static int balancedStringSplit(String s) {
        int count = 0, curr = 0;
        for (char c : s.toCharArray()) {
            curr = c == 'L' ? curr + 1 : curr - 1;
            if (curr == 0) {
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (input, count) -> System.out.println("Input - " + input + ", Count - " + count);
        //
        String str = "RLRRLLRLRL";
        int count = balancedStringSplit(str);
        logger.accept(str, count);
        //
        str = "RLRRRLLRLL";
        count = balancedStringSplit(str);
        logger.accept(str, count);
        //
        str = "LLLLRRRR";
        count = balancedStringSplit(str);
        logger.accept(str, count);
    }

}
