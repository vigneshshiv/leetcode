package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/add-binary/
 */
public class BinaryStringAddition {

    private static String addTwoNumbers(String a, String b) {
        if (a.length() < b.length()) {
            String n = a;
            a = b; b = n;
        }
        StringBuilder number = new StringBuilder();
        boolean carry = false;
        for (int idx = 0; idx < a.length(); idx++) {
            int i = a.length() - 1 - idx;
            int j = b.length() - 1 - idx;
            int count = a.charAt(i) == '1' ? 1 : 0;
            count += (j >= 0 && b.charAt(j) == '1') ? 1 : 0;
            if (carry) {
                count += 1;
            }
            number.append(count % 2 == 0 ? "0" : "1");
            carry = count > 1 ? true : false;
        }
        if (carry) {
            number.append("1");
        }
        return number.reverse().toString();
    }

    private static String addTwoNumbersOptimal(String a, String b) {
        StringBuilder number = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            number.append(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) number.append(carry);
        return number.reverse().toString();
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (nums, result) -> {
            System.out.println("N1 - " + nums[0] + ", N2 - " + nums[1] + ", Result - " + result);
        };
        //
        String n1 = "1", n2 = "0";
        String number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "1"; n2 = "1";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "10"; n2 = "1";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "10"; n2 = "10";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "111"; n2 = "1";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "1111111111111111111111111111"; n2 = "1";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
        //
        n1 = "1010"; n2 = "1011";
        number = addTwoNumbers(n1, n2);
        logger.accept(new String[] {n1, n2}, number);
    }

}
