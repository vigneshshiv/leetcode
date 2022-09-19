package code.java.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 */
public class SearchInBinarySearchTree {

    private static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.data == val) return root;
        return searchBST(val < root.data ? root.left : root.right, val);
    }

    private static TreeNode searchBSTIterative(TreeNode root, int val) {
        if (root == null || root.data == val) return root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(val < root.data ? root.left : root.right);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.data == val) return node;
                stack.push(val < node.data ? node.left : node.right);
            }
        }
        return null;
    }

    private static TreeNode searchBSTIterativeOptimal(TreeNode root, int val) {
        while (root != null && root.data != val) {
            root = val < root.data ? root.left : root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        TreeNode result = searchBST(root, 2);
        TreeNode.printPreOrderTraversal(result);
        System.out.println();
        result = searchBSTIterativeOptimal(root, 2);
        TreeNode.printPreOrderTraversal(result);
        System.out.println();
        //
        result = searchBST(root, 5);
        TreeNode.printPreOrderTraversal(result);
        System.out.println();
        result = searchBSTIterative(root, 5);
        TreeNode.printPreOrderTraversal(result);
    }

}
