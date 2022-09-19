package code.java.trees;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {

    private static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        //
        int depth = root.height();
        System.out.println("Height - " + depth);
        depth = minDepth(root);
        System.out.println("Minimum Depth - " + depth);
        System.out.println();
        //
        root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        //
        depth = root.height();
        System.out.println("Height - " + depth);
        depth = minDepth(root);
        System.out.println("Minimum Depth - " + depth);
    }

}
