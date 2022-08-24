package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/power-of-three/
 */
public class PowerOfThree {

    /**
     * https://stackoverflow.com/questions/1804311/how-to-check-if-an-integer-is-a-power-of-3
     */
    private static boolean isPowerOfThree(int n) {
        int maxInt32 = Integer.MAX_VALUE;
        int maxIntPowerOf3 = 1162261467; // 3^19, 3^20 = 3486784401 > MAX_VALUE
        if (n <= 0 || n > maxIntPowerOf3) return false;
        return maxIntPowerOf3 % n == 0;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Boolean> logger = (input, result) -> System.out.println("Is " + input + " power of three? - " + result);
        int n = 27;
        logger.accept(n, isPowerOfThree(n));
        //
        n = 6561;
        logger.accept(n, isPowerOfThree(n));
        //
        n = 19683;
        logger.accept(n, isPowerOfThree(n));
        //
        n = 7;
        logger.accept(n, isPowerOfThree(n));
        //
        n = 13;
        logger.accept(n, isPowerOfThree(n));
        //
        n = 45;
        logger.accept(n, isPowerOfThree(n));
    }

}
