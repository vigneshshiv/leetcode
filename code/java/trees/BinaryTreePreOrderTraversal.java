package code.java.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreOrderTraversal {

    private static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        result.add(root.data);
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        if (left != null) result.addAll(left);
        if (right != null) result.addAll(right);
        return result;
    }

    private static List<Integer> preOrderTraversalIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.data);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    /**
     * Keeping only rights children on the stack
     *
     * https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45266
     */
    private static List<Integer> preOrderTraversalIterativeOptimal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            // Keeping add top and left node values, while keep traversing left
            result.add(root.data);
            // If Root has right subtree, add it to stack
            if (root.right != null) {
                stack.push(root.right);
            }
            //   1
            //  / \
            // 2   3
            // If 1 & 2 is added to result, Stack is only having 3
            // If current node is 2, the 2's left is null,
            // So Pops out stack top, i.e, current node's (2's) parent (1) right subtree
            root = root.left;
            if (root == null && !stack.isEmpty()) {
                root = stack.pop();
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
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        List<Integer> result = preorderTraversal(root);
        System.out.println("Recursive approach - " + result);
        result = preOrderTraversalIterative(root);
        System.out.println("Iterative approach - " + result);
        result = preOrderTraversalIterativeOptimal(root);
        System.out.println("Iterative approach Optimal - " + result);
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
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = preorderTraversal(root);
        System.out.println("Recursive approach - " + result);
        result = preOrderTraversalIterative(root);
        System.out.println("Iterative approach - " + result);
        result = preOrderTraversalIterativeOptimal(root);
        System.out.println("Iterative approach Optimal - " + result);
    }

}
