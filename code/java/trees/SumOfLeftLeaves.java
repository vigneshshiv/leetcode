package code.java.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {

    private static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int result = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result += root.left.data;
        } else {
            result += sumOfLeftLeaves(root.left);
        }
        return result + sumOfLeftLeaves(root.right);
    }

    private static int sumOfLeftLeavesIterative(TreeNode root) {
        int result = 0;
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    result += node.left.data;
                } else {
                    stack.push(node.left);
                }
            }
            if (node.right != null && (node.right.left != null || node.right.right != null)) {
                stack.push(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        //
        int result = sumOfLeftLeaves(root);
        System.out.println("Recursive Left Leaves sum - " + result);
        result = sumOfLeftLeavesIterative(root);
        System.out.println("Iterative Left Leaves sum - " + result);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        result = sumOfLeftLeaves(root);
        System.out.println("Recursive Left Leaves sum - " + result);
        result = sumOfLeftLeavesIterative(root);
        System.out.println("Iterative Left Leaves sum - " + result);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(9);
        root.left.left.right = new TreeNode(10);
        root.left.left.left.left = new TreeNode(17);
        root.left.left.left.right = new TreeNode(21);
        root.left.left.right.right = new TreeNode(20);
        root.left.right.left = new TreeNode(11);
        root.left.right.right = new TreeNode(12);
        root.left.right.right.left = new TreeNode(15);
        root.left.right.right.right = new TreeNode(18);
        // Right
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(17);
        root.right.left.right = new TreeNode(69);
        root.right.right.left = new TreeNode(48);
        root.right.right.right = new TreeNode(50);
        root.right.right.left.left = new TreeNode(54);
        result = sumOfLeftLeaves(root);
        System.out.println("Recursive Left Leaves sum - " + result);
        result = sumOfLeftLeavesIterative(root);
        System.out.println("Iterative Left Leaves sum - " + result);
    }

}
