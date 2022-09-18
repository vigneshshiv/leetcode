package code.java.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {

    private static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);
        return root;
    }

    private static TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        // Right
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        invertTreeIterative(root);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        invertTree(root);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
    }

}
