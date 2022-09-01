package code.java.numbers;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/number-of-good-pairs/
 */
public class IdenticalPairs {

    private static int numIdenticalPairs(int[] nums) {
        int[] frequency = new int[101];
        for (int num : nums) {
            frequency[num] += 1;
        }
        IntUnaryOperator mapper = num -> (num * (num - 1)) / 2;
        return Arrays.stream(frequency).filter(num -> num > 1).map(mapper).sum();
    }

    private static int numIdenticalPairsOptimal(int[] nums) {
        int count = 0;
        int[] frequency = new int[101];
        for (int num : nums) {
            count += frequency[num]++;
        }
        return count;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, count) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Count - " + count);
        };
        //
        int[] nums = {1, 2, 3, 1, 1, 3};
        int count = numIdenticalPairs(nums);
        logger.accept(nums, count);
        count = numIdenticalPairsOptimal(nums);
        logger.accept(nums, count);
        //
        nums = new int[] {1, 1, 1, 1};
        count = numIdenticalPairs(nums);
        logger.accept(nums, count);
        count = numIdenticalPairsOptimal(nums);
        logger.accept(nums, count);
        //
        nums = new int[] {1, 2, 3};
        count = numIdenticalPairs(nums);
        logger.accept(nums, count);
        count = numIdenticalPairsOptimal(nums);
        logger.accept(nums, count);
    }

}
