package code.ctci.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class CanConstruct {

    /**
     * Time complexity: O(n^m * m), where n is the length of the word bank and m is target string length
     * Space complexity: O(m^2) space
     */
    private static boolean constructWord(String target, String[] wordBank) {
        if (target == "") return true;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                if (constructWord(suffix, wordBank)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the word bank, m is target string length
     * Space complexity: O(m^2) space
     */
    private static boolean constructWordMemo(String target, String[] wordBank, Map<String, Boolean> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == "") return true;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                if (constructWordMemo(suffix, wordBank, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }

    public static void main(String[] args) {
        BiFunction<String, String[], String> getMsg = (target, wordBank) -> "Target - " + target + ", Input - " + Arrays.toString(wordBank);
        BiConsumer<String, Boolean> logger = (msg, result) -> System.out.println(msg + ": " + result);
        //
        /*
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), constructWord(target, wordBank));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), constructWord(target, wordBank));
        //
        target = "enterpotentpot";
        wordBank = new String[] {"a", "p", "ent", "enter", "ot", "o", "t"};
        logger.accept(getMsg.apply(target, wordBank), constructWord(target, wordBank));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), constructWord(target, wordBank));
        */
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), constructWordMemo(target, wordBank, new HashMap<>()));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), constructWordMemo(target, wordBank, new HashMap<>()));
        //
        target = "enterpotentpot";
        wordBank = new String[] {"a", "p", "ent", "enter", "ot", "o", "t"};
        logger.accept(getMsg.apply(target, wordBank), constructWordMemo(target, wordBank, new HashMap<>()));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), constructWordMemo(target, wordBank, new HashMap<>()));
    }

}
