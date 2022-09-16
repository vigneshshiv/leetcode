package code.java.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostOrderTraversal {

    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.data);
        return result;
    }

    private static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.peek();
                if (node.right != null && node.right != last) {
                    root = node.right;
                } else {
                    result.add(node.data);
                    last = stack.pop();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        // Level Order - 1 - 3 5 - 4 2 - 7 8
        TreeNode.printPostOrderTraversal(root);
        System.out.println();
        List<Integer> result = postorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = postorderTraversalIterative(root);
        System.out.println("Iterative - " + result);
        System.out.println();
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
        // Level Order - 1 - 3 5 - 6 7 4 8 - 9 10 11 12 17 69 48 50 - 17 21 20 15 18 54
        TreeNode.printPostOrderTraversal(root);
        System.out.println();
        result = postorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = postorderTraversalIterative(root);
        System.out.println("Iterative - " + result);
    }

}
