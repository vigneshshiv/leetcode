package code.java.operators;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

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
     * Check the given number is Odd or Even by Least Significant Bit (LSB)
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
     * Given a list of integers where all integers occur no of times (as input), expect one which occur odd times. Find out that integer.
     *
     * Array items range from 1-9
     */
    static void findOddOccuringElement(int[] arr, int repeatTimes) {
        int[] occuranceTimes = new int[9];
        for (int value : arr) {
            int j = 9;
            while (value > 0 && j > 0) {
                if ((value & 1) == 1) {
                    occuranceTimes[j - 1] = occuranceTimes[j - 1] + 1;
                }
                value >>= 1;
                j--;
            }
        }
        int base = 2, result = 0;
        for (int i = 0; i < occuranceTimes.length; i++) {
            int setbit = occuranceTimes[occuranceTimes.length - 1 - i] % repeatTimes;
            if (setbit != 0) {
                result += setbit * Math.pow(base, i);
            }
        }
        System.out.println("Unique element - " + result);
    }

    /**
     * https://leetcode.com/problems/power-of-two/
     *
     * How to find the number is Power of 2
     *
     *  #1 n = 5
     *     5 = 0101
     *   5-1 = 0100
     *   ans = 0100
     *
     *  #2 n = 8
     *     8 = 1000
     *   n-1 = 0111
     *   ans = 0000
     *
     * Time: O(n) time, where n is the number of elements in the array
     * Space: O(1) space, no extra space allocated
     */
    static void isPowerOfTwo(int n) {
        // n & (n - 1) never have a 1 bit in the same place.
        boolean powerOfTwo = n > 0 & (n & (n - 1)) == 0;
        System.out.println("Given number - " + n + " is power of 2 - " + powerOfTwo);
    }

    /**
     * Power of any number with base
     */
    static void findPowerOfN(int base, int n) {
        if (Objects.equals(n, 0)) {
            System.out.println(n + " is zero, so the power of base " + base + " for " + n + " is zero");
            return;
        }
        int value = 0;
        int pos = 1;
        while (n > 0) {
            int val = n & 1;
            value += val * Math.pow(base, pos++);
            n >>= 1;
        }
        System.out.println("Base - " + base + ", " + n + "th value - " + value);
    }

    static void findPowerOfNOptimal(int base, int power) {
        int x = base, y = power;
        int value = 1;
        while (power > 0) {
            if ((power & 1) == 1) {
                value *= base;
            }
            base *= base;
            power >>= 1;
        }
        System.out.println(x + "^" + y + " = " + value);
    }

    static void countSetBits(int n) {
        System.out.println("Integer " + n + " binary string is " + Integer.toBinaryString(n));
        int count = 0;
        while (n > 0) {
            count++;
            n = n & (n-1);
        }
        System.out.println(count);
    }

    /**
     * Find XOR of no's from 0 to n  OR  XOR of all no's between a & b range
     *
     * Eg: Let's say n = 8
     * Note: x^0s = x | x^1s = ~x | x^x = 0
     *
     * n    XOR
     * 0    0
     * 1    1 (0^1 = 1)
     * 2    3 (0^1^2 -> 1^2)
     * 3    0 ((0^1^2)^3 -> 3^3)
     * 4    4 (0^4) 0 from previous up to 3
     * 5    1 (4^5) 4 from previous up to 4
     * 6    7 (1^6)
     * 7    0 (7^7)
     * 8    8 (0^8)
     */
    static int xor(int n) {
        /* Causes TLE for large numbers
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value ^= i;
        }
        System.out.println("XOR for " + n + " - " + value);
        */
        if (n % 4 == 0) return n;
        if (n % 4 == 1) return 1;
        if (n % 4 == 2) return n + 1;
        return 0;
    }

    /**
     * Find right most set bit for a number.
     *
     * Eg: n = 6, binary - 110
     * ans = n & (-n), that is 2
     *
     * Calculate -n is two ways
     * 1. Prefix 1 + (2^N-1 - K), N is 4th bit and K is 6
     *      2^3 - 6 => 2 (010)
     *      (1)010 => 10
     * 2. Flip N, then add 1 and then prepend the sign bit 1
     *      Flip 110 -> 001 and +1 => 010 => prepend 1 => (1)010
     *
     * Now, n & (-n) => 110 & 1010 => 0010 => 2 (Answer)
     */
    static void findRightMostSetBit(int n) {
        int mostSetBit = n & (-n);
        System.out.println("Most Set bit for " + n + " is " + mostSetBit);
    }

    static void shiftOperatorsTest() {
        System.out.println("1 << 0 - " + (1 << 0) + ", 1 << 1 - " + (1 << 1) + ", 1 << 2 - " + (1 << 2) + ", 1 << 3 - " + (1 << 3));
        int n = 3;
        System.out.println("x & 3 - " + (n & 1));
        // << means final n = n * 2^m, where is the no. of times
        // >> means final n = n / 2^m, where is the no. of times
        System.out.println("3 << 2 - " + (3 << 2));

        /**
         * 1 << 2 => 0100
         * ~(1 << 2) => 1011
         * -(1 << 2) => 1011
         *
         * 5 => 0101
         * ~(5) => -3 => 1101
         * -(5) => ?
         */
        System.out.println("1 << 2 - " + (1 << 2) + ", Binary - " + Integer.toBinaryString(1 << 2));
        System.out.println("~(1 << 2) - " + ~(1 << 2) + ", Binary - " + Integer.toBinaryString(~(1 << 2)));
        System.out.println("-(1 << 2) - " + -(1 << 2) + ", Binary - " + Integer.toBinaryString(-(1 << 2)));
        System.out.println();
        System.out.println("5 - " + 5 + ", Binary - " + Integer.toBinaryString(5));
        System.out.println("~(5) - " + ~(5) + ", Binary - " + Integer.toBinaryString(~(5)));
        System.out.println("-(5) - " + -(5) + ", Binary - " + Integer.toBinaryString(-(5)));
        System.out.println("12 & ~5 - " + (12 & ~(1 << 2)) + ", Binary - " + Integer.toBinaryString((12 & ~(1 << 2))));

        // Get Bit by AND
        BiFunction<Integer, Integer, Boolean> getBit = (num, i) -> (num & (1 << i)) != 0;
        BiFunction<Integer, Integer, Integer> getBitNum = (num, i) -> num & (1 << i);
        System.out.println("Get Bit for 4 and setting 2 - " + getBit.apply(4, 2));
        System.out.println("Get Bit for 3 and setting 2 - " + getBit.apply(3, 2));
        System.out.println("Get Bit for 4 and setting 2 - " + getBitNum.apply(4, 2)); // 4
        System.out.println("Get Bit for 3 and setting 2 - " + getBitNum.apply(3, 2)); // 0
        System.out.println("Get Bit for 6 and setting 2 - " + getBitNum.apply(6, 2)); // 4
        System.out.println("Get Bit for 6 and setting 3 - " + getBitNum.apply(6, 3)); // 0

        // Set Bit by OR - basically provides next number for shift 1 bit,
        // +2 for shift 2,
        // +4 for shift 3,
        // +8 for shift 4
        BiFunction<Integer, Integer, Integer> setBitNum = (num, i) -> num | (1 << i);
        System.out.println("Set Bit for 5 and setting 1 " + setBitNum.apply(5, 1));
        System.out.println("Set Bit for 5 and setting 2 " + setBitNum.apply(5, 2));
        System.out.println("Set Bit for 5 and setting 3 " + setBitNum.apply(5, 3));
        System.out.println("Set Bit for 5 and setting 4 " + setBitNum.apply(5, 4));

        // Clear Bit
        BiFunction<Integer, Integer, Integer> clearBit = (num, i) -> num & ~(1 << i);
        System.out.println("Clear Bit for 5 and setting 1 " + clearBit.apply(5, 1));
        System.out.println("Clear Bit for 5 and setting 2 " + clearBit.apply(5, 2));
        System.out.println("Clear Bit for 5 and setting 3 " + clearBit.apply(5, 3));
        System.out.println("Clear Bit for 5 and setting 4 " + clearBit.apply(5, 4));

        // Clear Bits MSB through I
        BiFunction<Integer, Integer, Integer> clearBitsMSBThroughI = (num, i) -> num | ((1 << i) - 1);
        System.out.println("Clear Bit MSB Through I for 5 and setting 1 " + clearBitsMSBThroughI.apply(5, 1));
        System.out.println("Clear Bit MSB Through I for 5 and setting 2 " + clearBitsMSBThroughI.apply(5, 2));
        System.out.println("Clear Bit MSB Through I for 5 and setting 3 " + clearBitsMSBThroughI.apply(5, 3));
        System.out.println("Clear Bit MSB Through I for 5 and setting 4 " + clearBitsMSBThroughI.apply(5, 4));

        // Clear Bits I through 0
        BiFunction<Integer, Integer, Integer> clearBitsIThrough0 = (num, i) -> num | (-1 << (i + 1));
        System.out.println("Clear Bits I Through 0 for 5 and setting 1 " + clearBitsIThrough0.apply(5, 1));
        System.out.println("Clear Bits I Through 0 for 5 and setting 2 " + clearBitsIThrough0.apply(5, 2));
        System.out.println("Clear Bits I Through 0 for 5 and setting 3 " + clearBitsIThrough0.apply(5, 3));
        System.out.println("Clear Bits I Through 0 for 5 and setting 4 " + clearBitsIThrough0.apply(5, 4));

        // Update Bit
        System.out.println("Update Bit for 5 and setting 1 and true " + updateBit(5, 1, true));
        System.out.println("Update Bit for 5 and setting 1 and false " + updateBit(5, 1, false));
        System.out.println("Update Bit for 5 and setting 2 and true " + updateBit(5, 2, true));
        System.out.println("Update Bit for 5 and setting 2 and false " + updateBit(5, 2, false));
        System.out.println("Update Bit for 5 and setting 3 and true " + updateBit(5, 3, true));
        System.out.println("Update Bit for 5 and setting 3 and false " + updateBit(5, 3, false));
        System.out.println("Update Bit for 5 and setting 4 and true " + updateBit(5, 4, true));
        System.out.println("Update Bit for 5 and setting 4 and false " + updateBit(5, 4, false));
    }

    private static int updateBit(int num, int i, boolean bitIs1) {
        int value = bitIs1 ? 1 : 0;
        int mask = ~(1 << i);
        return (num & mask) | (value << i);
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
        arr = new int[]{2, 2, 3, 2, 7, 7, 8, 7, 8, 8};
        int repeatTimes = 3;
        findOddOccuringElement(arr, repeatTimes);
        // Is the number of Power of 2
        isPowerOfTwo(24);
        isPowerOfTwo(16);
        // Power of any base, find the magic number
        findPowerOfN(5, 6); // 100 - 125 = (125 + 0 + 0)
        findPowerOfNOptimal(3, 6);
        // Count Set bit counts
        countSetBits(15);
        // Range for xor a, b = xor(b) ^ xor(a - 1)
        a = 3;
        b = 9;
        int ans = xor(b) ^ xor(a - 1);
        System.out.println("XOR of Range from 3 to 9 - " + ans);
        // Most set for number
        findRightMostSetBit(6);
        findRightMostSetBit(160);
        System.out.println("6 is ~6: " + (~6) + ", 6 is -6: " + (-6));
        System.out.println("12 & ~5 - " + (12 & ~(1 << 2)) + ", Binary - " + Integer.toBinaryString((12 & ~(1 << 2))));
    }

}
