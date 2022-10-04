package code.java.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInOrderTraversal {

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        result.addAll(inorderTraversal(root.left));
        result.add(root.data);
        result.addAll(inorderTraversal(root.right));
        return result;
    }

    private static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // Keep traversing to left and add it to stack till last
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                //   1
                //  / \
                // 2   3
                // All left tree traversed and currently root is NULL
                // 2 is added to result, Stack is only having 1
                // If current node is 2, and it's popped out, 2's right is assigned to root which is NULL
                // So in the next iteration root still be NULL.
                // Stack top value 1 added to result and 1 popped out and 1's right 3 assigned to root.
                result.add(stack.peek().data);
                root = stack.pop().right;
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
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        List<Integer> result = inorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = inorderTraversalIterative(root);
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
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        result = inorderTraversal(root);
        System.out.println("Recursive - " + result);
        result = inorderTraversalIterative(root);
        System.out.println("Iterative - " + result);
    }

}
