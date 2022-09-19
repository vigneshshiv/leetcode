package code.java.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/same-tree/
 */
public class SameTree {

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.data != q.data) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private static boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.data != q.data) return false;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerFirst(p);
        queue.offerLast(q);
        while (!queue.isEmpty()) {
            TreeNode N1 = queue.pollFirst();
            TreeNode N2 = queue.pollLast();
            if (N1.data != N2.data) {
                return false;
            }
            // Null elements check both nodes
            if ((N1.left != null && N2.left == null) || (N1.left == null && N2.left != null)
                    || (N1.right != null && N2.right == null) || (N1.right == null && N2.right != null)) {
                return false;
            }
            if (N1.left != null && N2.left != null) {
                queue.offerFirst(N1.left);
                queue.offerLast(N2.left);
            }
            if (N1.right != null && N2.right != null) {
                queue.offerFirst(N1.right);
                queue.offerLast(N2.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        //
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        //
        TreeNode.printPreOrderTraversal(root1);
        System.out.println();
        TreeNode.printPreOrderTraversal(root2);
        System.out.println();
        //
        boolean isSameTree = isSameTree(root1, root2);
        System.out.println("Recursive - Is same tree? " + isSameTree);
        isSameTree = isSameTreeIterative(root1, root2);
        System.out.println("Iterative - Is same tree? " + isSameTree);
        //
        root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        //
        root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root1);
        System.out.println();
        TreeNode.printPreOrderTraversal(root2);
        System.out.println();
        //
        isSameTree = isSameTree(root1, root2);
        System.out.println("Recursive - Is same tree? " + isSameTree);
        isSameTree = isSameTreeIterative(root1, root2);
        System.out.println("Iterative - Is same tree? " + isSameTree);
        //
        root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(1);
        //
        root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root1);
        System.out.println();
        TreeNode.printPreOrderTraversal(root2);
        System.out.println();
        //
        isSameTree = isSameTree(root1, root2);
        System.out.println("Recursive - Is same tree? " + isSameTree);
        isSameTree = isSameTreeIterative(root1, root2);
        System.out.println("Iterative - Is same tree? " + isSameTree);
    }

}
