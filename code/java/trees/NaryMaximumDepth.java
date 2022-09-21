package code.java.trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class NaryMaximumDepth {

    private static int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;
        if (root.children != null && !root.children.isEmpty()) {
            for (Node child : root.children) {
                depth = Math.max(depth, maxDepth(child));
            }
        }
        return depth + 1;
    }

    private static int maxDepthIterative(Node root) {
        int depth = 0;
        if (root == null) return depth;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level_size = queue.size();
            for (int i = 0; i < level_size; i++) {
                Node node = queue.poll();
                if (node != null && node.children != null && !node.children.isEmpty()) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            depth += 1;
        }
        return depth;
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
        int depth = maxDepth(root);
        System.out.println("Recursive: Max Depth - " + depth);
        //
        depth = maxDepthIterative(root);
        System.out.println("Iterative: Max Depth - " + depth);
        //
        root = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
        node4 = new Node(4);
        node5 = new Node(5);
        node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        //
        node11.children = Arrays.asList(node14);
        node7.children = Arrays.asList(node11);
        node8.children = Arrays.asList(node12);
        node9.children = Arrays.asList(node13);
        //
        node3.children = Arrays.asList(node6, node7);
        node4.children = Arrays.asList(node8);
        node5.children = Arrays.asList(node9, node10);
        //
        root.children = Arrays.asList(node2, node3, node4, node5);
        //
        depth = maxDepth(root);
        System.out.println("Recursive: Max Depth - " + depth);
        depth = maxDepthIterative(root);
        System.out.println("Iterative: Max Depth - " + depth);
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
