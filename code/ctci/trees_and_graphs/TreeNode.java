package code.ctci.trees_and_graphs;

import java.util.Arrays;

public class TreeNode {

    public int data;
    public TreeNode left, right, parent;
    private int size = 0;

    public TreeNode(int value) {
        data = value;
        size = 1;
    }

    public void setLeftNode(TreeNode node) {
        left = node;
        if (left != null) {
            left.parent = this;
        }
    }

    public void setRightNode(TreeNode node) {
        right = node;
        if (right != null) {
            right.parent = this;
        }
    }

    public void insertInOrder(int value) {
        if (value <= data) {
            if (left == null) {
                setLeftNode(new TreeNode(value));
            } else {
                left.insertInOrder(value);
            }
        } else if (value > data) {
            if (right == null) {
                setRightNode(new TreeNode(value));
            } else {
                right.insertInOrder(value);
            }
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height(): 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public TreeNode find(int value) {
        if (data == value) return this;
        if (value <= data) {
            return left != null ? left.find(value) : null;
        } else if (value > data) {
            return right != null ? right.find(value) : null;
        }
        return null;
    }

    public boolean isBST() {
        if (left != null) {
            if (data < left.data || !left.isBST()) return false;
        }
        if (right != null) {
            if (data >= right.data || !right.isBST()) return false;
        }
        return true;
    }

    public static TreeNode createMinimalBST(int[] arr) {
        return createMinimalBST(arr, 0, arr.length - 1);
    }

    private static TreeNode createMinimalBST(int[] arr, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.setLeftNode(createMinimalBST(arr, start, mid - 1));
        node.setRightNode(createMinimalBST(arr, mid + 1, end));
        return node;
    }

    public static void printInOrderTraversal(TreeNode node) {
        if (node == null) return;
        printInOrderTraversal(node.left);
        System.out.print(node.data + " ");
        printInOrderTraversal(node.right);
    }

    public static void printPreOrderTraversal(TreeNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        printPreOrderTraversal(node.left);
        printPreOrderTraversal(node.right);
    }

    public static void printPostOrderTraversal(TreeNode node) {
        if (node == null) return;
        printPostOrderTraversal(node.left);
        printPostOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        /**
         *    3
         *   / \
         *  2   4
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        printInOrderTraversal(root);
        System.out.println();
        // Pre-Order Traversal
        System.out.print("Pre-Order Traversal -- ");
        printPreOrderTraversal(root);
        System.out.println();
        // Post-Order Traversal
        System.out.print("Post-Order Traversal -- ");
        printPostOrderTraversal(root);
        System.out.println();
        System.out.println("Height - " + root.height() + "\n");

        /**
         * Minimal BST
         */
        int[] arr = {2, 3, 5, 6, 8, 9};
        TreeNode node = createMinimalBST(arr);
        System.out.println("Minimal BST created for - " + Arrays.toString(arr));
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        printInOrderTraversal(node);
        System.out.println();
        // Pre-Order Traversal
        System.out.print("Pre-Order Traversal -- ");
        printPreOrderTraversal(node);
        System.out.println();
        // Post-Order Traversal
        System.out.print("Post-Order Traversal -- ");
        printPostOrderTraversal(node);
        System.out.println();
        System.out.println("Height - " + node.height() + "\n");

        /**
         * Find 8 and print In-Order, Pre-Order & Post-Order traversal
         */
        TreeNode treeNode = node.find(8);
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        printInOrderTraversal(treeNode);
        System.out.println();
        // Pre-Order Traversal
        System.out.print("Pre-Order Traversal -- ");
        printPreOrderTraversal(treeNode);
        System.out.println();
        // Post-Order Traversal
        System.out.print("Post-Order Traversal -- ");
        printPostOrderTraversal(treeNode);
        System.out.println("\n");
        // Check whether part of subtree is BST
        System.out.println("Find node 8 is BST - " + treeNode.isBST());
    }

}
