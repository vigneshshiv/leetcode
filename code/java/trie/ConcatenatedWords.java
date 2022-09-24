package code.java.trie;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {

    /**
     * n - # of words
     * s - The highest length of the word
     * Sorting: O(n log(n))
     * Substring: O(n)
     *
     * Time complexity: O(n log(n) + n * s^3) -> O(n * s^3)
     * Space complexity: O(n + n) -> O(2n) -> O(n)
     */
    private static List<String> findAllConcatenatedWordsInADictInitialTry(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> store = new HashSet<>();
        Arrays.sort(words, Comparator.comparingInt(String::length)); // Optional
        for (String word : words) {
            if (canForm(word, store)) {
                result.add(word);
            }
            store.add(word);
        }
        return result;
    }

    private static boolean canForm(String word, Set<String> store) {
        if (store.isEmpty()) return false;
        boolean[] startsWith = new boolean[word.length() + 1];
        startsWith[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (startsWith[j] && store.contains(word.substring(j, i))) {
                    startsWith[i] = true;
                    break;
                }
            }
        }
        return startsWith[word.length()];
    }

    /**
     * Optimal solution
     */
    private static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> store = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (hasWord(word, store)) result.add(word);
        }
        return result;
    }

    private static boolean hasWord(String word, Set<String> store) {
        for (int i = 1; i < word.length(); i++) {
            if (store.contains(word.substring(0, i))) {
                String suffix = word.substring(i);
                if (store.contains(suffix) || hasWord(suffix, store)) {
                    store.add(suffix); // memoization - concatenated word for look up at later stage
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> findAllConcatenatedWordsInADictByTrie(String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildsTrie(words);
        for (String word : words) {
            if (canForm(root, word, 0, word.length() - 1)) {
                result.add(word);
            }
        }
        return result;
    }

    private static TrieNode buildsTrie(String[] words) {
        TrieNode root = new TrieNode('\0'); // Forms as Root
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (current.nodes[idx] == null) {
                    current.nodes[idx] = new TrieNode(c);
                }
                current = current.nodes[idx];
            }
            if (current != null) {
                current.setWordCompletion(true);
            }
        }
        return root;
    }

    private static boolean canForm(TrieNode root, String word, int start, int end) {
        TrieNode current = root;
        for (int i = start; i <= end; i++) {
            char c = word.charAt(i);
            current = current.nodes[c - 'a'];
            if (current == null) return false;
            if (current.isCompleteWord()) { // Prefix
                if (hasWord(root, word, i + 1, end) || canForm(root, word, i + 1, end)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasWord(TrieNode root, String word, int start, int end) {
        TrieNode current = root;
        for (int i = start; i <= end; i++) {
            char c = word.charAt(i);
            current = current.nodes[c - 'a'];
            if (current == null) return false;
        }
        return current.isCompleteWord();
    }

    public static void main(String[] args) {
        BiConsumer<String[], List<String>> logger = (words, result) -> {
            System.out.println("Words - " + Arrays.toString(words) + ", Result - " + result);
        };
        //
        String[] words = {"cat", "dog", "catdog"};
        List<String> result = findAllConcatenatedWordsInADictInitialTry(words);
        logger.accept(words, result);
        result = findAllConcatenatedWordsInADict(words);
        logger.accept(words, result);
        // Trie
        System.out.println("Trie approach below");
        result = findAllConcatenatedWordsInADictByTrie(words);
        logger.accept(words, result);
        //
        words = new String[] {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        result = findAllConcatenatedWordsInADictInitialTry(words);
        logger.accept(words, result);
        result = findAllConcatenatedWordsInADict(words);
        logger.accept(words, result);
        // Trie
        System.out.println("Trie approach below");
        result = findAllConcatenatedWordsInADictByTrie(words); // Not working
        logger.accept(words, result);
    }

    private static class TrieNode {
        private char character;
        private TrieNode[] nodes = new TrieNode[26];
        private boolean completeWord;

        public TrieNode(char character) {
            this.character = character;
        }

        public void setWordCompletion(boolean complete) {
            this.completeWord = complete;
        }

        public boolean isCompleteWord() {
            return completeWord;
        }

    }

}
