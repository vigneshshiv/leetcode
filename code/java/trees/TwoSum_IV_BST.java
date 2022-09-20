package code.java.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSum_IV_BST {

    private static boolean findTarget(TreeNode root, int k) {
        Map<Integer, Integer> table = new HashMap<>();
        return findTarget(root, k, table);
    }

    private static boolean findTarget(TreeNode root, int k, Map<Integer, Integer> table) {
        if (root == null) return false;
        if (table.containsKey(root.data)) return true;
        table.put(k - root.data, root.data);
        return findTarget(root.left, k, table) || findTarget(root.right, k, table);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        int k = 9;
        boolean hasTwoSumTarget = findTarget(root, k);
        System.out.println("K - " + k + ", Two sum of two elements target available - " + hasTwoSumTarget);
        //
        k = 28; hasTwoSumTarget = findTarget(root, k);
        System.out.println("K - " + k + ", Two sum of two elements target available - " + hasTwoSumTarget);
        //
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        k = 4; hasTwoSumTarget = findTarget(root, k);
        System.out.println("K - " + k + ", Two sum of two elements target available - " + hasTwoSumTarget);
    }

}
