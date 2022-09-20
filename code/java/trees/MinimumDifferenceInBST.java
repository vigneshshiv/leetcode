package code.java.trees;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 *
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumDifferenceInBST {

    static int min = Integer.MAX_VALUE, val = -1;

    private static int getMinimumDifference(TreeNode root) {
        // Cleanup before start
        min = Integer.MAX_VALUE;
        minimumDifference(root);
        // Clean Up for local other test
        val = -1;
        return min;
    }

    private static void minimumDifference(TreeNode root) {
        if (root == null) return;
        minimumDifference(root.left);
        if (val >= 0) {
            min = Math.min(min, root.data - val);
        }
        val = root.data;
        minimumDifference(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int minimumDiff = getMinimumDifference(root);
        System.out.println("Minimum difference - " + minimumDiff);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(48);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(49);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        minimumDiff = getMinimumDifference(root);
        System.out.println("Minimum difference - " + minimumDiff);
        //
        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(7);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        minimumDiff = getMinimumDifference(root);
        System.out.println("Minimum difference - " + minimumDiff);
    }

}
