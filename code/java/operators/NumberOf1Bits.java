package code.java.operators;

import code.java.utils.MethodsUtility;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

    /**
     * https://leetcode.com/problems/number-of-1-bits/discuss/55099
     *
     * Why can't we use n > 0?
     *  - Integer.MAX_VALUE is 2147483647, and Integer.MAX_VALUE + 1 is -2147483648. Num -2147483648 has 1 one.
     *  - -2147483648 fails that test, so we don't count any 1s, and instead incorrectly return 0
     *
     * Why can't we use n >>= 1?
     *  - The assumption of this code is that, starting with a 32-bit binary number,
     *      we can move the bits over to the right, filling in zeros from the left.
     *
     *  - The operation denoted by >> is indeed also a shift (the "signed" or "arithmetic" shift),
     *      but not the shift we're looking for (since it fills in whatever the sign bit is, either 0 or 1)
     */
    private static int hammingWeight(int n) {
        int ones = 0;
        // n > 0, fails to return the correct the answer because of Integer MAX_VALUE.
        // Integer.MAX_VALUE + 1 is -2147483648, so it's not greater than 0, so loop will not enter into loop
        while (n != 0) {
            ones += (n & 1);
            //
            n >>>= 1;
        }
        return ones;
    }

    /**
     * It works for the above case, just iterate it till last bit
     */
    private static int hammingWeightAnotherWay(int n) {
        int ones = 0;
        for (int i = 0; i < 32; i++) {
            ones += (n & 1);
            n >>= 1;
        }
        return ones;
    }

    private static int hammingWeightOptimal(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (num, onesCount) -> System.out.println("Num - " + num + ", Ones Count - " + onesCount);
        //
        int num = 3;
        logger.accept(num, hammingWeight(num));
        logger.accept(num, hammingWeightOptimal(num));
        //
        num = 10;
        logger.accept(num, hammingWeight(num));
        logger.accept(num, hammingWeightOptimal(num));
        //
        num = 15;
        logger.accept(num, hammingWeight(num));
        logger.accept(num, hammingWeightOptimal(num));
    }

}
