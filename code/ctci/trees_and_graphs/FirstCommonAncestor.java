package code.ctci.trees_and_graphs;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FirstCommonAncestor {

    private static class NodeResult {
        public TreeNode node;
        public boolean isAncestor;
        public NodeResult(TreeNode node, boolean ancestor) {
            this.node = node;
            this.isAncestor = ancestor;
        }
    }

    /**
     * Two pointer approach
     *
     * Time complexity: O(d^2), where d is the depth of the tree
     * Space complexity: O(1)
     */
    private static TreeNode findCommonAncestorByTwoPointers(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || Objects.isNull(p) || Objects.isNull(q)) return null;
        if (Objects.equals(p, q)) return p;
        TreeNode ap = p.parent;
        while (Objects.nonNull(ap)) {
            TreeNode aq = q.parent;
            while (Objects.nonNull(aq)) {
                if (Objects.equals(ap, aq)) return ap;
                aq = aq.parent;
            }
            ap = ap.parent;
        }
        return null;
    }

    /**
     * Find the depth of tree and move up the pointer to the same level, then approaching common ancestor
     *
     * Time complexity: O(d), where d is the depth of tree
     * Space complexity: O(1)
     */
    private static TreeNode findCommonAncestorByTwoPointersWithDepth(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || Objects.isNull(p) || Objects.isNull(q)) return null;
        if (Objects.equals(p, q)) return p;
        //
        int delta = depth(p) - depth(q);
        TreeNode shorter = delta > 0 ? q : p;
        TreeNode longer = delta > 0 ? p : q;
        longer = goUpByDelta(longer, Math.abs(delta));
        //
        while (!Objects.equals(shorter, longer) && Objects.nonNull(shorter) && Objects.nonNull(longer)) {
            shorter = shorter.parent;
            longer = longer.parent;
        }
        return Objects.isNull(shorter) || Objects.isNull(longer) ? null : shorter;
    }

    /**
     * Find Common Ancestor by Sibling approach
     *
     * Time complexity: O(t) time, t is the size of the subtree
     *  Worst case: O(n) time, where n is the number of nodes in the tree
     *
     * Space complexity: O(1)
     */
    private static TreeNode findCommonAncestorByTwoPointersSiblingApproach(TreeNode root, TreeNode p, TreeNode q) {
        if (!covers(root, p) || !covers(root, q)) return null;
        else if (covers(p, q)) return p;
        else if (covers(q, p)) return q;
        // Traverse p upwards until it covers q
        TreeNode sibling = getSibling(p);
        TreeNode parent = p.parent;
        while (!covers(sibling, q)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * Find Common Ancestor by Without Parent link approach
     *
     * Time complexity: O(n) time on a balanced tree
     */
    private static TreeNode findCommonAncestorByWithoutParentLinkApproach(TreeNode root, TreeNode p, TreeNode q) {
        if (!covers(root, p) || !covers(root, q)) return null;
        return commonAncestor(root, p, q);
    }

    private static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || Objects.isNull(p) || Objects.isNull(q)) return null;
        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        if (!Objects.equals(pIsOnLeft, qIsOnLeft)) return root;
        TreeNode node = pIsOnLeft ? root.left : root.right;
        return commonAncestor(node, p, q);
    }

    /**
     * Find Common Ancestor by Without Parent Link Optimal Approach
     */
    private static TreeNode findCommonAncestorByWithoutParentLinkOptimalApproach(TreeNode root, TreeNode p, TreeNode q) {
        NodeResult result = findCommonAncestor(root, p, q);
        return Objects.nonNull(result) && result.isAncestor ? result.node : null;
    }

    private static NodeResult findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || Objects.isNull(p) || Objects.isNull(q)) {
            return new NodeResult(root, false);
        }
        if (Objects.equals(root, p) && Objects.equals(root, q)) {
            return new NodeResult(root, true); // Found Common Ancestor
        }
        NodeResult leftNode = findCommonAncestor(root.left, p, q);
        if (Objects.nonNull(leftNode) && leftNode.isAncestor) return leftNode;
        //
        NodeResult rightNode = findCommonAncestor(root.right, p, q);
        if (Objects.nonNull(rightNode) && rightNode.isAncestor) return rightNode;
        //
        if (Objects.nonNull(leftNode.node) && Objects.nonNull(rightNode.node)) {
            return new NodeResult(root, true); // This is the Common Ancestor
        } else if (Objects.equals(root, p) || Objects.equals(root, q)) {
            boolean isAncestor = Objects.nonNull(leftNode.node) || Objects.nonNull(rightNode.node);
            return new NodeResult(root, isAncestor);
        } else {
            return new NodeResult(Objects.nonNull(leftNode.node) ? leftNode.node : rightNode.node, false);
        }
    }

    /**
     * Find Common Ancestor by Optimal Approach
     *
     * Time complexity: O(d^2) time, where d is the depth of the tree
     * Space complexity: O(1)
     */
    private static TreeNode findCommonAncestorByOptimalApproach(TreeNode root, TreeNode p, TreeNode q) {
        if (Objects.isNull(root) || Objects.isNull(p) || Objects.isNull(q)) return null;
        if (Objects.equals(p, q)) return p;
        // Util
        BiFunction<TreeNode, TreeNode, Boolean> isOnPath = (ancestor, node) -> {
            while (!Objects.equals(ancestor, node) && Objects.nonNull(node)) {
                node = node.parent;
            }
            return Objects.equals(ancestor, node);
        };
        TreeNode ancestor = p.parent;
        while (Objects.nonNull(ancestor)) {
            if (isOnPath.apply(ancestor, q)) return ancestor;
            ancestor = ancestor.parent;
        }
        return null;
    }

    /**
     * Depth of the tree of a given node
     */
    private static int depth(TreeNode node) {
        int depth = 0;
        while (Objects.nonNull(node)) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    /**
     * Move up node pointer position by delta
     */
    private static TreeNode goUpByDelta(TreeNode node, int delta) {
        while (delta > 0 && Objects.nonNull(node)) {
            node = node.parent;
            delta--;
        }
        return node;
    }

    private static boolean covers(TreeNode root, TreeNode node) {
        if (Objects.isNull(root)) return false;
        if (Objects.equals(root, node)) return true;
        return covers(root.left, node) || covers(root.right, node);
    }

    private static TreeNode getSibling(TreeNode node) {
        if (Objects.isNull(node) || Objects.isNull(node.parent)) return null;
        TreeNode parent = node.parent;
        return Objects.equals(parent.left, node) ? parent.right : parent.left;
    }

    private static void testAll(Function<TreeNode, String> outputData) {
        int[] array = {5, 3, 6, 1, 9, 11};
        TreeNode root = new TreeNode(20);
        Arrays.stream(array).forEach(value -> root.insertInOrder(value));
        TreeNode p = root.find(1);
        TreeNode q = root.find(9);
        // Two Pointer Approach
        TreeNode commonAncestor = findCommonAncestorByTwoPointers(root, p, q);
        System.out.println("TestAll - First Common Ancestor Node by Two Pointer approach - " + outputData.apply(commonAncestor));
        // Two Pointer with depth Approach
        commonAncestor = findCommonAncestorByTwoPointersWithDepth(root, p, q);
        System.out.println("TestAll - First Common Ancestor Node by Two Pointer with depth approach - " + outputData.apply(commonAncestor));
        // Two Pointer with Sibling Approach
        commonAncestor = findCommonAncestorByTwoPointersSiblingApproach(root, p, q);
        System.out.println("First Common Ancestor Node by Two Pointer Sibling approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkApproach(root, p, q);
        System.out.println("TestAll (1 to 10) First Common Ancestor Node by Without Parent Link approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Optimal Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkOptimalApproach(root, p, q);
        System.out.println("TestAll (1 to 10) First Common Ancestor Node by Without Parent Link Optimal approach - " + outputData.apply(commonAncestor));
        // Optimal Approach
        commonAncestor = findCommonAncestorByOptimalApproach(root, p, q);
        System.out.println("TestAll - First Common Ancestor Node by Optimal approach - " + outputData.apply(commonAncestor));
        //
        array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode node = TreeNode.createMinimalBST(array);
        TreeNode n3 = node.find(8);
        TreeNode n7 = node.find(8);
        // Two Pointer Approach
        commonAncestor = findCommonAncestorByTwoPointers(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Two Pointer approach - " + outputData.apply(commonAncestor));
        // Two Pointer with depth Approach
        commonAncestor = findCommonAncestorByTwoPointersWithDepth(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Two Pointer with depth approach - " + outputData.apply(commonAncestor));
        // Two Pointer with Sibling Approach
        commonAncestor = findCommonAncestorByTwoPointersSiblingApproach(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Two Pointer Sibling approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkApproach(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Without Parent Link approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Optimal Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkOptimalApproach(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Without Parent Link Optimal approach - " + outputData.apply(commonAncestor));
        // Optimal Approach
        commonAncestor = findCommonAncestorByOptimalApproach(node, p, q);
        System.out.println("TestAll (1 to 10) - First Common Ancestor Node by Optimal approach - " + outputData.apply(commonAncestor));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        // Left
        root.setLeftNode(new TreeNode(10));
        root.left.setLeftNode(new TreeNode(5));
        root.left.left.setLeftNode(new TreeNode(3));
        root.left.left.setRightNode(new TreeNode(7));
        root.left.setRightNode(new TreeNode(15));
        root.left.right.setRightNode(new TreeNode(17));
        // Right
        root.setRightNode(new TreeNode(30));
        //
        Function<TreeNode, String> outputData = node -> Objects.nonNull(node) ? String.valueOf(node.data) : null;
        // Inputs
        TreeNode p = root.find(7);
        TreeNode q = root.find(17);
        // Two Pointer Approach
        TreeNode commonAncestor = findCommonAncestorByTwoPointers(root, p, q);
        System.out.println("First Common Ancestor Node by Two Pointer approach - " + outputData.apply(commonAncestor));
        // Two Pointer with depth Approach
        commonAncestor = findCommonAncestorByTwoPointersWithDepth(root, p, q);
        System.out.println("First Common Ancestor Node by Two Pointer with depth approach - " + outputData.apply(commonAncestor));
        // Two Pointer with Sibling Approach
        commonAncestor = findCommonAncestorByTwoPointersSiblingApproach(root, p, q);
        System.out.println("First Common Ancestor Node by Two Pointer Sibling approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkApproach(root, p, q);
        System.out.println("First Common Ancestor Node by Without Parent Link approach - " + outputData.apply(commonAncestor));
        // Without Parent Link Optimal Approach
        commonAncestor = findCommonAncestorByWithoutParentLinkOptimalApproach(root, p, q);
        System.out.println("First Common Ancestor Node by Without Parent Link Optimal approach - " + outputData.apply(commonAncestor));
        // Optimal Approach
        commonAncestor = findCommonAncestorByOptimalApproach(root, p, q);
        System.out.println("First Common Ancestor Node by Optimal approach - " + outputData.apply(commonAncestor));
        // Test other scenarios
        testAll(outputData);
    }

}
