package code.java.trees;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBST {

    private static int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        findSmallest(root, k, pq);
        return pq.poll();
    }

    private static void findSmallest(TreeNode root, int k, PriorityQueue<Integer> pq) {
        if (root == null) return;
        findSmallest(root.left, k, pq);
        pq.offer(root.data);
        while (pq.size() > k) {
            pq.poll();
        }
        findSmallest(root.right, k, pq);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        // Right
        root.right = new TreeNode(4);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        int k = 1;
        int result = kthSmallest(root, k);
        System.out.println("Kth smallest, K - " + k + ", Result - " + result);
        //
        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        // Right
        root.right = new TreeNode(6);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        k = 3;
        result = kthSmallest(root, k);
        System.out.println("Kth smallest, K - " + k + ", Result - " + result);
    }

}
