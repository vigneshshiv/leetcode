package code.java.trees;

import code.java.utils.MethodsUtility;

import java.util.Objects;

public class SubtreeCheck {

    /**
     * Subtree check
     * T1 should always larger than T2 in this case
     *
     * Time complexity: O(n+m), where n and m are the number of nodes in T1 and T2
     * Space complexity: O(n+m)
     */
    private static void containsTree(TreeNode t1, TreeNode t2) {
        StringBuilder t1Order = new StringBuilder();
        StringBuilder t2Order = new StringBuilder();
        // Pre-Order traversal string
        getOrderString(t1, t1Order);
        getOrderString(t2, t2Order);
        boolean contains = t1Order.indexOf(t2Order.toString()) != -1;
        System.out.println("T1 Order - " + t1Order + ", T2 Order - " + t2Order + ", T1 Contains T2 - " + contains);
    }

    /**
     * Time complexity: O(n+m), where n and m are the number of nodes in T1 and T2
     * Space complexity: O(log(n) + log(m))
     */
    private static void containsTreeOptimal(TreeNode t1, TreeNode t2) {
        // Empty tree (T2) is always a subtree of T1
        boolean contains = Objects.nonNull(t2) ? containsSubtree(t1, t2) : true;
        System.out.println("Optimal Approach - Is T1 contains T2 - " + contains);
    }

    private static boolean containsSubtree(TreeNode t1, TreeNode t2) {
        if (Objects.isNull(t1)) return false; // Big tree is empty & subtree still not found
        if (Objects.equals(t1.data, t2.data) && matchTree(t1, t2)) return true;
        return containsSubtree(t1.left, t2) || containsSubtree(t1.right, t2);
    }

    private static boolean matchTree(TreeNode t1, TreeNode t2) {
        if (Objects.isNull(t1) && Objects.isNull(t2)) return true; // nothing left in the subtree
        if (Objects.isNull(t1) || Objects.isNull(t2)) return false; // tree is empty, therefore trees don't match
        if (!Objects.equals(t1.data, t2.data)) return false; // data doesn't match
        return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right); // Repeat until the last node of both left and right
    }

    private static void getOrderString(TreeNode node, StringBuilder builder) {
        if (Objects.isNull(node)) {
            builder.append("X");
            return;
        };
        builder.append(node.data);
        if (Objects.isNull(node.left) && Objects.isNull(node.right)) return;
        getOrderString(node.left, builder);
        getOrderString(node.right, builder);
    }

    public static void main(String[] args) {
        // #1 - t2 is not a subtree of t1
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(4);
        //
        TreeNode t2 = new TreeNode(3);
        t2.right = new TreeNode(4);
        containsTree(t1, t2);
        containsTreeOptimal(t1, t2);
        // #2 - t2 is a subtree of t1
        int[] array1 = {1, 2, 1, 3, 1, 1, 5};
        int[] array2 = {2, 3, 1};
        //
        t1 = MethodsUtility.createTreeFromArray(array1);
        t2 = MethodsUtility.createTreeFromArray(array2);
        containsTree(t1, t2);
        containsTreeOptimal(t1, t2);
    }

}
