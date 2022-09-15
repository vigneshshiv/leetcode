package code.java.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

    private static int findLowestCommonAncestorIterative(TreeNode root, int x, int y) {
        Map<Integer, Integer> nodes = new HashMap<>(); // <Node, Parent>
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        nodes.put(root.data, null);
        while (!nodes.containsKey(x) || !nodes.containsKey(y)) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (node.left != null) {
                    nodes.put(node.left.data, node.data);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    nodes.put(node.right.data, node.data);
                    queue.add(node.right);
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        while (x != -1) {
            visited.add(x);
            Integer data = nodes.getOrDefault(x, -1);
            x = data != null ? data.intValue() : -1;
        }
        while (!visited.contains(y)) {
            y = nodes.getOrDefault(y, -1);
        }
        return y;
    }

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225
     */
    private static int findLowestCommonAncestorRecursive(TreeNode root, int x, int y) {
        if (root == null || root.data == x || root.data == y) {
            return root != null ? root.data : -1;
        }
        int left = findLowestCommonAncestorRecursive(root.left, x, y);
        int right = findLowestCommonAncestorRecursive(root.right, x, y);
        return left == -1 ? right : (right == -1) ? left : root.data;
    }

    private static int findLowestCommonAncestor(TreeNode root, int x, int y) {
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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        int commonAncestor = findLowestCommonAncestor(root, 4, 5);
        System.out.println("Common Ancestor for 4 & 5 - " + commonAncestor);
        //
        commonAncestor = findLowestCommonAncestorIterative(root, 4, 5);
        System.out.println("Iterative Common Ancestor for 4 & 5 - " + commonAncestor);
    }

}
