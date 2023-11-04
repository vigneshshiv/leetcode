package code.java.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class FindMostFrequentElementInBST {

    static Integer prev = null;
    static int count = 1, max = 0;
    static int[] modes;

    private static int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> result = new ArrayList<>();
        findMostFrequentElements(root, result);
        modes = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            modes[i] = result.get(i);
        }
        return modes;
    }

    private static void findMostFrequentElements(TreeNode root, List<Integer> list) {
        if (root == null) return;
        findMostFrequentElements(root.left, list);
        if (prev != null) {
            if (root.data == prev) count++;
            else count = 1;
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.data);
        } else if (count == max) {
            list.add(root.data);
        }
        prev = root.data;
        findMostFrequentElements(root.right, list);
    }

    private static int[] findModeOptimal(TreeNode root) {
        Map<Integer, Integer> frequency = new HashMap<>();
        findModeRecursive(root, frequency);
        // Leetcode compilation issue for Optional class, so used java.util package directly here
        java.util.Optional<Integer> max = frequency.values().stream().max(Integer::compare);
        return max.isPresent() 
            ? frequency.entrySet().stream()
                .filter(entry -> entry.getValue() == max.get())
                .mapToInt(Map.Entry::getKey)
                .toArray() 
            : null;
    }

    private static void findModeRecursive(TreeNode node, Map<Integer, Integer> frequency) {
        if (node == null) return;
        // Count the frequency
        frequency.put(node.data, frequency.getOrDefault(node.data, 0) + 1);
        findModeRecursive(node.left, frequency);
        findModeRecursive(node.right, frequency);
    }

    private static int[] findModeIterative(TreeNode root) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                int key = stack.peek().data;
                frequency.put(key, frequency.getOrDefault(key, 0) + 1);
                root = stack.pop().right;
            }
        }
        // Leetcode compilation issue for Optional class, so used java.util package directly here
        java.util.Optional<Integer> max = frequency.values().stream().max(Integer::compare);
        return max.isPresent() 
            ? frequency.entrySet().stream()
                .filter(entry -> entry.getValue() == max.get())
                .mapToInt(Map.Entry::getKey)
                .toArray()
            : null;
    }

    private static int[] findModeBFS(TreeNode root) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            frequency.put(node.data, frequency.getOrDefault(node.data, 0) + 1);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        // Leetcode compilation issue for Optional class, so used java.util package directly here
        java.util.Optional<Integer> max = frequency.values().stream().max(Integer::compare);
        return max.isPresent() 
            ? frequency.entrySet().stream()
                .filter(entry -> entry.getValue() == max.get())
                .mapToInt(Map.Entry::getKey)
                .toArray()
            : null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int[] result = findMode(root);
        System.out.println(Arrays.toString(result));
        result = findModeOptimal(root);
        System.out.println(Arrays.toString(result));
        result = findModeIterative(root);
        System.out.println(Arrays.toString(result));
        result = findModeBFS(root);
        System.out.println(Arrays.toString(result));
        //
        root = new TreeNode(1);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = findMode(root);
        System.out.println(Arrays.toString(result));
        result = findModeOptimal(root);
        System.out.println(Arrays.toString(result));
        result = findModeIterative(root);
        System.out.println(Arrays.toString(result));
        result = findModeBFS(root);
        System.out.println(Arrays.toString(result));
        //
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = findMode(root);
        System.out.println(Arrays.toString(result));
        result = findModeOptimal(root);
        System.out.println(Arrays.toString(result));
        result = findModeIterative(root);
        System.out.println(Arrays.toString(result));
        result = findModeBFS(root);
        System.out.println(Arrays.toString(result));
    }

}
