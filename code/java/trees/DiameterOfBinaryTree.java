package code.java.trees;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

    static int max = 0;

    private static int diameterOfBinaryTree(TreeNode root) {
        max = 0; // Local Testing
        maxDepth(root);
        return max;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        // Right
        root.right = new TreeNode(3);
        //
        int diameter = diameterOfBinaryTree(root);
        System.out.println("Diameter of binary tree - " + diameter);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        diameter = diameterOfBinaryTree(root);
        System.out.println("Diameter of binary tree - " + diameter);
    }

}
