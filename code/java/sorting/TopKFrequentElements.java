package code.java.sorting;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {

    private static int[] topKFrequentBucket(int[] nums, int k) {
        // Number occurrence frequency
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        // Bucket Sort on frequency
        Map<Integer, List<Integer>> table = new HashMap<>();
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int i = 0; i < bucket.length; i++) bucket[i] = new ArrayList();
        for (int key : frequency.keySet()) {
            bucket[frequency.get(key)].add(key);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length; i > 0 && result.size() < k; i--) {
            result.addAll(bucket[i]);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> table = new HashMap<>(); // Value frequency
        Map<Integer, Integer> frequency = new HashMap<>(); // Number occurrence frequency
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        frequency.forEach((key, value) -> {
            List<Integer> keys = table.getOrDefault(value, new ArrayList<>());
            keys.add(key);
            table.put(value, keys);
        });
        for (int i = nums.length; i > 0 && result.size() < k; i--) {
            if (table.containsKey(i)) {
                result.addAll(table.get(i));
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] topKFrequentHeapify(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> frequency = new HashMap<>(); // Number occurrence frequency
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for(Map.Entry<Integer,Integer> entry: frequency.entrySet()){
            priorityQueue.add(entry);
        }
        while (result.size() < k) {
            result.add(priorityQueue.poll().getKey());
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        System.out.println("Top Frequent Elements - " + Arrays.toString(result));
        //
        nums = new int[] {1};
        k = 1;
        result = topKFrequent(nums, k);
        System.out.println("Top Frequent Elements - " + Arrays.toString(result));
        //
        nums = new int[] {4, 1, -1, 2, -1, 2, 3};
        k = 2;
        result = topKFrequent(nums, k);
        System.out.println("Top Frequent Elements - " + Arrays.toString(result));
    }

}
