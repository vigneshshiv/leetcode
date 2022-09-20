package code.java.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {

    private static List<String> binaryTreePaths(TreeNode root) {
        return paths(root, "");
    }

    private static List<String> paths(TreeNode root, String path) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        path = path.isEmpty() ? String.valueOf(root.data) : path + "->" + root.data;
        if (root.left == null && root.right == null) {
            result.add(path);
            return result;
        }
        result.addAll(paths(root.left, path));
        result.addAll(paths(root.right, path));
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        List<String> result = binaryTreePaths(root);
        System.out.println(result);
        //
        root = new TreeNode(1);
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = binaryTreePaths(root);
        System.out.println(result);
    }

}
