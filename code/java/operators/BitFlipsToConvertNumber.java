package code.java.operators;

/**
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
 */
public class BitFlipsToConvertNumber {

    private static int minBitFlips(int start, int goal) {
        int mask = start ^ goal;
        return mask - Math.max(start, goal);
    }

    public static void main(String[] args) {

    }

}
