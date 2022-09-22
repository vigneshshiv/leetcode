package code.java.queues;

import java.util.PriorityQueue;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInStream {

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int size;

    /**
     * Min heap implementation by default, as long as priority queue remains the size of K,
     *  will achieve Kth larger element in stream
     */
    public KthLargestElementInStream(int k, int[] nums) {
        this.size = k;
        for (int num : nums) pq.offer(num);
        while (pq.size() > size) pq.poll();
    }

    public int add(int val) {
        pq.offer(val);
        while (pq.size() > size) pq.poll();
        return pq.peek();
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        int k = 3;
        int[] nums = {4, 5, 8, 2};
        KthLargestElementInStream obj = new KthLargestElementInStream(k, nums);
        //
        int input = 3;
        int result = obj.add(input);
        logger.accept(input, result);
        //
        input = 5; result = obj.add(input);
        logger.accept(input, result);
        //
        input = 10; result = obj.add(input);
        logger.accept(input, result);
        //
        input = 9; result = obj.add(input);
        logger.accept(input, result);
        //
        input = 4; result = obj.add(input);
        logger.accept(input, result);
    }

}
