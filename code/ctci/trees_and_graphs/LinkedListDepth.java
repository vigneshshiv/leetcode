package code.ctci.trees_and_graphs;

import code.ctci.utils.MethodsUtility;

import java.util.*;

public class LinkedListDepth {

    /**
     * DFS - Recursive
     *
     * Time complexity: O(n) time
     * Space complexity: O(log n) space but since it requires returning O(N) data so space is dwarfed by O(n)
     */
    private static void traverseLevelsByDFS(List<LinkedList<TreeNode>> listNodes, TreeNode root, int level) {
        if (Objects.isNull(root)) return;
        LinkedList<TreeNode> levelNode = null;
        if (Objects.equals(listNodes.size(), level)) {
            levelNode = new LinkedList<>();
            listNodes.add(levelNode);
        } else {
            levelNode = listNodes.get(level);
        }
        levelNode.add(root);
        traverseLevelsByDFS(listNodes, root.left, level + 1);
        traverseLevelsByDFS(listNodes, root.right, level + 1);
    }

    /**
     * BFS - Iterative
     *
     * Time complexity: O(n) time
     * Space complexity: O(n) space
     */
    private static List<LinkedList<TreeNode>> iterateLevelsByBFS(TreeNode root) {
        if (Objects.isNull(root)) return null;
        List<LinkedList<TreeNode>> listNodes = new ArrayList<>();
        LinkedList<TreeNode> current = new LinkedList<>();
        current.add(root);
        while (!current.isEmpty()) {
            listNodes.add(current); // Add previous level
            LinkedList<TreeNode> parents = current; // Go to next level
            current = new LinkedList<>();
            for (TreeNode parent : parents) {
                // Visit left and right nodes
                if (Objects.nonNull(parent.left)) {
                    current.add(parent.left);
                }
                if (Objects.nonNull(parent.right)) {
                    current.add(parent.right);
                }
            }
        }
        return listNodes;
    }

    /**
     * Create LinkedList for each level by DFS
     */
    private static void createLevelByLevelLinkedListNodesByDFS(TreeNode root) {
        List<LinkedList<TreeNode>> listNodes = new ArrayList<>();
        traverseLevelsByDFS(listNodes, root, 0);
        // Print
        System.out.println("Print Result for DFS");
        printResult(listNodes);
    }

    /**
     * Create LinkedList for each level by BFS
     */
    private static void createLevelByLevelLinkedListNodesByBFS(TreeNode root) {
        List<LinkedList<TreeNode>> listNodes = iterateLevelsByBFS(root);
        // Print
        System.out.println("Print Result for BFS");
        printResult(listNodes);
    }

    /**
     * Print Result
     */
    private static void printResult(List<LinkedList<TreeNode>> listNodes) {
        int depth = 0;
        for (LinkedList<TreeNode> levelNode : listNodes) {
            Iterator<TreeNode> iterator = levelNode.listIterator();
            System.out.print("Link list at depth " + depth + ":");
            while (iterator.hasNext()) {
                System.out.print(" " + iterator.next().data);
            }
            System.out.println();
            depth++;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = MethodsUtility.createTreeFromArray(array);
        // Pre-Order Traversal
        System.out.print("In-Order Traversal -- ");
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        // DFS
        createLevelByLevelLinkedListNodesByDFS(root);
        // BFS
        root = MethodsUtility.createTreeFromArray(new int[] {2, 5, 6, 8, 9, 7, 4, 10, 49, 42});
        createLevelByLevelLinkedListNodesByBFS(root);
    }

}
