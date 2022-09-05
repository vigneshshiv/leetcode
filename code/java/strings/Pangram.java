package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 */
public class Pangram {

    private static boolean checkIfPangram(String sentence) {
        boolean[] chars = new boolean[26];
        for (char c : sentence.toCharArray()) {
            chars[c - 'a'] = true;
        }
        for (boolean occurance : chars) {
            if (!occurance) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BiConsumer<String, Boolean> logger = (sentence, isPangram) -> {
            System.out.println("Sentence - " + sentence + ", Pangram - " + isPangram);
        };
        //
        String sentence = "thequickbrownfoxjumpsoverthelazydog";
        boolean isPangram = checkIfPangram(sentence);
        logger.accept(sentence, isPangram);
        //
        sentence = "leetcode";
        isPangram = checkIfPangram(sentence);
        logger.accept(sentence, isPangram);
    }

}
