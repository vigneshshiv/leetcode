package code.java.trees_and_graphs;

/**
 * https://leetcode.com/problems/root-equals-sum-of-children/
 */
public class TreeCheck {

    private static boolean checkTree(TreeNode root) {
        return root.data == (root.left.data + root.right.data);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println(checkTree(root));
        //
        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println(checkTree(root));
    }

}
