package code.ctci.dynamic_programming;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AllConstruct {

    private static Supplier<List<String>> emptyList = () -> new ArrayList<>();
    private static Supplier<List<List<String>>> empty2DList = () -> new ArrayList<>();

    private static BiFunction<List<List<String>>, String, List<List<String>>> combiner = (suffixWays, target) -> {
        List<List<String>> combinations = empty2DList.get();
        List<String> targetWays = emptyList.get();
        if (Objects.nonNull(suffixWays) && !suffixWays.isEmpty()) {
            combinations.addAll(suffixWays.stream().map(ArrayList::new).collect(Collectors.toList()));
            combinations.stream().map(way -> way.add(target)).collect(Collectors.toList());
        } else {
            targetWays.add(target);
            combinations.add(targetWays);
        }
        return combinations;
    };

    /**
     * Time complexity: O(n^m * m), where n is the length of the word bank, m is target string length
     * Space complexity: O(m^2)
     */
    private static List<List<String>> wordCombinations(String target, String[] wordBank) {
        if (target == "") return empty2DList.get();
        var combinations = empty2DList.get();
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                var suffixWays = wordCombinations(suffix, wordBank);
                if (Objects.nonNull(suffixWays)) {
                    var targetWays = combiner.apply(suffixWays, word);
                    combinations.addAll(targetWays);
                }
            }
        }
        return combinations.isEmpty() ? null : combinations;
    }

    /**
     * Time complexity: O(n^m), where n is the length of the word bank, m is target string length
     * Space complexity: O(m) space - Height of the recursive calls
     */
    private static List<List<String>> wordCombinationsMemo(String target, String[] wordBank, Map<String, List<List<String>>> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == "") return empty2DList.get();
        var combinations = empty2DList.get();
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                var suffix = target.substring(word.length());
                var suffixWays = wordCombinationsMemo(suffix, wordBank, memo);
                if (Objects.nonNull(suffixWays)) {
                    var targetWays = combiner.apply(suffixWays, word);
                    combinations.addAll(targetWays);
                }
            }
        }
        memo.put(target, combinations);
        return combinations.isEmpty() ? null : combinations;
    }

    /**
     * Time complexity: O(n^m), where n is the length of the word bank, m is target string length
     * Space complexity: O(n^m)
     */
    private static List<List<String>> wordCombinationsByTabulation(String target, String[] wordBank) {
        Object[] table = new Object[target.length() + 1];
        table[0] = emptyList.get();
        for (int i = 0; i < table.length; i++) {
            if (Objects.nonNull(table[i])) {
                for (String word : wordBank) {
                    if (i + word.length() <= target.length() && target.substring(i, i + word.length()).indexOf(word) == 0) {
                        List<List<String>> targetWays = combiner.apply((List<List<String>>) table[i], word);
                        if (Objects.nonNull(table[i + word.length()])) {
                            ((List<List<String>>) table[i + word.length()]).addAll(targetWays);
                        } else {
                            table[i + word.length()] = targetWays;
                        }
                    }
                }
            }
        }
        return (List<List<String>>) table[target.length()];
    }

    public static void main(String[] args) {
        BiFunction<String, String[], String> getMsg = (target, wordBank) -> "Target - " + target + ", Input - " + Arrays.toString(wordBank);
        BiConsumer<String, List<List<String>>> logger = (msg, result) ->
                System.out.println(msg + ": " + (Objects.nonNull(result) ? result.toString() : null));
        //
        /*
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinations(target, wordBank));
        //
        target = "abcdef";
        wordBank = new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinations(target, wordBank));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinations(target, wordBank));
        //
        target = "purple";
        wordBank = new String[] {"purp", "p", "ur", "le", "purpl"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinations(target, wordBank));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinations(target, wordBank));
        */
        // Memoization
        /*
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsMemo(target, wordBank, new HashMap<>()));
        //
        target = "abcdef";
        wordBank = new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsMemo(target, wordBank, new HashMap<>()));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsMemo(target, wordBank, new HashMap<>()));
        //
        target = "purple";
        wordBank = new String[] {"purp", "p", "ur", "le", "purpl"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsMemo(target, wordBank, new HashMap<>()));
        //
        // TODO
        target = "eeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        // logger.accept(getMsg.apply(target, wordBank), wordCombinationsMemo(target, wordBank, new HashMap<>()));
        */
        // Tabulation
        String target = "abcdef";
        String[] wordBank = new String[] {"ab", "abc", "cd", "def", "abcd"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsByTabulation(target, wordBank));
        //
        target = "abcdef";
        wordBank = new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsByTabulation(target, wordBank));
        //
        target = "skateboard";
        wordBank = new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsByTabulation(target, wordBank));
        //
        target = "purple";
        wordBank = new String[] {"purp", "p", "ur", "le", "purpl"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsByTabulation(target, wordBank));
        //
        target = "eeeeeeeeeeeeeeeeeeeeeeef";
        wordBank = new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        logger.accept(getMsg.apply(target, wordBank), wordCombinationsByTabulation(target, wordBank));
    }

}
