package code.java.numbers;

/**
 * https://leetcode.com/problems/sqrtx/
 */
public class SqrtNumber {

    /**
     * This code runs in O(log n)
     * This algorithm is essentially doing a binary search to find the square root
     */
    static int findSqrtNumber(int n, int min, int max) {
        if (max < min) return -1; // No square root
        int guess = min + (max - min) / 2;
        // guess * guess causes integer max range 2^32 - 1.
        if (guess * guess == n) return guess;
        if (guess * guess < n) { // too low
            return findSqrtNumber(n, guess + 1, max); // try higher
        } else {
            return findSqrtNumber(n, min, guess - 1); // try lower
        }
    }

    /**
     * This code runs in O(log n)
     * This algorithm is essentially doing a binary search to find the square root
     */
    static int findSqrtOfNumber(int n) {
        if (n == 0) return 0;
        int min = 1, guess = 0, max = n;
        while (min < max) {
            guess = min + (max - min) / 2;
            if (guess <= n / guess && (guess + 1) > n / (guess + 1)) { // Found the Result
                return guess;
            } else if (guess > n / guess) { // Keep check left
                max = guess;
            } else {
                min = guess + 1;
            }
        }
        return min;
    }

    /**
     * Time complexity: O(log n)
     */
    static int sqrtNewtonMethod(int n) {
        if (n == 0) return 0;
        long x = n;
        while (x > n / x) {
            x = (x + n / x) / 2;
        }
        return (int) x;
    }

    private static double sqrtNewtonMethodForPrecision(int n) {
        double x = n;
        double root = 0d;
        while (true) {
            root = 0.5 * (x + (n / x));
            if (Math.abs(root - x) < 0.5) {
                break;
            }
            x = root;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("Square Root for 64 - " + findSqrtNumber(64, 1, 64));
        System.out.println("New Square Root for 65 - " + findSqrtOfNumber(65));
        System.out.println("Square Root for 4 - " + findSqrtNumber(4, 1, 4));
        System.out.println("New Square Root for 4 - " + findSqrtOfNumber(4));
        System.out.println("Square Root for 8 - " + findSqrtNumber(8, 1, 8));
        System.out.println("New Square Root for 8 - " + findSqrtOfNumber(8));
        System.out.println("New Square Root for 1 - " + findSqrtOfNumber(1));
        System.out.println("New Square Root for 0 - " + findSqrtOfNumber(0));
        System.out.println("New Square Root for 3 - " + findSqrtOfNumber(3));
        System.out.println("New Square Root for 5 - " + findSqrtOfNumber(5));
        System.out.println("New Square Root for 2147395599 - " + findSqrtOfNumber(2147395599));
        System.out.println((int) Math.sqrt(2147395599));
        System.out.println("Newton Square Root for 18 - " + sqrtNewtonMethodForPrecision(18));
    }

}
