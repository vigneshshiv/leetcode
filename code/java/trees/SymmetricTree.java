package code.java.trees;

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

    /**
     * Time complexity: O(n)
     * Space complexity: O(log(n))
     */
    private static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return areSymmetric(root.left, root.right);
    }

    private static boolean areSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if ((root1 != null && root2 == null) || (root1 == null && root2 != null)
                || (root1.data != root2.data)) return false;
        return areSymmetric(root1.left, root2.right) && areSymmetric(root1.right, root2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println("Is Tree symmetric - " + isSymmetric(root));
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println("Is Tree symmetric - " + isSymmetric(root));
    }

}
