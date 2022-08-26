package code.java.numbers;

/**
 * https://leetcode.com/problems/palindrome-number/
 */
public class PalindromeNumber {

    /**
     * Time complexity: O(d), d is digit count of a number
     * Space complexity: O(1)
     */
    private static boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) return false;
        int n = x;
        int num = 0, remainder = 0;
        while (x > 0) {
            remainder = x % 10;
            num = (num * 10) + remainder;
            x /= 10;
        }
        return n == num;
    }

    public static void main(String[] args) {
        int num = 121;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = -121;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = 10;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = 5;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = 22;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = 232;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
        //
        num = 45;
        System.out.println(num + " is a palindrome? " + isPalindrome(num));
    }

}
