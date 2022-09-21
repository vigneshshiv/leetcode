package code.java.trees;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNode {

    static int min = Integer.MAX_VALUE, secondMin = min;

    private static int findSecondMinimumValue(TreeNode root) {
        // Local Testing
        min = Integer.MAX_VALUE; secondMin = min;
        find2ndMin(root);
        return secondMin == Integer.MAX_VALUE ? -1 : secondMin;
    }

    private static void find2ndMin(TreeNode root) {
        if (root == null) return;
        find2ndMin(root.left);
        if (root.data < min) {
            secondMin = min;
            min = root.data;
        } else if (root.data < secondMin && root.data != min) {
            secondMin = root.data;
        }
        find2ndMin(root.right);
    }

    private static int findSecondMinimumValueOptimal(TreeNode root) {
        if (root.left == null) return -1;
        int left = root.data == root.left.data ? findSecondMinimumValueOptimal(root.left) : root.left.data;
        int right = root.data == root.right.data ? findSecondMinimumValueOptimal(root.right) : root.right.data;
        return left == -1 || right == -1 ? Math.max(left, right) : Math.min(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int result = findSecondMinimumValueOptimal(root);
        System.out.println("2nd Minimum - " + result);
        //
        root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = findSecondMinimumValue(root);
        System.out.println("2nd Minimum - " + result);
    }

}
