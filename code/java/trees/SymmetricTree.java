package code.java.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

    /**
     * Time complexity: O(n)
     * Space complexity: O(log(n))
     */
    private static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return areSymmetric(root.left, root.right);
    }

    private static boolean areSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if ((root1 != null && root2 == null) || (root1 == null && root2 != null)
                || (root1.data != root2.data)) return false;
        return areSymmetric(root1.left, root2.right) && areSymmetric(root1.right, root2.left);
    }

    private static boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        stack.offerFirst(root.left);
        stack.offerLast(root.right);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pollFirst();
            TreeNode right = stack.pollLast();
            if (left.data != right.data) return false;
            // Mirror view elements check
            if ((left.left == null && right.right != null) || (left.left != null && right.right == null)
                    || (left.right != null && right.left == null) || (left.right == null && right.left != null)) {
                return false;
            }
            if (left.right != null && right.left != null) {
                stack.offerFirst(left.right);
                stack.offerLast(right.left);
            }
            if (left.left != null && right.right != null) {
                stack.offerFirst(left.left);
                stack.offerLast(right.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println("Recursive - Is Tree symmetric - " + isSymmetric(root));
        System.out.println("Iterative - Is Tree symmetric - " + isSymmetricIterative(root));
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println("Recursive - Is Tree symmetric - " + isSymmetric(root));
        System.out.println("Iterative - Is Tree symmetric - " + isSymmetricIterative(root));
        //
        root = new TreeNode(1);
        root.left = new TreeNode(0);
        System.out.println("Recursive - Is Tree symmetric - " + isSymmetric(root));
        System.out.println("Iterative - Is Tree symmetric - " + isSymmetricIterative(root));
        //
        root = new TreeNode(1);
        System.out.println("Recursive - Is Tree symmetric - " + isSymmetric(root));
        System.out.println("Iterative - Is Tree symmetric - " + isSymmetricIterative(root));
    }

}
