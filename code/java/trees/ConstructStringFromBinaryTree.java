package code.java.trees;

/**
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 */
public class ConstructStringFromBinaryTree {

    private static String tree2str(TreeNode root) {
        if (root == null) return "";
        String result = String.valueOf(root.data);
        if (root.left != null) {
            result += "(" + tree2str(root.left) + ")";
        }
        if (root.left == null && root.right != null) {
            result += "()";
        }
        if (root.right != null) {
            result += "(" + tree2str(root.right) + ")";
        }
        return result;
    }

    private static String tree2strOptimal(TreeNode root) {
        if (root == null) return "";
        StringBuilder result = new StringBuilder();
        result.append(root.data);
        if (root.left != null) {
            result.append("(").append(tree2str(root.left)).append(")");
        }
        if (root.left == null && root.right != null) {
            result.append("()");
        }
        if (root.right != null) {
            result.append("(").append(tree2str(root.right)).append(")");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        String result = tree2str(root);
        System.out.println(result);
        //
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        //
        TreeNode.printPreOrderTraversal(root);
        System.out.println();
        result = tree2strOptimal(root);
        System.out.println(result);
    }

}
