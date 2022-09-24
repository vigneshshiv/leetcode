package code.java.trie;

import java.util.List;

/**
 * Implements a trie, store input words, finds words with a given prefix tree
 */
public class Trie {

    // The root of this Trie
    private TrieNode root;

    /* Takes a list of strings, and constructs a trie that stores these strings */
    public Trie(List<String> words) {
        root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }
    }

    /* Takes a array of strings, and constructs a trie that stores these strings */
    public Trie(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }
    }

    /* Checks whether a trie contains a string with the prefix passed in as argument */
    public boolean contains(String prefix) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChild(c);
            if (lastNode == null) {
                return false;
            }
        }
        return lastNode.terminates();
    }

    public TrieNode getRoot() {
        return root;
    }

}
