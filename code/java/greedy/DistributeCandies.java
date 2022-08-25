package code.java.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * N Kids stand in a line, each having an integer rating.
 *
 * We distribute candies following:
 *  - Each kid gets at least 1 candy
 *  - Kids with higher ratings than their neighbours get more candies
 *
 *  Find the minimum candies required.
 */
public class DistributeCandies {

    /**
     * Time complexity: O(n log(n)) - sorting
     * Space complexity: O(n)
     *
     * Not working for this - {1, 2, 7, 4, 3, 3, 1}; // 12 (Result [1,2,3,2,1,2,1])
     */
    private static int distributeCandiesToKids(int[] ratings) {
        Arrays.sort(ratings);
        List<Integer> indices = new LinkedList<>();
        for (int idx = 0; idx < ratings.length; idx++) {
            indices.add(idx);
        }
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int idx : indices) {
            if (idx > 0 && ratings[idx] > ratings[idx - 1]) {
                candies[idx] = Math.max(candies[idx], candies[idx - 1] + 1);
            }
            if (idx < ratings.length - 1 && ratings[idx] > ratings[idx + 1]) {
                candies[idx] = Math.max(candies[idx], candies[idx + 1] + 1);
            }
        }
        System.out.println(Arrays.toString(candies));
        return Arrays.stream(candies).sum();
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int distributeCandiesOptimal(int[] ratings){
        int prev = 0, distributedCandies = 0, decreaseSequence = 0, total = 0;
        for (int rating : ratings) {
            if (rating >= prev) {
                if (decreaseSequence > 0) {
                    if (decreaseSequence >= distributedCandies) {
                        total += 1 + decreaseSequence - distributedCandies;
                    }
                    // Reset and starts from base condition
                    decreaseSequence = 0;
                    distributedCandies = 1;
                }
                distributedCandies = rating > prev ? distributedCandies + 1 : 1;
                total += distributedCandies;
            } else {
                decreaseSequence += 1;
                total += decreaseSequence;
            }
            prev = rating;
        }
        if (decreaseSequence >= distributedCandies) {
            total += 1 + decreaseSequence - distributedCandies;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 3, 7, 1}; // 7 (Result [1,2,3,1])
        int[] input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
        System.out.println("Optimal: Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesOptimal(input));
        //
        ratings = new int[] {1, 7, 4, 3, 1}; // 11 (Result [1,4,3,2,1])
        input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
        System.out.println("Optimal: Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesOptimal(input));
        //
        ratings = new int[] {1, 2, 7, 4, 3, 3, 1}; // 12 (Result [1,2,3,2,1,2,1])
        input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
        System.out.println("Optimal: Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesOptimal(input));
    }

}
