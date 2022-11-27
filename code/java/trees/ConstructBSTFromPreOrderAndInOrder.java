package code.java.trees;

import java.util.Arrays;
import java.util.Stack;
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

    /**
     * Map solution - https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34555
     */
    private static TreeNode buildTreeIterative(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode current = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (current.data != inorder[j]) {
                current.left = new TreeNode(preorder[i]);
                stack.push(current);
                current = current.left;
            } else {
                j += 1;
                while (!stack.isEmpty() && stack.peek().data == inorder[j]) {
                    current = stack.pop();
                    j += 1;
                }
                current = current.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }

    private static TreeNode buildTreeOptimal(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        // Mid element
        int mid = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                mid = i;
            }
        }
        root.left = buildTreeOptimal(
                Arrays.copyOfRange(preorder, 1, mid + 1),
                Arrays.copyOfRange(inorder, 0, mid)
                );
        root.right = buildTreeOptimal(
                Arrays.copyOfRange(preorder, mid + 1, preorder.length),
                Arrays.copyOfRange(inorder, mid + 1, inorder.length)
                );
        return root;
    }

    // Solution without array copies
    // https://github.com/neetcode-gh/leetcode/blob/main/java/105-Construct-Binary-Tree-from-Preorder-and-Inorder-Traversal.java

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
        // Iterative
        root = buildTreeIterative(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        // Arrays copy of range
        root = buildTreeOptimal(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        //
        preorder = new int[] {-1};
        inorder = new int[] {-1};
        root = buildTree(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        // Iterative
        root = buildTreeIterative(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        // Arrays copy of range
        root = buildTreeOptimal(preorder, inorder);
        logger.accept(preorder, inorder);
        TreeNode.printPreOrderTraversal(root);
    }

}
