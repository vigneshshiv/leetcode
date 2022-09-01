package code.java.strings;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/
 */
public class SentenceWordCount {

    private static int mostWordsFound(String[] sentences) {
        int count = 0;
        for (String str : sentences) {
            count = Math.max(count, str.split(" ").length);
        }
        return count;
    }

    private static int mostWordsFoundOneLine(String[] sentences) {
        return Stream.of(sentences).mapToInt(str -> str.split(" ").length).max().getAsInt();
    }

    public static void main(String[] args) {
        BiConsumer<String[], Integer> words_logger = (sentences, count) -> {
            System.out.println("Sentences - " + Arrays.toString(sentences) + ", Max Count - " + count);
        };
        //
        String[] sentences = {"alice and bob love leetcode", "i think so too", "this is great thanks very much"};
        int count = mostWordsFound(sentences);
        words_logger.accept(sentences, count);
        count = mostWordsFoundOneLine(sentences);
        words_logger.accept(sentences, count);
        //
        sentences = new String[] {"please wait", "continue to fight", "continue to win"};
        count = mostWordsFound(sentences);
        words_logger.accept(sentences, count);
        count = mostWordsFoundOneLine(sentences);
        words_logger.accept(sentences, count);
    }

}
