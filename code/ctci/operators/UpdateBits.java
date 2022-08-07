package code.ctci.operators;

public class UpdateBits {

    private static void updateBits(int n, int m, int i, int j) {
        System.out.println("n - " + Integer.toBinaryString(n));
        System.out.println("m - " + Integer.toBinaryString(m));
        // Clear all bits from i through j in n.
        int allOnes = ~0; // will be sequence of all 1's
        // 1's before position j, then 0s
        int left = allOnes << (j + 1);
        System.out.println("left - " + Integer.toBinaryString(left));
        // 1's after position i
        int right = ((1 << i) - 1);
        // All 1s, except for 0s be
        System.out.println("right - " + Integer.toBinaryString(right));
        // Create a mask and All 1's, except for 0s between i and j
        int mask = left | right;
        // Clear bits j through i then put m in there
        int n_cleared = n & mask; // clear bits j through i
        int m_shifted = m << i; // Move m into correct position
        int result = n_cleared | m_shifted;
        System.out.println(n + " holds " + m + " from pos " + i + " to " + j + ", result - " + Integer.toBinaryString(result));
    }

    public static void main(String[] args) {
        updateBits(32, 11, 1, 4);
        int a = ~23423;
        int b = 5;
        updateBits(a, b, 29, 31);
    }

}
