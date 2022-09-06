package code.java.arrays;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 */
public class ReduceArraySizeToHalf {

    private static int minSetSizeByPriorityQueue(int[] arr) {
        int count = 0, halfSize = arr.length / 2;
        Map<Integer, Integer> table = new HashMap<>();
        for (int num : arr) {
            table.put(num, table.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(table.size(), Collections.reverseOrder());
        for (int val : table.values()) {
            queue.offer(val);
        }
        while (!queue.isEmpty() && halfSize > 0) {
            halfSize -= queue.poll();
            count++;
        }
        return count;
    }

    // TODO!
    private static int minSetSize(int[] arr) {
        int count = 0, halfSize = arr.length / 2;
        Map<Integer, Integer> table = new HashMap<>();
        for (int num : arr) {
            table.put(num, table.getOrDefault(num, 0) + 1);
        }
        Set<Integer> visited = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            int remaining = halfSize - entry.getValue() == 0 ? 1 : halfSize - entry.getValue();
            if (visited.add(entry.getKey())
                    && (entry.getValue() >= halfSize || (entry.getValue() < halfSize && table.containsValue(remaining)))) {
                count += 1;
                halfSize -= entry.getValue();
                visited.add(getMatchKey(table, entry.getKey(), remaining));
            }
        }
        return count;
    }

    private static int getMatchKey(Map<Integer, Integer> table, int key, int value) {
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            if (entry.getKey() != key && entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, count) -> System.out.println("Input - " + Arrays.toString(input) + ", Count - " + count);
        //
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        int count = minSetSize(arr);
        logger.accept(arr, count);
        //
        arr = new int[] {7, 7, 7, 7, 7, 7};
        count = minSetSize(arr);
        logger.accept(arr, count);
        //
        arr = new int[] {1, 9};
        count = minSetSize(arr);
        logger.accept(arr, count);
        //
        arr = new int[] {1000, 1000, 3, 7};
        count = minSetSizeByPriorityQueue(arr);
        logger.accept(arr, count);
    }

}
