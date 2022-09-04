package code.java.strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/sorting-the-sentence/
 */
public class SortSentence {

    private static String sortSentence(String s) {
        Comparator<String> ascOrder = (s1, s2) -> s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
        String[] words = s.split(" ");
        Arrays.sort(words, ascOrder);
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            sentence.append(word.substring(0, word.length() - 1));
            if (i < words.length - 1) {
                sentence.append(" ");
            }
        }
        return sentence.toString();
    }

    private static String sortSentenceOptimal(String s) {
        String[] words = s.split(" ");
        String[] result = new String[words.length];
        for (String word : words) {
            int idx = (int) word.charAt(word.length() - 1) - '0';
            result[idx - 1] = word.substring(0, word.length() - 1);
        }
        return Arrays.stream(result).map(word -> word + " ").collect(Collectors.joining()).strip();
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String str = "is2 sentence4 This1 a3";
        String result = sortSentence(str);
        logger.accept(str, result);
        //
        str = "Myself2 Me1 I4 and3";
        result = sortSentenceOptimal(str);
        logger.accept(str, result);
    }

}
