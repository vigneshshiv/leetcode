package code.java.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        stackNodes(root);
    }

    public int next() {
        TreeNode current = stack.pop();
        stackNodes(current.right);
        return current.data;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void stackNodes(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        // Right
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        //
        TreeNode.printInOrderTraversal(root);
        System.out.println();
        //
        BSTIterator bstIterator = new BSTIterator(root);
        System.out.println("Next - " + bstIterator.next());
        System.out.println("Next - " + bstIterator.next());
        System.out.println("Has Next - " + bstIterator.hasNext());
        System.out.println("Next - " + bstIterator.next());
        System.out.println("Has Next - " + bstIterator.hasNext());
        System.out.println("Next - " + bstIterator.next());
        System.out.println("Has Next - " + bstIterator.hasNext());
        System.out.println("Next - " + bstIterator.next());
        System.out.println("Has Next - " + bstIterator.hasNext());
    }

}
