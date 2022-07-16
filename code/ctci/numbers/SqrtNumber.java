package code.ctci.numbers;

public class SqrtNumber {

    /**
     * This code runs in O(log n)
     * This algorithm is essentially doing a binary search to find the square root
     */
    static int findSqrtNumber(int n, int min, int max) {
        if (max < min) return -1; // No square root
        int guess = (min + max) / 2;
        if (guess * guess == n) return guess;
        if (guess * guess < n) { // too low
            return findSqrtNumber(n, guess + 1, max); // try higher
        } else {
            return findSqrtNumber(n, min, guess - 1); // try lower
        }
    }

    public static void main(String[] args) {
        System.out.println("Square Root for 65 - " + findSqrtNumber(64, 1, 64));
    }

}
