package code.java.trees;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/my-calendar-iii/
 */
public class MyCalendarThree {

    private Map<Integer, Integer> lines;

    // https://leetcode.com/submissions/detail/816942221/
    public MyCalendarThree() {
        lines = new TreeMap<>();
    }

    public int book(int start, int end) {
        lines.put(start, lines.getOrDefault(start, 0) + 1);
        // however, the interval in this problem is [start, end), so we don't need to add 1 here.
        lines.put(end, lines.getOrDefault(end, 0) - 1);
        int mx = 0, cnt = 0;
        for (int x : lines.values()) {
            // Calculate the prefix sum
            cnt += x;
            // Record the maximum overlapping intervals
            mx = Math.max(mx, cnt);
        }
        return mx;
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (input, result) -> {
            System.out.println("Start - " + input[0] + ", End - " + input[1] + ", Interval - " + result);
        };
        //
        MyCalendarThree calendar = new MyCalendarThree();
        int start = 10, end = 20;
        int interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
        //
        start = 50; end = 60;
        interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
        //
        start = 10; end = 40;
        interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
        //
        start = 5; end = 15;
        interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
        //
        start = 5; end = 10;
        interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
        //
        start = 25; end = 55;
        interval = calendar.book(start, end);
        logger.accept(new int[] {start, end}, interval);
    }

}
