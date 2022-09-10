package code.java.trees;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InOrderSuccessor {

    /**
     * Left most child
     */
    private static TreeNode leftMostNode(TreeNode node) {
        while (Objects.nonNull(node.left)) {
            node = node.left;
        }
        return node;
    }

    /**
     * Next Successor node
     *
     * Time complexity: O(h), where h is the height of the tree
     * Space complexity: O(1) space
     */
    private static TreeNode nextSuccessorNode(TreeNode node) {
        if (Objects.isNull(node)) return null;
        if (Objects.nonNull(node.right)) {
            return leftMostNode(node.right);
        } else {
            TreeNode current = node;
            TreeNode parent = current.parent;
            while (Objects.nonNull(parent) && !Objects.equals(parent.left, current)) {
                current = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode rootNode = TreeNode.createMinimalBST(array);
        // In-Order Traversal
        System.out.print("In-Order Traversal -- ");
        TreeNode.printInOrderTraversal(rootNode);
        System.out.println();
        int[] nodes = {1, 4, 5, 7, 8};
        Function<Integer, TreeNode> finder = idx -> rootNode.find(idx);
        BiConsumer<Integer, TreeNode> successorNode = (idx, node) -> {
            TreeNode nextNode = nextSuccessorNode(finder.apply(idx));
            System.out.println(idx + "'s Next node - " + (Objects.nonNull(nextNode) ? nextNode.data : null));
        };
        Arrays.stream(nodes).forEach(idx -> successorNode.accept(idx, finder.apply(idx)));
    }

}
