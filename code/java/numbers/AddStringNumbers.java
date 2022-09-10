package code.java.numbers;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/add-strings/
 */
public class AddStringNumbers {

    /**
     * Add two large numbers using String format
     */
    private static void addTwoSum(String num1, String num2) {
        if (Objects.isNull(num1) || num1.isEmpty() || Objects.isNull(num2) || num2.isEmpty()) {
            return;
        }
        String result = "";
        int carry = 0;
        int num1Len = num1.length(), num2Len = num2.length();
        System.out.println("Num1 Len - " + num1Len + ", Num2 Len - " + num2Len);
        int maxLength = Math.max(num1Len, num2Len);
        for (int i = 1; i <= maxLength; i++) {
            int num1Last = num1Len - i >= 0 ? Character.getNumericValue(num1.charAt(num1Len - i)) : 0;
            int num2Last = num2Len - i >= 0 ? Character.getNumericValue(num2.charAt(num2Len - i)) : 0;
            int sum = num1Last + num2Last + carry;
            carry = sum / 10;
            sum = sum % 10;
            if (maxLength == i && carry > 0) {
                result = (carry * 10) + sum + result;
            } else {
                result = sum + result;
            }
        }
        System.out.println(num1 + " + " + num2 + " = " + result);
    }

    private static String addStrings(String num1, String num2) {
        StringBuilder number = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += num1.charAt(i--) - '0';
            if (j >= 0) sum += num2.charAt(j--) - '0';
            number.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) number.append(carry);
        return number.reverse().toString();
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (nums, result) -> {
            System.out.println("Num1 - " + nums[0] + ", Num2 - " + nums[1] + ", Result - " + result);
        };
        //
        addTwoSum("999999999999999999999999999", "3");
        //
        String num1 = "11", num2 = "123";
        String number = addStrings(num1, num2);
        logger.accept(new String[] {num1, num2}, number);
        //
        num1 = "456"; num2 = "77";
        number = addStrings(num1, num2);
        logger.accept(new String[] {num1, num2}, number);
        //
        num1 = "100"; num2 = "1";
        number = addStrings(num1, num2);
        logger.accept(new String[] {num1, num2}, number);
        //
        num1 = "9999"; num2 = "3";
        number = addStrings(num1, num2);
        logger.accept(new String[] {num1, num2}, number);
    }

}
