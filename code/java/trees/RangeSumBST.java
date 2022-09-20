package code.java.trees;

/**
 * https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumBST {

    private static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        if (root.data > low) { sum += rangeSumBST(root.left, low, high); }
        if (root.data < high) { sum += rangeSumBST(root.right, low, high); }
        if (root.data >= low && root.data <= high) {
            sum += root.data;
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int low = 7, high = 15;
        int sum = rangeSumBST(root, low, high);
        System.out.println("Sum - " + sum);
        //
        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(1);
        root.left.right.left = new TreeNode(6);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(18);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        low = 6; high = 10;
        sum = rangeSumBST(root, low, high);
        System.out.println("Sum - " + sum);
    }

}
