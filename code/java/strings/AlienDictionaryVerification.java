package code.java.strings;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class AlienDictionaryVerification {

    private static boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) return true;
        int[] store = getOrder(order);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int j = i + 1;
            while (j < words.length) {
                if (!compareWords(word, words[j], store)) {
                    return false;
                }
                j += 1;
            }
        }
        return true;
    }

    private static boolean compareWords(String word1, String word2, int[] order) {
        if (word1.equals(word2)) return true;
        if (word1.startsWith(word2) && word1.length() > word2.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < word1.length() && j < word2.length()) {
            char c1 = word1.charAt(i++), c2 = word2.charAt(j++);
            if (c1 != c2) {
                if (order[c1 - 'a'] > order[c2 - 'a']) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    private static int[] getOrder(String order) {
        int[] store = new int[26];
        for (int i = 0; i < order.length(); i++) {
            store[order.charAt(i) - 'a'] = i;
        }
        return store;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (words, isSorted) -> System.out.println(Arrays.toString(words) + ", Is Sorted - " + isSorted);
        //
        String[] words = {
                "hello", "leetcode"
        };
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean stringsInOrder = isAlienSorted(words, order);
        logger.accept(words, stringsInOrder);
        //
        words = new String[] {
                "word", "world", "row"
        };
        order = "worldabcefghijkmnpqstuvxyz";
        stringsInOrder = isAlienSorted(words, order);
        logger.accept(words, stringsInOrder);
        //
        words = new String[] {
                "apple", "app"
        };
        order = "abcdefghijklmnopqrstuvwxyz";
        stringsInOrder = isAlienSorted(words, order);
        logger.accept(words, stringsInOrder);
        //
        words = new String[] {
                "app", "apple"
        };
        order = "abcdefghijklmnopqrstuvwxyz";
        stringsInOrder = isAlienSorted(words, order);
        logger.accept(words, stringsInOrder);
    }

}
