package code.java.trees;

import code.java.utils.MethodsUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PathsWithSum {

    /**
     * Count Paths with Sum, traverse every node and add data and compare with targetSum then count the path.
     *
     * Time complexity: O(N log N), where N is the number of nodes in the tree.
     * Space complexity: O(1)
     */
    private static int countPathsWithSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) return 0;
        // Count paths with sum starting from the root
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
        // Try the nodes on the left and right
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);
        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    /**
     * Count the number of paths with the targetSum starting from this node.
     */
    private static int countPathsWithSumFromNode(TreeNode node, int targetSum, int runningSum) {
        if (Objects.isNull(node)) return 0;
        runningSum += node.data;
        int totalPaths = 0;
        if (Objects.equals(runningSum, targetSum)) { // Path found
            totalPaths++;
        }
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, runningSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, runningSum);
        return totalPaths;
    }

    /**
     * Count Paths with Sum Optimal Approach
     *
     * Time complexity: O(N), where N is the number of nodes in the tree
     * Space complexity: O(log N)
     */
    private static int countPathsWithSumOptimal(TreeNode node, int targetSum) {
        if (Objects.isNull(node)) return 0;
        Map<Integer, Integer> memo = new HashMap<>();
        int pathCount = countPathsWithSum(node, targetSum, 0, memo);
        // System.out.println("Path Count - " + pathCount + ", memo size - " + memo.size());
        // memo.forEach((key, value) -> System.out.println("Key - " + key + ", Value - " + value));
        return pathCount;
    }

    /**
     * Count Paths with Sum
     */
    private static int countPathsWithSum(TreeNode node, int targetSum, int runningSum, Map<Integer, Integer> memo) {
        if (Objects.isNull(node)) return 0; // Base case
        runningSum += node.data;
        int pathCount = runningSum - targetSum;
        int totalPaths = memo.getOrDefault(pathCount, 0);
        // If runningSum equals targetSum, then additional path is identified from Root to till this node.
        if (Objects.equals(runningSum, targetSum)) { // Path found
            totalPaths++;
        }
        managePathRunningSumDetail(memo, runningSum, 1);
        totalPaths += countPathsWithSum(node.left, targetSum, runningSum, memo);
        totalPaths += countPathsWithSum(node.right, targetSum, runningSum, memo);
        managePathRunningSumDetail(memo, runningSum, -1);
        return totalPaths;
    }

    private static void managePathRunningSumDetail(Map<Integer, Integer> memo, int runningSum, int delta) {
        int value = memo.getOrDefault(runningSum, 0) + delta;
        if (Objects.equals(value, 0)) { // Remove when zero to reduce space usage
            memo.remove(runningSum);
        } else {
            memo.put(runningSum, value);
        }
    }

    private static void testAll() {
        int min = -20;
        int max = 20;
        int size = 20;
        boolean isWorking = true;
        TreeNode root = MethodsUtility.randomBST(size, min, max);
        for (int targetSum = min; targetSum <= max; targetSum++) {
            int result1 = countPathsWithSum(root, targetSum);
            int result2 = countPathsWithSumOptimal(root, targetSum);
            if (result1 > 0 || result2 > 0) {
                System.out.println(targetSum + ": " + result1 + ", " + result2 + " | " + (result1 == result2));
            }
            if (result1 != result2) {
                System.out.println("Not working - " + targetSum + ": " + result1 + ", " + result2);
                isWorking = false;
                break;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.setLeftNode(new TreeNode(3));
        root.left.setLeftNode(new TreeNode(3));
        root.left.setRightNode(new TreeNode(-2));
        root.setRightNode(new TreeNode(1));
        root.right.setRightNode(new TreeNode(2));
        //
        int targetSum = 8;
        int pathsCount = countPathsWithSum(root, targetSum);
        System.out.println("Paths Count for TargetSum-8 is " + pathsCount);
        pathsCount = countPathsWithSumOptimal(root, targetSum);
        System.out.println("Paths Count for TargetSum-8 is " + pathsCount);
        // Test few other random cases
        testAll();
    }

}
