package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/count-asterisks/
 */
public class CountAsterisks {

    private static int countAsterisks(String s) {
        int count = 0, pipes = 0;
        for (char c : s.toCharArray()) {
            pipes += c == '|' ? 1 : 0;
            count += c == '*' && pipes % 2 == 0 ? 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (input, count) -> System.out.println("Input - " + input + ", Count - " + count);
        //
        String str = "l|*e*et|c**o|*de|";
        int count = countAsterisks(str);
        logger.accept(str, count);
        //
        str = "iamprogrammer";
        count = countAsterisks(str);
        logger.accept(str, count);
        //
        str = "yo|uar|e**|b|e***au|tifu|l";
        count = countAsterisks(str);
        logger.accept(str, count);
        //
        str = "|**|*|*|**||***||";
        count = countAsterisks(str);
        logger.accept(str, count);
    }

}
