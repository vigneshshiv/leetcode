package code.java.operators;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {

    // you need treat n as an unsigned value
    private static int reverseBits(int n) {
        if (n == 0) return 0;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= n & 1;
            n >>= 1;
        }
        return result;
    }

    // Follow up: https://leetcode.com/problems/reverse-bits/discuss/54746

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (N, result) -> {
            System.out.println("N - " + N + ", Result - " + result + ", Binary - " + Integer.toBinaryString(result));
        };
        //
        int N = 3;
        int result = reverseBits(N);
        logger.accept(N, result);
        //
        N = 5; result = reverseBits(N);
        logger.accept(N, result);
        //
        N = 7; result = reverseBits(N);
        logger.accept(N, result);
        //
        N = 43261596; result = reverseBits(N);
        logger.accept(N, result);
        //
        // N = 4294967293; result = reverseBits(N);
        // logger.accept(N, result);
    }

}
