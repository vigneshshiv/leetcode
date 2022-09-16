package code.java.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeBinaryTrees {

    private static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.data + root2.data);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    /**
     * BFS (Deque) approach, DFS is similar just use Stack (Deque) approach
     *
     * https://leetcode.com/problems/merge-two-binary-trees/discuss/104331
     */
    private static TreeNode mergeTreesIterative(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        Deque<TreeNode[]> deque = new ArrayDeque<>();
        deque.offer(new TreeNode[] {root1, root2});
        while (!deque.isEmpty()) {
            TreeNode[] N = deque.poll();
            // If Right side is Null, then continue
            if (N[1] == null) continue;;
            // Merge Left and Right
            N[0].data += N[1].data;
            // If Left side is Null, then replace with Right side node
            if (N[0].left == null) {
                N[0].left = N[1].left;
            } else {
                deque.offer(new TreeNode[] {N[0].left, N[1].left});
            }
            if (N[0].right == null) {
                N[0].right = N[1].right;
            } else {
                deque.offer(new TreeNode[] {N[0].right, N[1].right});
            }
        }
        return root1;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        //
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        //
        TreeNode result = mergeTrees(root1, root2);
        TreeNode.printPreOrderTraversal(result);
        System.out.println();
        result = mergeTreesIterative(root1, root2);
        TreeNode.printPreOrderTraversal(result);
        System.out.println();
    }

}
