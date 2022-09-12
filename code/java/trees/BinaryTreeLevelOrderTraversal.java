package code.java.trees;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> sets = new LinkedList<>();
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                sets.add(queue.poll().data);
            }
            result.add(sets);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
        //
        root = new TreeNode(1);
        result = levelOrder(root);
        System.out.println(result);
        //
        result = levelOrder(null);
        System.out.println(result);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        result = levelOrder(root);
        System.out.println(result);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        result = levelOrder(root);
        System.out.println(result);
    }

}
