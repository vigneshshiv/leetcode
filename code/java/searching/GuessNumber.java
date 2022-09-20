package code.java.searching;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 */
public class GuessNumber {

    private static int[] GUESS = {1, 1, 0, -1, 1, 0, 1, 1, 1};

    private int guessNumber(int n) {
        int low = 0, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // int x = guess(mid);
            int x = GUESS[mid]; // Mimic API behaviour
            if (x == 0) return mid;
            if (x == -1) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {

    }

}
