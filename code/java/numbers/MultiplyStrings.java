package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings {

    private static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
                pos[p2] = sum % 10;
                pos[p1] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.isEmpty() && p == 0)) sb.append(p);
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (nums, product) -> {
            System.out.println("N1 - " + nums[0] + ", N2 - " + nums[1] + ", Product - " + product);
        };
        //
        String num1 = "2", num2 = "3";
        String product = multiply(num1, num2);
        logger.accept(new String[] {num1, num2}, product);
        //
        num1 = "123"; num2 = "456";
        product = multiply(num1, num2);
        logger.accept(new String[] {num1, num2}, product);
    }

}
