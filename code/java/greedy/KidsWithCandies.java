package code.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 */
public class KidsWithCandies {

    private static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }
        return result;
    }

    private static List<Boolean> kidsWithCandiesOneLine(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        return Arrays.stream(candies).mapToObj(candy -> (candy + extraCandies >= max)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        BiConsumer<int[], List<Boolean>> logger = (candies, result) -> {
            System.out.println("Candies - " + Arrays.toString(candies) + ", Result - " + result);
        };
        //
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        List<Boolean> result = kidsWithCandiesOneLine(candies, extraCandies);
        logger.accept(candies, result);
        //
        candies = new int[] {4, 2, 1, 1, 2};
        extraCandies = 1;
        result = kidsWithCandiesOneLine(candies, extraCandies);
        logger.accept(candies, result);
        //
        candies = new int[] {12, 1, 12};
        extraCandies = 10;
        result = kidsWithCandiesOneLine(candies, extraCandies);
        logger.accept(candies, result);
    }

}
