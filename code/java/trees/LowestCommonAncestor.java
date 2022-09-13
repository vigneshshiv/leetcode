package code.java.trees;

import java.util.Objects;
import java.util.Stack;

public class LowestCommonAncestor {

    private static int findLowestCommonAncestor(TreeNode root, int x, int y) {
        Stack<Integer> pathToX = pathToXYIterative(root, x);
        Stack<Integer> pathToY = pathToXYIterative(root, y);
        int commonAncestor = -1;
        while (!pathToX.isEmpty() && !pathToY.isEmpty()) {
            x = pathToX.pop(); y = pathToY.pop(); // Side effect, Not a pure function
            if (x == y) commonAncestor = x;
            else break;
        }
        return commonAncestor;
    }

    // TODO: Iterative solution needs to be checked
    private static Stack<Integer> pathToXYIterative(TreeNode root, int xy) {
        Stack<Integer> result = new Stack<>();
        Stack<TreeNode> queue = new Stack<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            if (queue.peek().data == xy) {
                result.add(queue.pop().data);
                // To be verified
                int data = queue.peek().data;
                System.out.println("Popped data - " + data);
            } else {
                if (queue.peek().left == null && queue.peek().right == null) {
                    int data = queue.pop().data;
                    System.out.println("Popped data else - " + data);
                }
                if (!result.isEmpty()) {
                    result.add(queue.pop().data);
                    // To be verified
                    int data = queue.peek().data;
                    System.out.println("Popped data else result not empty - " + data);
                } else {
                    TreeNode node = queue.peek();
                    if (node.right != null) {
                        queue.push(node.right);
                    }
                    if (node.left != null) {
                        queue.push(node.left);
                    }
                }
            }
        }
        return result;
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
    }

}
