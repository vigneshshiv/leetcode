package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/add-digits/
 */
public class AddDigits {

    private static int addDigits(int num) {
        if (num < 9) return num;
        do {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        } while (num > 9);
        return num;
    }

    /**
     * https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
     */
    private static int addDigitsOptimal(int num) {
        return 1 + (num - 1) % 9;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (num, result) -> System.out.println("Num - " + num + ", Result - " + result);
        //
        int num = 38;
        int result = addDigitsOptimal(num);
        logger.accept(num, result);
        //
        num = 0; result = addDigits(num);
        logger.accept(num, result);
        //
        num = 100; result = addDigits(num);
        logger.accept(num, result);
    }

}
