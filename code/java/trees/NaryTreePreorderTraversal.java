package code.java.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */
public class NaryTreePreorderTraversal {

    private static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }

    private static void preorderTraversal(Node root, List<Integer> result) {
        if (Objects.nonNull(root)) {
            result.add(root.val);
            if (Objects.nonNull(root.children) && !root.children.isEmpty()) {
                for (Node child : root.children) {
                    preorderTraversal(child, result);
                }
            }
        }
    }

    /**
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    private static List<Integer> preorderTraversalIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (Objects.nonNull(root.children)) {
                for (int i = root.children.size() - 1; i >= 0; i--) {
                    stack.add(root.children.get(i));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node3.children = Arrays.asList(node5, node6);
        root.children = Arrays.asList(node3, node2, node4);
        List<Integer> result = preorder(root); // [1, 3, 5, 6, 2, 4]
        System.out.println(result);
        //
        root = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
        node4 = new Node(4);
        node5 = new Node(5);
        node6 = new Node(6);
        Node node7 = new Node(7);
        Node node11 = new Node(11);
        Node node14 = new Node(14);
        //
        node11.children = Arrays.asList(node14);
        node7.children = Arrays.asList(node11);
        node3.children = Arrays.asList(node6, node7);
        //
        Node node8 = new Node(8);
        Node node12 = new Node(12);
        node8.children = Arrays.asList(node12);
        node4.children = Arrays.asList(node8);
        //
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node13 = new Node(13);
        node9.children = Arrays.asList(node13);
        node5.children = Arrays.asList(node9, node10);
        //
        root.children = Arrays.asList(node2, node3, node4, node5);
        result = preorderTraversalIterative(root); // [1, 2, 3, 6, 7, 11, 14, 4, 8, 12, 5, 9, 13, 10]
        System.out.println(result);
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

}
