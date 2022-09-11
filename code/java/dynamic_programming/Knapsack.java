package code.java.dynamic_programming;

public class Knapsack {

    private static int knapsack(int[] weight, int[] values, int capacity) {
        int[][] memo = new int[weight.length + 1][capacity + 1];
        return higherValues(weight, values, capacity, weight.length, memo);
    }

    private static int higherValues(int[] weight, int[] values, int capacity, int index, int[][] memo) {
        int result = 0;
        if (capacity >= 0 && memo[index][capacity] > 0) {
            return memo[index][capacity];
        }
        if (index == 0 || capacity <= 0) return result;
        int left = higherValues(weight, values, capacity, index - 1, memo);
        int right = values[index - 1] + higherValues(weight, values, capacity - weight[index - 1], index - 1, memo);
        result = Math.max(left, right);
        memo[index][capacity] = result;
        return result;
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 4, 2, 5}, values = {5, 3, 5, 3, 2};
        int capacity = 10;
        int gain = knapsack(weight, values, capacity);
        System.out.println("Maximum Gain - " + gain);
    }

}
