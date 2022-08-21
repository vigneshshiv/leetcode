package code.java.searching;

/**
 * https://leetcode.com/problems/valid-perfect-square/
 */
public class ValidPerfectSquare {

    private static boolean isPerfectSquare(int n) {
        if (n == 0) return false;
        int min = 1, guess = 0, max = n;
        while (min <= max) {
            guess = min + (max - min) / 2;
            if (n % guess == 0 && guess == n / guess) { // Found the Result
                return true;
            } else if (guess > n / guess) { // Keep check left
                max = guess - 1;
            } else {
                min = guess + 1;
            }
        }
        return false;
    }

    /**
     * Math approach
     *
     * n = 25
     * i = 1, n = 24
     * i = 3, n = 21
     * i = 5, n = 16
     * i = 7, n = 9
     * i = 9, n = 0
     */
    private static boolean isPerfectSquareMathApproach(int n) {
        int i = 1;
        while (n > 0) {
            n -= i;
            i += 2;
        }
        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println("Is 64 perfect square ? - " + isPerfectSquare(64) + ", Math Approach - " + isPerfectSquareMathApproach(64));
        System.out.println("Is 20 perfect square ? - " + isPerfectSquare(20) + ", Math Approach - " + isPerfectSquareMathApproach(20));
        System.out.println("Is 25 perfect square ? - " + isPerfectSquare(25) + ", Math Approach - " + isPerfectSquareMathApproach(25));
        System.out.println("Is 49 perfect square ? - " + isPerfectSquare(49) + ", Math Approach - " + isPerfectSquareMathApproach(49));
        System.out.println("Is 1 perfect square ? - " + isPerfectSquare(1) + ", Math Approach - " + isPerfectSquareMathApproach(1));
        System.out.println("Is 0 perfect square ? - " + isPerfectSquare(0) + ", Math Approach - " + isPerfectSquareMathApproach(0));
        System.out.println("Is 16 perfect square ? - " + isPerfectSquare(16) + ", Math Approach - " + isPerfectSquareMathApproach(16));
        System.out.println("Is 14 perfect square ? - " + isPerfectSquare(14) + ", Math Approach - " + isPerfectSquareMathApproach(14));
        System.out.println("Is 5 perfect square ? - " + isPerfectSquare(5) + ", Math Approach - " + isPerfectSquareMathApproach(5));
    }

}
