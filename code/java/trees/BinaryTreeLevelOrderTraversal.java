package code.java.trees;

import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

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

    private static List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        levelOrderRecursive(root, 0, result);
        return result;
    }

    private static void levelOrderRecursive(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) return;
        List<Integer> sets = new ArrayList<>();
        if (level + 1 > result.size()) {
            result.add(sets);
        }
        sets = result.get(level);
        sets.add(root.data);
        levelOrderRecursive(root.left, level + 1, result);
        levelOrderRecursive(root.right, level + 1, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
        result = levelOrderRecursive(root);
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
