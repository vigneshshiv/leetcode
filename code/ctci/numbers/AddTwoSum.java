package code.ctci.numbers;

import java.util.Objects;

public class AddTwoSum {

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

    public static void main(String[] args) {
        addTwoSum("999999999999999999999999999", "3");
    }

}
