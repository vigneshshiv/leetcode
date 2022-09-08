package code.java.numbers;

/**
 * https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 */
public class ProductSumDiff {

    private static int subtractProductAndSum(int n) {
        int product = 1, sum = 0;
        while (n > 0) {
            int digit = n % 10;
            product *= digit;
            sum += digit;
            n /= 10;
        }
        return product - sum;
    }

    public static void main(String[] args) {
        System.out.println("234 - " + subtractProductAndSum(234));
        System.out.println("4421 - " + subtractProductAndSum(4421));
    }

}
