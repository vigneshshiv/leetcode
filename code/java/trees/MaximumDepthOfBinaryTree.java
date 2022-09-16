package code.java.trees;

import java.util.List;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
        int depth = root.height();
        System.out.println("Height - " + depth);
        depth = maxDepth(root);
        System.out.println("Depth - " + depth);
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
        depth = root.height();
        System.out.println("Height - " + depth);
        depth = maxDepth(root);
        System.out.println("Depth - " + depth);
    }

}
