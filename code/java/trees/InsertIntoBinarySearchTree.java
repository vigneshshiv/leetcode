package code.java.trees;

/**
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoBinarySearchTree {

    private static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.data) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    private static TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode node = root;
        while (true) {
            if (node != null) {
                if (val < node.data) {
                    if (node.left == null) {
                        node.left = new TreeNode(val);
                        break;
                    } else {
                        node = node.left;
                    }
                } else {
                    if (node.right == null) {
                        node.right = new TreeNode(val);
                        break;
                    } else {
                        node = node.right;
                    }
                }
            }
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
        insertIntoBST(root, 5);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        insertIntoBSTIterative(root, 8);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
    }

}
