package code.ctci.utils;

import code.ctci.trees_and_graphs.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodsUtility {

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static int randomIntRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static boolean randomBoolean() {
        return randomIntRange(0, 1) == 0;
    }

    public static boolean randomBoolean(int percentTrue) {
        return randomIntRange(1, 100) <= percentTrue;
    }

    public static boolean[][] randomBooleanMatrix(int M, int N, int percentTrue) {
        boolean[][] matrix = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = randomBoolean(percentTrue);
            }
        }
        return matrix;
    }

    public static int[][] randomMatrix(int M, int N, int min, int max) {
        int[][] matrix = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = randomIntRange(min, max);
            }
        }
        return matrix;
    }

    public static int[] randomArray(int N, int min, int max) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = randomIntRange(min, max);
        }
        return array;
    }

    public static String convertToBinaryString(int n, int size) {
        String value = "";
        for (int i = 0; i < size; i++) {
            value = String.valueOf(n & 1) + value;
            n >>= 1;
        }
        return value;
    }

    public static TreeNode randomBST(int range, int min, int max) {
        Supplier<Integer> randomIntGenerator = () -> randomIntRange(min, max);
        int value = randomIntGenerator.get();
        TreeNode root = new TreeNode(value);
        for (int i = 1; i < range; i++) {
            root.insertInOrder(randomIntGenerator.get());
        }
        return root;
    }

    public static TreeNode createTreeFromArray(int[] array) {
        if (array.length == 0) return null;
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // Function
        Function<Integer, TreeNode> nodeCreation = idx -> new TreeNode(array[idx]);
        boolean done = false;
        int i = 1;
        while (!done) {
            TreeNode node = queue.element();
            if (Objects.isNull(node.left)) {
                node.left = nodeCreation.apply(i);
                queue.add(node.left);
                i++;
            } else if (Objects.isNull(node.right)) {
                node.right = nodeCreation.apply(i);
                queue.add(node.right);
                i++;
            } else {
                queue.remove();
            }
            if (i == array.length) {
                done = true;
            }
        }
        return root;
    }

}
