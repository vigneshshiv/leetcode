package code.java.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Node in the Trie, Most of the logic is implemented here
 */
public class TrieNode {
    /* Nodes or Children of Trie */
    private Map<Character, TrieNode> nodes;
    private boolean terminates = false;

    // The character stored in this node as data
    private char character;

    public TrieNode() {
        nodes = new HashMap<>();
    }

    /* Constructs a Trie node and initializes of child nodes with an empty map */
    public TrieNode(char character) {
        this();
        this.character = character;
    }

    /* Returns the character data stored in this node */
    public char getChar() {
        return character;
    }

    /* Add this word to trie and builds a char tree (notion) */
    public void addWord(String word) {
        if (word == null || word.isEmpty()) return;
        char firstChar = word.charAt(0);
        TrieNode node = getChild(firstChar);
        if (node == null) {
            node = new TrieNode(firstChar);
            nodes.put(firstChar, node);
        }
        if (word.length() > 1) {
            node.addWord(word.substring(1));
        } else {
            node.setTerminates(true);
        }
    }

    /* Find and return child node of this character */
    public TrieNode getChild(char c) {
        return nodes.get(c);
    }

    /* Returns true if map contains no key-value pair */
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    /* Return whether this node represents the end of a complete word */
    public boolean terminates() {
        return terminates;
    }

    /* Set this node is the end of a complete word */
    public void setTerminates(boolean terminate) {
        this.terminates = terminate;
    }

}
