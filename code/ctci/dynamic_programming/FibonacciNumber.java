package code.ctci.dynamic_programming;

import java.util.Arrays;

public class FibonacciNumber {

    /**
     * Computes Nth Fibonacci Numbers
     *
     * There are 2 branches per call, So the runtime is O(2^N).
     *
     * The Space complexity of this algorithm is O(N), Although we have O(2^N) nodes in the tree total,
     *  Only O(N) exist at any given time. Therefore, only O(N) memory needed to execute.
     */
    static int fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * Memoization approach to print all Fibonacci Numbers from 0 to N
     *
     * This code runs on O(N) time, Space complexity: O(N) space
     */
    static int fibMemo(int n, int[] memo) {
        if (memo[n] > 0) return memo[n];
        if (n <= 2) return 1;
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    /**
     * Bottom up approach
     *
     * Time complexity: O(N) time
     * Space complexity: O(N)
     */
    static int fibBottomUp(int n) {
        if (n <= 2) return 1;
        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    /**
     * Fib optimal approach by space complexity
     *
     * Time complexity: O(N) time
     * Space complexity: O(1)
     */
    static int fibOptimal(int n) {
        if (n <= 2) return 1;
        int result = 0, a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 48;
        System.out.println("Fib (" + n + ") - " + fib(n));
        System.out.println("Fib Optimal (" + n + ") - " + fibOptimal(n));
    }

}
