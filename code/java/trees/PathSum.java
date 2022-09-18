package code.java.trees;

import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/path-sum/
 */
public class PathSum {

    private static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.data == targetSum) return true;
        return hasPathSum(root.left, targetSum - root.data) || hasPathSum(root.right, targetSum - root.data);
    }

    private static boolean hasPathSumIterative(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> values = new Stack<>();
        stack.push(root);
        values.push(root.data);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int value = values.pop();
            if (node.left == null && node.right == null && value == targetSum) {
                return true;
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                    values.push(node.right.data + value);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    values.push(node.left.data + value);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        int targetSum = 22;
        boolean hasPath = hasPathSum(root, targetSum);
        System.out.println("Recursive: Has Path Sum of 22 in the tree? - " + hasPath);
        hasPath = hasPathSumIterative(root, targetSum);
        System.out.println("Iterative: Has Path Sum of 22 in the tree? - " + hasPath);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        targetSum = 5;
        hasPath = hasPathSum(root, targetSum);
        System.out.println("Recursive: Has Path Sum of 5 in the tree? - " + hasPath);
        hasPath = hasPathSumIterative(root, targetSum);
        System.out.println("Iterative: Has Path Sum of 5 in the tree? - " + hasPath);
    }

}
