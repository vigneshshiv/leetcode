package code.java.queues;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

    private static int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        int[] frequency = new int[26];
        int max = 0, max_freq = 0;
        for (char c : tasks) {
            int idx = c - 'A';
            frequency[idx]++;
            if (frequency[idx] > max) {
                max = frequency[idx];
                max_freq = 1;
            } else if (frequency[idx] == max) {
                max_freq++;
            }
        }
        // max - 1 -> means char frame, If A occur 3 times, A holds 2 frames to hold other tasks
        // (n + 1) interval, before and after interval counted, If n is 1, task1 -> idle -> task2, i.e, (so n + 1)
        // Above two holds rest of the tasks, plus max frequency occurrance of the same repeated tasks.
        return Math.max(tasks.length, (max - 1) * (n + 1) + max_freq);
    }

    private static int leastIntervalByQueue(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : tasks) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (char c : frequency.keySet()) {
            queue.offer(frequency.get(c));
        }
        Map<Integer, Integer> scheduler = new HashMap<>();
        int runningTime = 0;
        while (!queue.isEmpty() || !scheduler.isEmpty()) {
            if (scheduler.containsKey(runningTime - n - 1)) {
                queue.offer(scheduler.remove(runningTime - n - 1));
            }
            if (!queue.isEmpty()) {
                int left = queue.poll() - 1;
                if (left > 0) {
                    scheduler.put(runningTime, left);
                }
            }
            runningTime += 1;
        }
        return runningTime;
    }

    public static void main(String[] args) {
        BiConsumer<char[][], Integer> logger = (input, result) -> {
            System.out.println("Tasks - " + Arrays.toString(input[0]) + ", Interval - " + Arrays.toString(input[1]) + ", Result - " + result);
        };
        //
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int interval = 2;
        int result = leastInterval(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
        result = leastIntervalByQueue(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
        //
        interval = 0;
        result = leastInterval(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
        result = leastIntervalByQueue(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
        //
        tasks = new char[] {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        interval = 2;
        result = leastInterval(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
        result = leastIntervalByQueue(tasks, interval);
        logger.accept(new char[][]{tasks, new char[] {(char) interval}}, result);
    }

}
