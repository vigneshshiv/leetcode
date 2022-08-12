package code.ctci.dynamic_programming;

public class RecursiveMulitply {

    private static int peasantCounter = 0;
    private static int halfCount = 0;
    private static int halfCountMemo = 0;
    private static int halfCountOptimal = 0;

    private static void initialize() {
        peasantCounter = 0;
        halfCount = 0;
        halfCountMemo = 0;
        halfCountOptimal = 0;
    }

    /**
     * Basic Recursive approach, Not suitable for large numbers
     */
    private static int multiply(int a, int b) {
        if (b == 1) return a;
        return a + multiply(a, b - 1);
    }

    /**
     * Time complexity: O(log s), where s is the bigger number of two numbers
     * Space complexity: O(1)
     */
    private static int peasantMultiply(int a, int b) {
        if (a < b) return peasantMultiply(b, a);
        int value = 0;
        while (a > 0) {
            peasantCounter++;
            if ((a % 10) % 2 == 1) {
                value += b;
            }
            a >>= 1;
            b <<= 1;
        }
        return value;
    }

    private static int multiplyByHalfAndAdd(int a, int b) {
        int low = a < b ? a : b;
        int high = a < b ? b : a;
        return multiplyByHalfHelper(low, high);
    }

    private static int multiplyByHalfAndAddMemo(int a, int b) {
        int low = a < b ? a : b;
        int high = a < b ? b : a;
        int[] memo = new int[low + 1];
        return multiplyByHalfHelperMemo(low, high, memo);
    }

    /**
     * Compute half. If uneven, compute other half. If even, double it.
     *
     * Time complexity: O(log s), where s is the smallest of two number
     * Space complexity: O(log s)
     */
    private static int multiplyByHalfHelper(int low, int high) {
        if (low == 0) return 0;
        if (low == 1) return high;
        int lower = low >> 1;
        int firstHalf = multiplyByHalfHelper(lower, high);
        int secondHalf = firstHalf;
        if (low % 2 == 1) {
            halfCount++;
            secondHalf = multiplyByHalfHelper(low - lower, high);
        }
        halfCount++;
        return firstHalf + secondHalf;
    }

    /**
     * Memoized approach - Compute half. If uneven, compute other half. If even, double it.
     *
     * Time complexity: O(log s), where s is the smallest of two number
     * Space complexity: O(log s)
     */
    private static int multiplyByHalfHelperMemo(int low, int high, int[] memo) {
        if (memo[low] > 0) return memo[low];
        if (low == 0) return 0;
        if (low == 1) return high;
        int lower = low >> 1;
        int firstHalf = multiplyByHalfHelperMemo(lower, high, memo);
        int secondHalf = firstHalf;
        if (low % 2 == 1) {
            halfCountMemo++;
            secondHalf = multiplyByHalfHelperMemo(low - lower, high, memo);
        }
        halfCountMemo++;
        memo[low] = firstHalf + secondHalf;
        return memo[low];
    }

    /**
     * Compute half. If uneven, compute other half. If even, double it.
     *
     * Time complexity: O(log s), where s is the smallest of two number
     * Space complexity: O(log s)
     */
    private static int multiplyByHalfHelperOptimal(int low, int high) {
        if (low == 0) return 0;
        if (low == 1) return high;
        int lower = low >> 1;
        int half = multiplyByHalfHelperOptimal(lower, high);
        if (low % 2 == 0) {
            halfCountOptimal++;
            return half + half;
        } else {
            halfCountOptimal += 2;
            return half + half + high;
        }
    }

    public static void main(String[] args) {
        System.out.println("5 * 3 - " + multiply(5, 3));
        System.out.println("8 * 7 - " + multiply(8, 7));
        System.out.println("2002 * 8120 - " + peasantMultiply(2002, 8120) + ", No of counts - " + peasantCounter);
        System.out.println("2002 * 8120 - " + multiplyByHalfAndAdd(2002, 8120) + ", No of counts - " + halfCount);
        System.out.println("2002 * 8120 - " + multiplyByHalfAndAddMemo(2002, 8120) + ", No of counts - " + halfCountMemo);
        System.out.println("2002 * 8120 - " + multiplyByHalfHelperOptimal(2002, 8120) + ", No of counts - " + halfCountOptimal);
        initialize();
        System.out.println("7212 * 13282 - " + peasantMultiply(7212, 13282) + ", No of counts - " + peasantCounter);
        System.out.println("7212 * 13282 - " + multiplyByHalfAndAdd(7212, 13282) + ", No of counts - " + halfCount);
        System.out.println("7212 * 13282 - " + multiplyByHalfAndAddMemo(7212, 13282) + ", No of counts - " + halfCountMemo);
        System.out.println("7212 * 13282 - " + multiplyByHalfHelperOptimal(7212, 13282) + ", No of counts - " + halfCountOptimal);

    }

}
