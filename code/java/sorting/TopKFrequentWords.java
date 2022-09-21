package code.java.sorting;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    private static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new LinkedList<>();
        Map<Integer, List<String>> table = new HashMap<>(); // Value frequency
        Map<String, Integer> frequency = new HashMap<>(); // Word occurrence frequency
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        frequency.forEach((key, value) -> {
            List<String> keys = table.getOrDefault(value, new ArrayList<>());
            keys.add(key);
            table.put(value, keys);
        });
        for (int i = words.length; i > 0; i--) {
            if (table.containsKey(i)) {
                List<String> sorted = table.get(i).stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                for (int j = 0; j < sorted.size() && result.size() < k; j++) {
                    result.add(sorted.get(j));
                }
            }
        }
        return result;
    }

    private static List<String> topKFrequentOptimal(String[] words, int k) {
        Map<String, Integer> frequency = new HashMap<>(); // Word occurrence frequency
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        List<String> result = new ArrayList<>(frequency.keySet());
        Comparator<String> wordComparator = (w1, w2) -> {
            return frequency.get(w1) == frequency.get(w2) ? w1.compareTo(w2) : frequency.get(w2) - frequency.get(w1);
        };
        Collections.sort(result, wordComparator);
        return result.subList(0, k);
    }

    /**
     * https://leetcode.com/problems/top-k-frequent-words/discuss/2603190
     *
     *
     * Min Heap approach
     */
    private static List<String> topKFrequentByPriorityQueue(String[] words, int k) {
        LinkedList<String> result = new LinkedList<>();
        Map<String, Integer> frequency = new HashMap<>(); // Word occurrence frequency
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        Comparator<String> wordComparator = (w1, w2) -> {
            return frequency.get(w1) == frequency.get(w2) ? w2.compareTo(w1) : frequency.get(w1) - frequency.get(w2);
        };
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(wordComparator);
        for (String word : frequency.keySet()) {
            priorityQueue.offer(word);
            if (priorityQueue.size() > k) priorityQueue.poll();
        }
        while (!priorityQueue.isEmpty()) {
            result.addFirst(priorityQueue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String[][], List<String>> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input[0]) + ", K - " + Arrays.toString(input[1]));
            System.out.println("Top K Frequent words - " + result);
        };
        //
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> result = topKFrequentByPriorityQueue(words, k);
        logger.accept(new String[][] {words, new String[] {String.valueOf(k)}}, result);
        //
        k = 1;
        result = topKFrequent(words, k);
        logger.accept(new String[][] {words, new String[] {String.valueOf(k)}}, result);
        //
        words = new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        k = 4;
        result = topKFrequentOptimal(words, k);
        logger.accept(new String[][] {words, new String[] {String.valueOf(k)}}, result);
    }

}
