package code.java.strings;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 */
public class ArrayStringsCheck {

    private static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        String s1 = Arrays.stream(word1).collect(Collectors.joining());
        String s2 = Arrays.stream(word2).collect(Collectors.joining());
        return Objects.equals(s1, s2);
    }

    /**
     * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/discuss/967572
     */
    private static boolean arrayStringsAreEqualOptimal(String[] word1, String[] word2) {
        int p1 = 0, p2 = 0, w1 = 0, w2 = 0;
        while (w1 < word1.length && w2 < word2.length) {
            String s1 = word1[w1], s2 = word2[w2];
            if (s1.charAt(p1) != s2.charAt(p2)) return false;
            if (p1 < s1.length() - 1) {
                p1++;
            } else {
                p1 = 0;
                w1++;
            }
            if (p2 < s2.length() - 1) {
                p2++;
            } else {
                p2 = 0;
                w2++;
            }
        }
        return w1 == word1.length && w2 == word2.length;
    }

    public static void main(String[] args) {
        BiConsumer<String[][], Boolean> logger = (words, isEqual) -> {
            System.out.println("Word1 - " + Arrays.toString(words[0]) + ", Word2 - " + Arrays.toString(words[1]) + ", IsEqual - " + isEqual);
        };
        //
        String[] word1 = {"ab", "c"}, word2 = {"a", "bc"};
        boolean isEqual = arrayStringsAreEqual(word1, word2);
        logger.accept(new String[][] {word1, word2}, isEqual);
        //
        word1 = new String[] {"a", "cb"};
        word2 = new String[] {"ab", "c"};
        isEqual = arrayStringsAreEqual(word1, word2);
        logger.accept(new String[][] {word1, word2}, isEqual);
        //
        word1 = new String[] {"abc", "d", "defg"};
        word2 = new String[] {"abcddefg"};
        isEqual = arrayStringsAreEqual(word1, word2);
        logger.accept(new String[][] {word1, word2}, isEqual);
    }

}
