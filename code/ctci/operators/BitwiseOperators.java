package code.ctci.operators;

import java.util.Arrays;

public class BitwiseOperators {

    /**
     * Change Number sign
     */
    static void changeSign(int number) {
        int signedNumber = ~number + 1;
        System.out.println("Change Sign input - " + number + ", after sign change - " + signedNumber);
    }

    /**
     * Swap 2 numbers by XOR
     *
     * Time: O(1) time
     * Space: O(1) no extra space allocated
     */
    static void swapNumbers(int a, int b) {
        System.out.println("Swap Numbers inputs, a - " + a + ", b - " + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("Swap Numbers outputs, a - " + a + ", b - " + b);
    }

    /**
     * Check the given number is Odd or Even
     *
     * Time: O(1) time
     * Space: O(1) no extra space allocated
     */
    static void checkOddOrEven(int n) {
        String result = (n & 1) == 0 ? "Even" : "Odd";
        System.out.println("The given number " + n + " is " + result);
    }

    /**
     * Given a list of integers where all integers occur even times, expect one which occur odd times. Find out that integer.
     *
     * Solution - XOR of any two same numbers will always be 0, and XOR of any number with 0 is the number itself.
     *
     * Time: O(n) time, where n is the number of elements in the array
     * Space: O(1) space, no extra space allocated
     */
    static void findOddOccuringElement(int[] arr) {
        int checker = 0;
        for (int value : arr) {
            checker ^= value;
        }
        System.out.println("Given array - " + Arrays.toString(arr) + ", Odd Element - " + checker);
    }

    /**
     * How to find the number is Power of 2
     *
     * Time: O(n) time, where n is the number of elements in the array
     * Space: O(1) space, no extra space allocated
     */
    static void isPowerOfTwo(int n) {
        // n & (n - 1) never have a 1 bit in the same place.
        boolean powerOfTwo = n != 0 & (n & (n - 1)) == 0;
        System.out.println("Given number - " + n + " is power of 2 - " + powerOfTwo);
    }

    public static void main(String[] args) {
        // Change Sign
        int number = 12;
        changeSign(number);
        // Swap Numbers
        int a = 4, b = 6;
        swapNumbers(a, b);
        // Check the number is odd or Even
        int n = 14;
        checkOddOrEven(n);
        // Find Odd occurring element in array
        int[] arr = { 1, 2, 2, 1, 1, 4, 4, 4, 5, 4, 5 };
        findOddOccuringElement(arr);
        // Is the number of Power of 2
        isPowerOfTwo(24);
        isPowerOfTwo(16);
    }

}
