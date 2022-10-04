package code.java.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostOrderTraversal {

    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.data);
        return result;
    }

    /**
     * Sample binary tree
     *
     *    1
     *   / \
     *  2   3
     *
     */
    private static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        // Reference for last visited right node, for when parent is on top of the stack
        TreeNode last = null;
        while (root != null || !stack.isEmpty()) {
            // Keep pushing left nodes, all the way down onto stack
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.peek();
                // When Parent is on top stack, it checks with right node which has a refence in last variable
                // If both are same, it will not add repeated reference onto stack
                // Pops out stack top, i.e parent node, and level up higher for other nodes.
                if (node.right != null && node.right != last) {
                    root = node.right;
                } else {
                    // If any of the right node is empty, the block executes and add value from top of stack
                    result.add(node.data);
                    // Pops out stock top
                    last = stack.pop();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        // Level Order - 1 - 3 5 - 4 2 - 7 8
        TreeNode.printPostOrderTraversal(root);
        System.out.println();
        List<Integer> result = postorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = postorderTraversalIterative(root);
        System.out.println("Iterative - " + result);
        System.out.println();
        //
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(9);
        root.left.left.right = new TreeNode(10);
        root.left.left.left.left = new TreeNode(17);
        root.left.left.left.right = new TreeNode(21);
        root.left.left.right.right = new TreeNode(20);
        root.left.right.left = new TreeNode(11);
        root.left.right.right = new TreeNode(12);
        root.left.right.right.left = new TreeNode(15);
        root.left.right.right.right = new TreeNode(18);
        // Right
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(17);
        root.right.left.right = new TreeNode(69);
        root.right.right.left = new TreeNode(48);
        root.right.right.right = new TreeNode(50);
        root.right.right.left.left = new TreeNode(54);
        // Level Order - 1 - 3 5 - 6 7 4 8 - 9 10 11 12 17 69 48 50 - 17 21 20 15 18 54
        TreeNode.printPostOrderTraversal(root);
        System.out.println();
        result = postorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = postorderTraversalIterative(root);
        System.out.println("Iterative - " + result);
    }

}
