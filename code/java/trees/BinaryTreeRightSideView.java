package code.java.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // If Root is null, then return empty
        if (root == null) return result;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            while (levelSize-- > 0) {
                TreeNode current = q.poll();
                if (levelSize == 0) {
                    result.add(current.data);
                }
                if (current.left != null) {
                    q.offer(current.left);
                }
                if (current.right != null) {
                    q.offer(current.right);
                }
            }
        }
        return result;
    }

    private static List<Integer> rightSideViewRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        rightSideViewRecursive(root, result, 0);
        return result;
    }

    private static void rightSideViewRecursive(TreeNode root, List<Integer> result, int level) {
        if (root == null) return;
        if (level == result.size()) {
            result.add(root.data);
        }
        rightSideViewRecursive(root.right, result, level + 1);
        rightSideViewRecursive(root.left, result, level + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        //
        List<Integer> result = rightSideView(root);
        System.out.println(result);
        result = rightSideViewRecursive(root);
        System.out.println(result);
        //
        root = new TreeNode(1);
        root.right = new TreeNode(3);
        result = rightSideView(root);
        System.out.println(result);
        result = rightSideViewRecursive(root);
        System.out.println(result);
        //
        result = rightSideView(null);
        System.out.println(result);
        result = rightSideViewRecursive(null);
        System.out.println(result);
    }

}
