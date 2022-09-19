package code.java.trees;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {

    private static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    private static int checkHeight(TreeNode root) {
        if (root == null) return -1;
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (Math.abs(leftHeight - rightHeight) > 1) return Integer.MIN_VALUE;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        boolean isBalanced = isBalanced(root);
        System.out.println("Is Balanced - " + isBalanced);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        isBalanced = isBalanced(root);
        System.out.println("Is Balanced - " + isBalanced);
    }

}
