package code.java.numbers;

/**
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 */
public class NumberOfSteps {

    private static int numberOfStepsToZero(int num) {
        return stepsToZero(num, 0);
    }

    private static int stepsToZero(int num, int count) {
        if (num == 0) return count;
        return stepsToZero((num & 1) == 0 ? num / 2 : num - 1, count + 1);
    }

    public static void main(String[] args) {
        int num = 14;
        System.out.println(num + " no of steps to reduce to zero - " + numberOfStepsToZero(num));
        //
        num = 8;
        System.out.println(num + " no of steps to reduce to zero - " + numberOfStepsToZero(num));
        //
        num = 123;
        System.out.println(num + " no of steps to reduce to zero - " + numberOfStepsToZero(num));
    }

}
