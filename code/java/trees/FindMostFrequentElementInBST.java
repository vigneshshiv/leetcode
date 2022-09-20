package code.java.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int[] result = findMode(root);
        System.out.println(Arrays.toString(result));
        //
        root = new TreeNode(1);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = findMode(root);
        System.out.println(Arrays.toString(result));
    }

}
