package code.java.trie;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * https://leetcode.com/submissions/detail/810441384/
 */
public class PrefixTrieTree {

    // The root of this Trie
    private TrieNode root;

    public PrefixTrieTree() {
        root = new TrieNode('\0'); // Forms as Root
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (current.nodes[idx] == null) {
                current.nodes[idx] = new TrieNode(c);
            }
            current = current.nodes[idx];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return contains(word, false);
    }

    public boolean startsWith(String prefix) {
        return contains(prefix, true);
    }

    private boolean contains(String prefix, boolean startsWith) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            current = current.nodes[c - 'a'];
            if (current == null) {
                return false;
            }
        }
        return startsWith ? true : current.isWord;
    }

    public static void main(String[] args) {
        PrefixTrieTree trie = new PrefixTrieTree();
        trie.insert("apple");
        System.out.println("Search apple - " + trie.search("apple"));
        System.out.println("Search app - " + trie.search("app"));
        System.out.println("Starts with app - " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("Search app - " + trie.search("app"));
    }

    /**
     * Node in the Trie, Most of the logic is implemented here
     */
    private class TrieNode {
        private char character;
        private TrieNode[] nodes = new TrieNode[26];
        private boolean isWord;

        public TrieNode(char character) {
            this.character = character;
        }
    }

}
