package code.java.numbers;

/**
 * https://leetcode.com/problems/reverse-integer/
 */
public class ReverseNumber {

    /**
     * Time complexity: O(d), d is digit count of a number
     * Space complexity: O(1)
     */
    private static int reverse(int x) {
        long num = 0, remainder = 0;
        int sign = 1;
        if (x < 0) {
            x *= -1;
            sign = -1;
        }
        while (x > 0) {
            remainder = x % 10;
            num = (num * 10) + remainder;
            x /= 10;
        }
        return num > Integer.MAX_VALUE ? 0 : (int) num * sign;
    }

    /**
     * Reverse Number
     */
    private static int reverseNumber(int x) {
        int digits = (int) Math.log10(x) + 1;
        return reverseHelper(x, digits);
    }

    private static int reverseHelper(int x, int digits) {
        if (x % 10 == x) return x;
        return x % 10 * (int) Math.pow(10, digits - 1) + reverseHelper(x / 10, digits - 1);
    }

    public static void main(String[] args) {
        int x = 1432;
        System.out.println(x + " Reverse - " + reverse(x));
        System.out.println(x + " Reverse - " + reverseNumber(x));
        //
        x = 470;
        System.out.println(x + " Reverse - " + reverse(x));
        System.out.println(x + " Reverse - " + reverseNumber(x));
        //
        x = -123;
        System.out.println(x + " Reverse - " + reverse(x));
        //
        int y = -1563847412;
        System.out.println(y + " Reverse - " + reverse(y));
    }

}
