package code.java.graphs;

import code.java.trees.TreeNode;

import java.util.*;

public class BSTSequence {

    /**
     * Time complexity: O(nm)
     * Space complexity: O(nm)
     */
    private static List<LinkedList<Integer>> allSequences(TreeNode node) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (Objects.isNull(node)) {
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);
        // Recurse on left and right subtrees
        List<LinkedList<Integer>> leftSequences = allSequences(node.left);
        List<LinkedList<Integer>> rightSequences = allSequences(node.right);
        // Weave (merge) together each list from left and right sides
        for (LinkedList<Integer> left : leftSequences) {
            for (LinkedList<Integer> right : rightSequences) {
                List<LinkedList<Integer>> weavedLists = new ArrayList<>();
                weaveLists(left, right, weavedLists, prefix);
                result.addAll(weavedLists);
            }
        }
        return result;
    }

    private static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                                   List<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        // If one list is empty and add cloned prefix and the rest
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        // Recurse with head of first added to the prefix. Put it back afterwards
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);
        // Do the same thing with second and restore it later
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    public static void main(String[] args) {
        // int[] array = {100, 50, 20, 75, 150, 120, 170};
        int[] array = {1, 3};
        TreeNode root = new TreeNode(2);
        Arrays.stream(array).forEach(value -> root.insertInOrder(value));
        //
        List<LinkedList<Integer>> sequenceList = allSequences(root);
        System.out.println("Sequence List size - " + sequenceList.size());
        sequenceList.stream().forEach(System.out::println);
    }

}
