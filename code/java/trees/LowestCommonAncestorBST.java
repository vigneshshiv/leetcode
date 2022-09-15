package code.java.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorBST {

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/discuss/64963
     */
    private static int findLowestCommonAncestorBSTIterative(TreeNode root, int x, int y) {
        while ((root.data - x) * (root.data - y) > 0) {
            root = x < root.data ? root.left : root.right;
        }
        return root.data;
    }

    private static int findLowestCommonAncestorBST(TreeNode root, int x, int y) {
        Stack<Integer> pathToX = pathToXY(root, x);
        Stack<Integer> pathToY = pathToXY(root, y);
        int commonAncestor = -1;
        while (!pathToX.isEmpty() && !pathToY.isEmpty()) {
            x = pathToX.pop(); y = pathToY.pop(); // Side effect, Not a pure function
            if (x == y) commonAncestor = x;
            else break;
        }
        return commonAncestor;
    }

    private static Stack<Integer> pathToXY(TreeNode root, int xy) {
        Stack<Integer> stack = new Stack<>();
        if (root == null) return null;
        if (root.data == xy) {
            stack.push(xy);
            return stack;
        }
        stack = pathToXY(root.left, xy);
        if (stack != null) {
            stack.push(root.data);
            return stack;
        }
        stack = pathToXY(root.right, xy);
        if (stack != null) {
            stack.push(root.data);
            return stack;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        //
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        int commonAncestor = findLowestCommonAncestorBSTIterative(root, 0, 5);
        System.out.println("Common Ancestor for 0 & 5 - " + commonAncestor);
        //
        commonAncestor = findLowestCommonAncestorBSTIterative(root, 2, 8);
        System.out.println("Common Ancestor for 2 & 8 - " + commonAncestor);
        //
        commonAncestor = findLowestCommonAncestorBST(root, 2, 3);
        System.out.println("Common Ancestor for 2 & 3 - " + commonAncestor);
        //
        commonAncestor = findLowestCommonAncestorBST(root, 7, 9);
        System.out.println("Common Ancestor for 7 & 9 - " + commonAncestor);
    }

}
