package code.java.searching;

/**
 * https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

    private static boolean[] BAD_VERSION = {false, false, false, false, false, false, true, true, true};

    private static boolean isBadVersion(int n) {
        return BAD_VERSION[n];
    }

    private static int firstBadVersion(int n) {
        if (n == 1) return 1;
        int low = 0, mid = 0, high = n;
        while (low <= high) {
            mid = low + (high - low) / 2;
            // Call to API
            boolean result = isBadVersion(mid);
            if (!result) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println("BadVersion started at index - " + firstBadVersion(9));
    }

}
