package code.ctci.numbers;

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
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * Memoization approach to print all Fibonacci Numbers from 0 to n
     *
     * This code runs on O(N) time
     */
    static int fibMemo(int n, int[] memo) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else if (memo[n] > 0) return memo[n];
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println("Fib (7) - " + fib(n));
        //
        int[] memo = new int[n + 1];
        for (int i = 0; i < n; i++) {
            System.out.println(i + "th Fib No : " + fibMemo(i, memo));
        }
    }

}
