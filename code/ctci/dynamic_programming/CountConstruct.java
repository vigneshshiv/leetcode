package code.ctci.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Write a function `countConstruct(target, wordBank)` that accepts a target string and an array of strings.
 *
 * The function should return the number of ways that the `target` can be constructed by concatenating elements of the `wordBank` array.
 *
 * You may reuse elements of `wordBank` as many times as needed.
 */
public class CountConstruct {

    /**
     * Time complexity: O(n^m * m), where n is the length of the word bank and m is target string
     * Space complexity: O(m^2)
     */
    private static int findNoOfWays(String target, String[] wordBank) {
        if (target == "") return 1;
        var count = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                var noOfWays = findNoOfWays(suffix, wordBank);
                count += noOfWays;
            }
        }
        return count;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the word bank and m is target string
     * Space complexity: O(m^2)
     */
    private static int findNoOfWaysMemo(String target, String[] wordBank, Map<String, Integer> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == "") return 1;
        var count = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                var noOfWays = findNoOfWaysMemo(suffix, wordBank, memo);
                count += noOfWays;
            }
        }
        memo.put(target, count);
        return count;
    }

    public static void main(String[] args) {
        BiFunction<String, String[], String> getMsg = (target, wordBank) -> "Target - " + target + ", Input - " + Arrays.toString(wordBank);
        BiConsumer<String, Integer> logger = (msg, result) -> System.out.println(msg + ": " + result);
        //
        /*
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWays(target, wordBank));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWays(target, wordBank));
        //
        target = "enterpotentpot";
        wordBank = new String[] {"a", "p", "ent", "enter", "ot", "o", "t"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWays(target, wordBank));
        //
        target = "purple";
        wordBank = new String[] {"purp", "p", "ur", "le", "purpl"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWays(target, wordBank));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWays(target, wordBank));
        */
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWaysMemo(target, wordBank, new HashMap<>()));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWaysMemo(target, wordBank, new HashMap<>()));
        //
        target = "enterpotentpot";
        wordBank = new String[] {"a", "p", "ent", "enter", "ot", "o", "t"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWaysMemo(target, wordBank, new HashMap<>()));
        //
        target = "purple";
        wordBank = new String[] {"purp", "p", "ur", "le", "purpl"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWaysMemo(target, wordBank, new HashMap<>()));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), findNoOfWaysMemo(target, wordBank, new HashMap<>()));
    }

}
