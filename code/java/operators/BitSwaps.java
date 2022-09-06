package code.java.operators;

import code.java.utils.MethodsUtility;

/**
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
 */
public class BitSwaps {

    private static int noOfSwapRequired(int start, int goal) {
        int count = 0;
        int c = start ^ goal;
        while (c != 0) {
            c = c & (c - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int start = 29, goal = 15;
        int swaps = noOfSwapRequired(start, goal);
        System.out.println("No of Swap of needed for " + MethodsUtility.convertToBinaryString(goal, 4)
                + " to convert to " + MethodsUtility.convertToBinaryString(start, 5) + " is : " + swaps);
        //
        start = 10; goal = 7;
        swaps = noOfSwapRequired(start, goal);
        System.out.println("No of Swap of needed for " + MethodsUtility.convertToBinaryString(goal, 3)
                + " to convert to " + MethodsUtility.convertToBinaryString(start, 4) + " is : " + swaps);
        //
        start = 3; goal = 4;
        swaps = noOfSwapRequired(start, goal);
        System.out.println("No of Swap of needed for " + MethodsUtility.convertToBinaryString(goal, 3)
                + " to convert to " + MethodsUtility.convertToBinaryString(start, 2) + " is : " + swaps);
        //
        start = 35; goal = 22;
        swaps = noOfSwapRequired(start, goal);
        System.out.println("No of Swap of needed for " + MethodsUtility.convertToBinaryString(goal, 5)
                + " to convert to " + MethodsUtility.convertToBinaryString(start, 6) + " is : " + swaps);
    }

}
