package code.ctci.trees_and_graphs;

import java.util.Arrays;
import java.util.Random;

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

    public static boolean isBST(TreeNode node) {
        return checkBST(node, null, null);
    }

    private static boolean checkBST(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.data <= min) || (max != null && node.data > max)) {
            return false;
        }
        if (!checkBST(node.left, min, node.data) || !checkBST(node.right, node.data, max)) {
            return false;
        }
        return true;
    }

    public static TreeNode createMinimalBST(int[] arr) {
        return createMinimalBST(arr, 0, arr.length - 1);
    }

    /**
     * Time complexity: O(n) time, where n is the length of the array
     * Space complexity: O(log n) space
     */
    private static TreeNode createMinimalBST(int[] arr, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.setLeftNode(createMinimalBST(arr, start, mid - 1));
        node.setRightNode(createMinimalBST(arr, mid + 1, end));
        return node;
    }

    /**
     * Time complexity: O(n) time
     * Space complexity: O(h), where h is the height of the tree
     *
     */
    private static int checkHeight(TreeNode node) {
        if (node == null) return -1;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (Math.abs(leftHeight - rightHeight) > 1) return Integer.MIN_VALUE;
        else return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Is Tree balanced
     */
    public static boolean isBalanced(TreeNode node) {
        /*
        // Non optimal way
        if (node == null) return false;
        int leftHeight = node.left != null ? node.left.height() + 1: 0;
        int rightHeight = node.right != null ? node.right.height() + 1: 0;
        System.out.println("Left Height - " + leftHeight + ", Right Height - " + rightHeight);
        return Math.abs(leftHeight - rightHeight) <= 1;
        */
        return checkHeight(node) != Integer.MIN_VALUE;
    }

    /**
     * Get Random node
     *
     * Time complexity: O(log n), where n is the number of nodes
     * Space complexity: O(log n)
     */
    public TreeNode getRandomNode() {
        int leftSize = left == null ? 0 : left.size;
        Random random = new Random();
        int index = random.nextInt(size);
        if (index == leftSize) {
            return this;
        } else if (index < leftSize) {
            return left.getRandomNode();
        } else {
            return right.getRandomNode();
        }
    }

    /**
     * Get Random node by provided random index
     *
     * Time complexity: O(log n) OR accurately it is O(d), where d is the depth of the tree
     * Space complexity: O(log n)
     */
    public TreeNode getRandomNode(int index) {
        int leftSize = left == null ? 0 : left.size;
        if (index == leftSize) {
            return this;
        } else if (index < leftSize) {
            return left.getRandomNode(index);
        } else {
            return right.getRandomNode(index - (leftSize + 1));
        }
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
        System.out.println();

        // Minimal BST
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode rootNode = createMinimalBST(array);
        System.out.println("Root? " + rootNode.data);
        System.out.println("Created BST? " + rootNode.isBST());
        System.out.println("Height - " + rootNode.height());
        // Pre-Order Traversal
        System.out.print("Pre-Order Traversal -- ");
        printPreOrderTraversal(rootNode);
        System.out.println();

        // BST Check
        TreeNode bstTree = new TreeNode(20);
        bstTree.left = new TreeNode(10);
        bstTree.right = new TreeNode(30);
        bstTree.left.right = new TreeNode(25);
        System.out.println("Is Valid BST (Actually Not) - " + bstTree.isBST());
        System.out.println("Is Valid BST with correct implementation - " + isBST(bstTree));

        // Balanced Tree
        TreeNode balancedTree = new TreeNode(5);
        // Left side
        balancedTree.left = new TreeNode(2);
        balancedTree.left.left = new TreeNode(1);
        balancedTree.left.right = new TreeNode(3);
        balancedTree.left.right.right = new TreeNode(4);
        // Right side
        balancedTree.right = new TreeNode(8);
        balancedTree.right.left = new TreeNode(6);
        balancedTree.right.right = new TreeNode(9);
        System.out.println("Is tree balanced - " + isBalanced(balancedTree));

        // Not balanced tree
        TreeNode notBalancedTree = new TreeNode(5);
        // Left side
        notBalancedTree.left = new TreeNode(2);
        notBalancedTree.left.left = new TreeNode(1);
        notBalancedTree.left.right = new TreeNode(3);
        notBalancedTree.left.right.right = new TreeNode(4);
        // Right side
        notBalancedTree.right = new TreeNode(8);
        System.out.println("Is tree balanced - " + isBalanced(notBalancedTree));
    }

}
