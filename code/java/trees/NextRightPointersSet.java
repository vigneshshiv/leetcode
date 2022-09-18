package code.java.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class NextRightPointersSet {

    private static Node connect(Node root) {
        if (root == null) return null;
        connect(root.left, root.right);
        return root;
    }

    private static void connect(Node left, Node right) {
        if (left == null && right == null || left.next != null) {
            return;
        }
        left.next = right;
        connect(left.right, right.left);
        connect(left.left, left.right);
        connect(right.left, right.right);
    }

    private static Node levelOrderIterative(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node last = null;
            while (levelSize-- > 0) {
                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                Node node = queue.poll();
                node.next = last;
                last = node;
            }
        }
        return root;
    }

    private static Node levelOrderIterativeOptimal(Node root) {
        if (root == null || root.left == null) return root;
        Node node = root;
        while (node != null && node.left != null) {
            Node next = node.left;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            node = next;
        }
        return root;
    }

    private static List<String> levelOrder(Node root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> sets = new LinkedList<>();
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                Node node = queue.poll();
                result.add(String.valueOf(node.val));
                if (node.next != null) {
                    result.add(String.valueOf(node.next.val));
                }
            }
            result.add("#");
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        //
        root = connect(root);
        List<String> result = levelOrder(root);
        System.out.println(result);
        //
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        //
        root = levelOrderIterative(root);

        System.out.println(result);
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
