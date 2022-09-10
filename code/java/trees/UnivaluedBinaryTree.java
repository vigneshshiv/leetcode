package code.java.trees;

/**
 * https://leetcode.com/problems/univalued-binary-tree/
 */
public class UnivaluedBinaryTree {

    private static boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.left.data != root.data) {
            return false;
        }
        if (root.right != null && root.right.data != root.data) {
            return false;
        }
        if (isUnivalTree(root.left) && isUnivalTree(root.right)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        boolean isUnivaluedTree = isUnivalTree(root);
        System.out.println("Above tree isUnivalued? " + isUnivaluedTree);
        System.out.println();
        //
        root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(2);
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        isUnivaluedTree = isUnivalTree(root);
        System.out.println("Above tree isUnivalued? " + isUnivaluedTree);
    }

}
