package code.java.linkedlist;

/**
 * https://leetcode.com/problems/happy-number/
 */
public class HappyNumber {

    private static boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(digitSquareSum(fast));
        } while (slow != fast);
        //
        return slow == 1 ? true : false;
    }

    private static int digitSquareSum(int num) {
        int ans = 0;
        while (num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(n + " Is happy number? - " + isHappy(n));
        //
        n = 2;
        System.out.println(n + " Is happy number? - " + isHappy(n));
        //
        n = 1;
        System.out.println(n + " Is happy number? - " + isHappy(n));
    }

}
