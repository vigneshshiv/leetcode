package code.java.operators;

import code.java.utils.MethodsUtility;

public class BitSwaps {

    private static int noOfSwapRequired(int a, int b) {
        int count = 0;
        int c = a ^ b;
        while (c != 0) {
            c = c & (c - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int swaps = noOfSwapRequired(29, 15);
        System.out.println("No of Swap of needed for " + MethodsUtility.convertToBinaryString(15, 5)
                + " to convert to " + MethodsUtility.convertToBinaryString(29, 5) + " is : " + swaps);
    }

}
