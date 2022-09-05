package code.java.arrays;

/**
 * https://leetcode.com/problems/xor-operation-in-an-array/
 */
public class ArrayXOR {

    private static int xorOperation(int n, int start) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            x ^= (start + 2 * i);
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println("XOR Operation - " + xorOperation(5, 0));
        System.out.println("XOR Operation - " + xorOperation(4, 3));
    }

}
