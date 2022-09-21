package code.java.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/last-stone-weight/
 */
public class LastStoneWeight {

    private static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : stones) queue.offer(x);
        while (queue.size() > 1) {
            queue.offer(queue.poll() - queue.poll());
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (stones, lastOne) -> {
            System.out.println("Stones - " + Arrays.toString(stones) + ", Last - " + lastOne);
        };
        //
        int[] stones = {2, 7, 4, 1, 8, 1};
        int lastOne = lastStoneWeight(stones);
        logger.accept(stones, lastOne);
        //
        stones = new int[] {2, 7, 4, 1, 8};
        lastOne = lastStoneWeight(stones);
        logger.accept(stones, lastOne);
        //
        stones = new int[] {1};
        lastOne = lastStoneWeight(stones);
        logger.accept(stones, lastOne);
        //
        stones = new int[] {7, 6, 7, 6, 9};
        lastOne = lastStoneWeight(stones);
        logger.accept(stones, lastOne);
    }

}
