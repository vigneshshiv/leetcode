package code.java.trees_and_graphs;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class RandomNode {

    private TreeNode root;

    private void insertInOrder(int value) {
        if (Objects.isNull(root)) {
           root = new TreeNode(value);
        } else {
            root.insertInOrder(value);
        }
    }

    private TreeNode selectRandomNode() {
        if (Objects.isNull(root)) return null;
        Random random = new Random();
        int index = random.nextInt(root.getSize());
        return root.getRandomNode(index);
    }

    public static void main(String[] args) {
        int[] counts = new int[10];
        for (int i = 0; i < 1000; i++) {
            RandomNode randomNode = new RandomNode();
            int[] array = {1, 0, 6, 2, 3, 9, 4, 5, 8, 7};
            Arrays.stream(array).forEach(value -> randomNode.insertInOrder(value));
            int data = randomNode.selectRandomNode().data;
            counts[data]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + ": " + counts[i]);
        }
    }

}
