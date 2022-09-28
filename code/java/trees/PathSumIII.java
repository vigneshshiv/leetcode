package code.java.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {

    private static int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        Map<Long, Integer> table = new HashMap<>();
        table.put(0l, 1);
        return findPathSum(root, 0l, targetSum, table);
    }

    private static int findPathSum(TreeNode root, long sum, int target, Map<Long, Integer> table) {
        if (root == null) return 0;
        // Update the prefix sum with the current value
        sum += root.data;
        // No. of valid paths up to current node
        int numWays = table.getOrDefault(sum - target, 0);
        // Update the current sum, so that downstream nodes can be found with the target sum
        table.merge(sum, 1, Integer::sum);
        int result = numWays + findPathSum(root.left, sum, target, table) + findPathSum(root.right, sum, target, table);
        // Remove the sum for parent node right sub-tree
        table.merge(sum, -1, Integer::sum);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        // Right
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int result = pathSum(root, 8);
        System.out.println("Result - " + result);
        System.out.println();
        //
        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = pathSum(root, 22);
        System.out.println("Result - " + result);
        System.out.println();
        //
        root = new TreeNode(1000000000);
        root.left = new TreeNode(1000000000);
        root.left.left = new TreeNode(294967296);
        root.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left.left = new TreeNode(1000000000);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = pathSum(root, 0);
        System.out.println("Result - " + result);
        System.out.println();
    }

}
