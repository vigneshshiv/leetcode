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


    public static void main(String[] args) {
        int[] ratings = {1, 3, 7, 1}; // 7 (Result [1,2,3,1])
        int[] input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
        //
        ratings = new int[] {1, 7, 4, 3, 1}; // 11 (Result [1,4,3,2,1])
        input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
        //
        ratings = new int[] {1, 2, 7, 4, 3, 3, 1}; // 12 (Result [1,2,3,2,1,2,1])
        input = ratings.clone();
        System.out.println("Input - " + Arrays.toString(input) + ", Distributed candies - " + distributeCandiesToKids(ratings));
    }

}
