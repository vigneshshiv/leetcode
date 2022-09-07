package code.java.arrays;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
 */
public class AverageSalary {

    private static double average(int[] salary) {
        double total = 0d;
        int min = Integer.MAX_VALUE, max = 0;
        for (int amt : salary) {
            total += amt;
            if (amt < min) {
                min = amt;
            }
            if (amt > max) {
                max = amt;
            }
        }
        return (total - (min + max)) / (salary.length - 2);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Double> logger = (salary, average) -> {
            System.out.println("Salary - " + Arrays.toString(salary) + ", Average Salary excluding min & max - " + average);
        };
        //
        int[] salary = {4000, 3000, 1000, 2000};
        double average = average(salary);
        logger.accept(salary, average);
        //
        salary = new int[] {1000, 2000, 3000};
        average = average(salary);
        logger.accept(salary, average);
    }

}
