package code.java.operators;

import code.java.utils.MethodsUtility;

public class PairwiseSwaps {

    private static int swapOddEvenBits(int x) {
        return ( ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) ) ;
    }

    public static void main(String[] args) {
        int swap = swapOddEvenBits(11);
        System.out.println(MethodsUtility.convertToBinaryString(swap, 4));
    }

}
