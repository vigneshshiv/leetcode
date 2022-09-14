package code.java.trees;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {

    private static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return checkBST(root, null, null);
    }

    private static boolean checkBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if ((min != null && root.data <= min) || (max != null && root.data >= max)) {
            return false;
        }
        return checkBST(root.left, min, root.data) && checkBST(root.right, root.data, max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println("Is 213 is Valid BST - " + isValidBST(root));
        //
        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println("Is 15345 is Valid BST - " + isValidBST(root));
        //
        root = new TreeNode(1);
        root.left = new TreeNode(1);
        System.out.println("Is (LEFT)11 is Valid BST - " + isValidBST(root));
        //
        root = new TreeNode(1);
        root.right = new TreeNode(1);
        System.out.println("Is 11(RIGHT) is Valid BST - " + isValidBST(root));
    }

}
