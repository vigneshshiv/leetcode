package code.java.trees;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBSTFromPreOrderAndInOrder {

    private static int preIdx = 0, inIdx = 0;

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Reset to default for every test case
        preIdx = 0; inIdx = 0;
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private static TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (preIdx >= preorder.length) return null;
        if (inorder[inIdx] == stop) {
            inIdx += 1;
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx++]);
        root.left = build(preorder, inorder, root.data);
        root.right = build(preorder, inorder, stop);
        return root;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (preOrder, inOrder) -> {
            System.out.println("PreOrder - " + Arrays.toString(preOrder) + ", InOrder - " + Arrays.toString(inOrder));
        };
        //
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        preorder = new int[] {-1};
        inorder = new int[] {-1};
        root = buildTree(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
    }

}
