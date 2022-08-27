package code.java.numbers;

public class ReverseNumber {

    /**
     * Time complexity: O(d), d is digit count of a number
     * Space complexity: O(1)
     */
    private static int reverse(int x) {
        int num = 0, remainder = 0;
        while (x > 0) {
            remainder = x % 10;
            num = (num * 10) + remainder;
            x /= 10;
        }
        return num;
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
    }

}
