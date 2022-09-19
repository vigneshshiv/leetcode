package code.java.trees;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBST {

    private static TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums, 0, nums.length - 1);
    }

    private static TreeNode createBST(int[] nums, int low, int high) {
        if (high < low) return null;
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBST(nums, low, mid - 1);
        root.right = createBST(nums, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println("Nums - " + Arrays.toString(nums));
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        nums = new int[] {1, 3};
        root = sortedArrayToBST(nums);
        System.out.println("Nums - " + Arrays.toString(nums));
        TreeNode.printPreOrderTraversal(root);
    }

}
